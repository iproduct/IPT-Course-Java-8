/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoiceServer is part of multi-tier client-server invoicing application 
* using JPA, Swing and RMI technologies.
* 
* Copyright (c) 2012 - 2016 IPT - Intellectual Products & Technologies Ltd.
* All rights reserved.
*
* E-mail: office@iproduct.org
* Web: http://iproduct.org/
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License (the "License")
* as published by the Free Software Foundation version 2 of the License.
* You may not use this file except in compliance with the License. You can
* obtain a copy of the License at root directory of this project in file
* LICENSE.txt.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* When distributing the software, include this COPYRIGHT & LICENSE HEADER
* in each file, and include the License file LICENSE.txt in the root directory
* of your distributable.
*
* GPL Classpath Exception:
* IPT - Intellectual Products & Technologies (IPT) designates this particular
* file as subject to the "Classpath" exception as provided by IPT in
* the GPL Version 2 License file that accompanies this code.
*
* In case you modify this file,
* please add the appropriate notice below the existing Copyright notices,
* with the fields enclosed in brackets {} replaced by your own identification:
* "Portions Copyright (c) {year} {name of copyright owner}"
*/

package org.iproduct.invoicing.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.iproduct.invoicing.entity.Item;
import org.iproduct.invoicing.entity.Position;
import org.iproduct.invoicing.jpa.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.jpa.exceptions.EntityDoesNotExistException;

public class PositionJPAController{
	
	public Position create(Position position)  
			throws EntityAlreadyExistsException {
		EntityManager em = JPAMainController.getEntityManager();
		if(em.find(Position.class, position.getId()) != null)
			throw new EntityAlreadyExistsException("Position with Id Number = " 
					+ position.getId() + " already exists.");
		Item item = em.find(Item.class, position.getItem().getId());
		if(item == null) 
			item = JPAMainController.getItemController().create(position.getItem());
			
		item.addPosition(position);
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.persist(position);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		em.close();
		return position;
	}
	
	public Position update(Position position)  
			throws EntityDoesNotExistException {
		EntityManager em = JPAMainController.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Position old = em.find(Position.class, position.getId());
		if(old == null)
			throw new EntityDoesNotExistException("Position with Id Number = " 
					+ position.getId() + " does not exist.");
		Item item = position.getItem();
		Item oldItem = em.find(Item.class, old.getItem().getId());
		if(item == null)
			try {
				item = JPAMainController.getItemController().create(position.getItem());
			} catch (EntityAlreadyExistsException e1) {
				e1.printStackTrace();
			}
		oldItem.removePosition(position);
		item.addPosition(position);
		
		try{
			position = em.merge(position);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		em.close();
		return position;
	}
	
	public Position delete(long positionId)  
			throws EntityDoesNotExistException {
		EntityManager em = JPAMainController.getEntityManager();
		Position position = em.find(Position.class, positionId);
		if(position == null)
			throw new EntityDoesNotExistException("Position with Id Number = " 
					+ position.getId() + " does not exist.");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try{
			em.remove(position);
			transaction.commit();
		} catch (PersistenceException e) {
			transaction.rollback();
		}
		em.close();
		return position;
	}
	
	public List<Position> find(long invoicenumber, int from, int maxNumber) {
		EntityManager em = JPAMainController.getEntityManager();
		List<Position> result = em.createNamedQuery("Position.findAll", Position.class)
				.setFirstResult(from)
				.setMaxResults(maxNumber)
				.getResultList();
		em.close();
		return result;
	}
	
	public List<Position> findAll(long invoicenumber) {
		return find(invoicenumber, 0, Integer.MAX_VALUE);
	}
	
	public long getCount(){
		EntityManager em = JPAMainController.getEntityManager();
		long result = em.createNamedQuery("Position.count", Long.class)
				.getSingleResult();
		em.close();
		return result;
	}
	
}
