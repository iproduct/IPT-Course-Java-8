package invocing.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import invoicing.entity.Item;

public class JPAControllerLauncer {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public JPAControllerLauncer() {		
	}
	
	public static void init(){
		//1. Create EntityManagerFactory
		emf = Persistence.createEntityManagerFactory("Invoicing");
	}
	
	public static EntityManager getEntityManager() {
		if(em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}
	
	public static void main(String[] args) {
		JPAControllerLauncer.init();
		EntityManager em = JPAControllerLauncer.getEntityManager();
		@SuppressWarnings("unchecked")
		List<Item> items = em.createNamedQuery("Item.findAll").getResultList();
		for(Item item : items)
			System.out.println(item);
	}

}
