package invocing.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContragentJPAController {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ContragentJPAController() {
		
	}
	
	public void init(){
		//1. Create EntityManagerFactory
		emf = Persistence.createEntityManagerFactory("Invoicing");
	}
	
	public static void main(String[] args) {
		ContragentJPAController cController = new ContragentJPAController();
		cController.init();

	}

}
