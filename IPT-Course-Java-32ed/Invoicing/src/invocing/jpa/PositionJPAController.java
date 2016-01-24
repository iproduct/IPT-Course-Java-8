package invocing.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import invocing.jpa.exceptions.EntityAlreadyExistsException;
import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Item;
import invoicing.entity.Position;

public class PositionJPAController{
	
	public Position create(Position position)  
			throws EntityAlreadyExistsException {
		EntityManager em = JPALauncher.getEntityManager();
		if(em.find(Position.class, position.getId()) != null)
			throw new EntityAlreadyExistsException("Position with Id Number = " 
					+ position.getId() + " already exists.");
		Item item = em.find(Item.class, position.getItem().getId());
		if(item == null) 
			item = JPALauncher.getItemController().create(position.getItem());
			
		item.addPosition(position);
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.persist(position);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return position;
	}
	
	public Position update(Position position)  
			throws EntityDoesNotExistException {
		EntityManager em = JPALauncher.getEntityManager();
		Position old = em.find(Position.class, position.getId());
		if(old == null)
			throw new EntityDoesNotExistException("Position with Id Number = " 
					+ position.getId() + " does not exist.");
		Item item = em.find(Item.class, position.getItem().getId());
		Item oldItem = em.find(Item.class, old.getItem().getId());
		if(item == null)
			try {
				item = JPALauncher.getItemController().create(position.getItem());
			} catch (EntityAlreadyExistsException e1) {
				e1.printStackTrace();
			}
		oldItem.removePosition(position);
		item.addPosition(position);
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			position = em.merge(position);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return position;
	}
	
	public Position delete(long PositionId)  
			throws EntityDoesNotExistException {
		EntityManager em = JPALauncher.getEntityManager();
		Position Position = em.find(Position.class, PositionId);
		if(Position == null)
			throw new EntityDoesNotExistException("Position with Id Number = " 
					+ Position.getId() + " does not exist.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.remove(Position);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		return Position;
	}
	
	public List<Position> find(int from, int maxNumber) {
		EntityManager em = JPALauncher.getEntityManager();
		return em.createNamedQuery("Position.findAll", Position.class)
				.setFirstResult(from)
				.setMaxResults(maxNumber)
				.getResultList();
	}
	
	public List<Position> findAll() {
		return find(0, Integer.MAX_VALUE);
	}
	
	public long getCount(){
		EntityManager em = JPALauncher.getEntityManager();
		return em.createNamedQuery("Position.count", Long.class)
				.getSingleResult();
	}
	
}
