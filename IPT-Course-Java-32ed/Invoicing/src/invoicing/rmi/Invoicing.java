package invoicing.rmi;

import java.util.Date;
import java.util.List;

import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Contragent;
import invoicing.entity.Invoice;
import invoicing.entity.Item;
import invoicing.entity.old.Position;

public interface Invoicing {
	//Item management
	Item addItem(Item item);
	List<Item> getAllProducts();
	List<Item> getProducts(int from, int max);
	Item updateItem(Item item);
	Item deleteItem(long itemId) throws EntityDoesNotExistException;
	
	//Contragent managemeent
	Contragent addContragent(Contragent contragent);
	List<Contragent> getAllContragents();
	List<Contragent> getContragents(int from, int max);
	Contragent updateContragent(Contragent Contragent);
	Contragent deleteContragent(long contragentId) throws EntityDoesNotExistException;
	
	//Invoice management
	Invoice createInvoice(Date date, Contragent issuer, 
			Contragent receiver, List<Position> positions);
	List<Invoice> getAllInvoices();
	List<Invoice> getInvoices(int from, int max);
	Invoice updateInvoice(Invoice invoice);
	Invoice deleteInvoice(long invoiceNumber) throws EntityDoesNotExistException;

}
