package at.carrier.truck.boundary;

import java.net.URI;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TruckBoundaryIT {

    private static final String RUT_URI = "http://localhost:8282/truck/resources/trucks";

    //Resource Under Test
    private WebTarget rut;
    private Client client;

    @Before
    public void initClient() {
        this.client = ClientBuilder.newBuilder().
                build();
        this.rut = client.target(RUT_URI);
    }


    @Test
    public void crud() {
        Truck in = new Truck();
        in.setHorsePower(455.5d);
        in.setKfz("KR-277AI");
        in.setModel("Volvo");
        Response response = this.rut.request(MediaType.APPLICATION_JSON).
                post(Entity.entity(in, MediaType.APPLICATION_XML));
        assertThat(response.getStatus(), is(201));
        URI location = response.getLocation();
        assertNotNull(location);
        //post / creation
        JsonObject out = this.client.target(location).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(JsonObject.class);
        assertThat(out.getString("kfz"), is(in.getKfz()));
        //all
        JsonArray result = this.rut.request().
                accept(MediaType.APPLICATION_JSON).
                get(JsonArray.class);
        assertNotNull(result);
        assertTrue(result.size() > 0);

    }
}
