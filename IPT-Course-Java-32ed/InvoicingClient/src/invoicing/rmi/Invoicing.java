package invoicing.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Contragent;
import invoicing.entity.Invoice;
import invoicing.entity.Item;
import invoicing.entity.old.Position;

public interface Invoicing extends Remote{
	//Item management
	Item addItem(Item item) throws RemoteException;
	List<Item> getAllProducts() throws RemoteException;
	List<Item> getProducts(int from, int max) throws RemoteException;
	Item updateItem(Item item) throws RemoteException;
	Item deleteItem(long itemId) throws RemoteException, EntityDoesNotExistException;
	
	//Contragent managemeent
	Contragent addContragent(Contragent contragent) throws RemoteException;
	List<Contragent> getAllContragents() throws RemoteException;
	List<Contragent> getContragents(int from, int max) throws RemoteException;
	Contragent updateContragent(Contragent Contragent) throws RemoteException;
	Contragent deleteContragent(long contragentId) 
			throws RemoteException, EntityDoesNotExistException;
	
	//Invoice management
	Invoice createInvoice(Date date, Contragent issuer, 
			Contragent receiver, List<Position> positions) throws RemoteException;
	List<Invoice> getAllInvoices() throws RemoteException;
	List<Invoice> getInvoices(int from, int max) throws RemoteException;
	Invoice updateInvoice(Invoice invoice) throws RemoteException;
	Invoice deleteInvoice(long invoiceNumber) 
			throws RemoteException, EntityDoesNotExistException;
}
