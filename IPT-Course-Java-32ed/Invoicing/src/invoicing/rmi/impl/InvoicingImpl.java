package invoicing.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import invocing.jpa.exceptions.EntityDoesNotExistException;
import invoicing.entity.Contragent;
import invoicing.entity.Invoice;
import invoicing.entity.Item;
import invoicing.entity.old.Position;
import invoicing.rmi.Invoicing;

public class InvoicingImpl extends UnicastRemoteObject implements Invoicing {
	
	public static final int RMI_REGISTRY_PORT = 2016;
	private static final long serialVersionUID = 1L;

	public InvoicingImpl() throws RemoteException {
		super();
	}

	public InvoicingImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	public InvoicingImpl(int port) throws RemoteException {
		super(port);
	}

	@Override
	public Item addItem(Item item) throws RemoteException {
		return null;
	}

	@Override
	public List<Item> getAllProducts() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getProducts(int from, int max) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item updateItem(Item item) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item deleteItem(long itemId) throws RemoteException, EntityDoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contragent addContragent(Contragent contragent) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contragent> getAllContragents() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contragent> getContragents(int from, int max) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contragent updateContragent(Contragent Contragent) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contragent deleteContragent(long contragentId) throws RemoteException, EntityDoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice createInvoice(Date date, Contragent issuer, Contragent receiver, List<Position> positions)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> getAllInvoices() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Invoice> getInvoices(int from, int max) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice deleteInvoice(long invoiceNumber) throws RemoteException, EntityDoesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		try {
			InvoicingImpl invoicingImpl = new InvoicingImpl();
			System.setSecurityManager(new SecurityManager());
			Registry registry = LocateRegistry.createRegistry(RMI_REGISTRY_PORT);
			System.out.println("RMI registry started on port: " + RMI_REGISTRY_PORT);
			UnicastRemoteObject.unexportObject(invoicingImpl, true);
			Invoicing stub = 
		          (Invoicing) UnicastRemoteObject.exportObject(invoicingImpl, 0);
		    registry.rebind("//localhost:2016/Invoicing", stub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
