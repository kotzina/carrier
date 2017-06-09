package at.carrier.truck.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import at.carrier.truck.entity.Truck;

@Stateless
public class Trucks {

	@PersistenceContext
    EntityManager em;
	
    public Truck find(long truckId) {
        return this.em.find(Truck.class, truckId);
    }
    
    public Truck insert(Truck truck) {
        this.em.persist(truck);
        return this.em.find(Truck.class,truck.getId());
    }
    
    public List<Truck> findAll(){
    	return this.em.createNamedQuery(Truck.findAll,Truck.class).getResultList();
    }
}
