package at.carrier.truck.boundary;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import at.carrier.truck.entity.Truck;

@Stateless
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TuckResource {

	@Inject
	private Trucks trucks;

	@POST
	public Response addTruck(Truck request, @Context UriInfo info) {
		Truck truck = trucks.insert(request);
		JsonObject json = truck.toJson();
		URI uri = info.getAbsolutePathBuilder().path("/" + truck.getId()).build();
		return Response.created(uri).entity(json).build();
	}

	@GET
	@Path("{id}")
	public Truck find(@PathParam("id") long id) {
		return trucks.find(id);
	}

	@GET
	public Response all() {
		JsonArray registrationList = allAsJson(this.trucks.findAll());
		if (registrationList == null || registrationList.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(registrationList).build();
	}

	
    public JsonArray allAsJson(List<Truck> trucks) {
        Collector<JsonObject, ?, JsonArrayBuilder> jsonCollector
                = Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                        (left, right) -> {
                            left.add(right);
                            return left;
                        });
        return trucks.stream().map(Truck::toJson).
                collect(jsonCollector).build();

    }
}
