package invocing.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import invocing.jpa.exceptions.EntityAlreadyExistsException;
import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Item;

public class ItemJPAController{
	
	public Item create(Item Item)  
			throws EntityAlreadyExistsException {
		EntityManager em = JPALauncher.getEntityManager();
		if(em.find(Item.class, Item.getId()) != null)
			throw new EntityAlreadyExistsException("Item with Id Number = " 
					+ Item.getId() + " already exists.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.persist(Item);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return Item;
	}
	
	public Item update(Item Item)  
			throws EntityDoesNotExistException {
		EntityManager em = JPALauncher.getEntityManager();
		Item old = em.find(Item.class, Item.getId());
		if(old == null)
			throw new EntityDoesNotExistException("Item with Id Number = " 
					+ Item.getId() + " does not exist.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			Item = em.merge(Item);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return Item;
	}
	
	public Item delete(long ItemId)  
			throws EntityDoesNotExistException {
		EntityManager em = JPALauncher.getEntityManager();
		Item Item = em.find(Item.class, ItemId);
		if(Item == null)
			throw new EntityDoesNotExistException("Item with Id Number = " 
					+ Item.getId() + " does not exist.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.remove(Item);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return Item;
	}
	
	public List<Item> find(int from, int maxNumber) {
		EntityManager em = JPALauncher.getEntityManager();
		return em.createNamedQuery("Item.findAll", Item.class)
				.setFirstResult(from)
				.setMaxResults(maxNumber)
				.getResultList();
	}
	
	public List<Item> findAll() {
		return find(0, Integer.MAX_VALUE);
	}
	
	public long getCount(){
		EntityManager em = JPALauncher.getEntityManager();
		return em.createNamedQuery("Item.count", Long.class)
				.getSingleResult();
	}
	
}
