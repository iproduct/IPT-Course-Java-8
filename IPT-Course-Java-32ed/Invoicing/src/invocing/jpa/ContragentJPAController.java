package invocing.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import invocing.jpa.exceptions.EntityAlreadyExistsException;
import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Contragent;

public class ContragentJPAController{
	
	public Contragent create(Contragent contragent)  
			throws EntityAlreadyExistsException {
		EntityManager em = JPALauncher.getEntityManager();
		if(em.find(Contragent.class, contragent.getIdNumber()) != null)
			throw new EntityAlreadyExistsException("Contragent with Id Number = " 
					+ contragent.getIdNumber() + " already exists.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.persist(contragent);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return contragent;
	}
	
	public Contragent update(Contragent contragent)  
			throws EntityDoesNotExistException {
		EntityManager em = JPALauncher.getEntityManager();
		Contragent old = em.find(Contragent.class, contragent.getIdNumber());
		if(old == null)
			throw new EntityDoesNotExistException("Contragent with Id Number = " 
					+ contragent.getIdNumber() + " does not exist.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			contragent = em.merge(contragent);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return contragent;
	}
	
	public Contragent delete(long contragentId)  
			throws EntityDoesNotExistException {
		EntityManager em = JPALauncher.getEntityManager();
		Contragent contragent = em.find(Contragent.class, contragentId);
		if(contragent == null)
			throw new EntityDoesNotExistException("Contragent with Id Number = " 
					+ contragent.getIdNumber() + " does not exist.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.remove(contragent);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return contragent;
	}
	
	public List<Contragent> find(int from, int maxNumber) {
		EntityManager em = JPALauncher.getEntityManager();
		return em.createNamedQuery("Contragent.findAll", Contragent.class)
//				.setParameter("orderedBy", orderBy)
				.setFirstResult(from)
				.setMaxResults(maxNumber)
				.getResultList();
	}
	
	public List<Contragent> findAll() {
		return find(0, Integer.MAX_VALUE);
	}
	
	public long getCount(){
		EntityManager em = JPALauncher.getEntityManager();
		return em.createNamedQuery("Contragent.count", Long.class)
				.getSingleResult();
	}
	
}
