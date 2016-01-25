package invocing.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import invocing.jpa.exceptions.EntityAlreadyExistsException;
import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Contragent;
import invoicing.entity.Item;

import static invoicing.entity.ContragentType.*;

public class JPALauncher {
	private static EntityManagerFactory emf;
	private static ContragentJPAController contragentController;
	private static ItemJPAController itemController;
	private static PositionJPAController positionController;
//	private static InvoiceJPAController invoiceController;

	public JPALauncher() {		
	}
	
	public static void init(){
		//1. Create EntityManagerFactory
		emf = Persistence.createEntityManagerFactory("Invoicing");
		//2. Initialize application singleton JPAControllers 
		contragentController = new ContragentJPAController();
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static ContragentJPAController getContragentController() {
		return contragentController;
	}
	
	public static ItemJPAController getItemController() {
		return itemController;
	}
	
	public static PositionJPAController getPositionController() {
		return positionController;
	}
	
//	public static InvoiceJPAController getInvoiceController() {
//		return invoiceController;
//	}
	
	public static void main(String[] args) {
		JPALauncher.init();
		EntityManager em = JPALauncher.getEntityManager();

		List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
//		for(Item item : items)
//			System.out.println(item);
		
		Contragent c1 = new Contragent(456644555, "WebWidgets Ltd.", "Plovdiv 4000", 
				ORGANIZATION, "Stoyan Petrov");
//		try {
//			getContragentController().create(c1);
//			Contragent c2 = em.find(Contragent.class, c1.getIdNumber());
//			c2.setAddress("Plovdiv 4000");
//			getContragentController().update(c2);
//			getContragentController().delete(c2.getIdNumber());
//		} catch (EntityAlreadyExistsException e) {
//			e.printStackTrace();
//		} catch (EntityDoesNotExistException e) {
//			e.printStackTrace();
//		}
		
		
		List<Contragent> contragents = getContragentController().findAll();
//		List<Contragent> contragents = em.createNamedQuery("Contragent.findAll", 
//				Contragent.class)
//			.getResultList();
		for(Contragent contrgent : contragents)
			System.out.println(contrgent);
		
		//Print count
		System.out.println("Contragents count: " + getContragentController().getCount());

	}

}
