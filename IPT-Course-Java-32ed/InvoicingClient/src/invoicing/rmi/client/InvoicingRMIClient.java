package invoicing.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import invoicing.rmi.Invoicing;

public class InvoicingRMIClient {
	public static final String INVOICING_SERVICE_NAME = "Invoicing";
	
	public static Invoicing connectToServer(String serviceUrl) 
			throws RemoteException, NotBoundException, MalformedURLException {
//		Registry registry = LocateRegistry.getRegistry(host, port);
//		Invoicing server = (Invoicing) registry.lookup(
//				INVOICING_SERVICE_NAME);
		Invoicing server = (Invoicing) Naming.lookup(serviceUrl);
		return server;
		
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
		Invoicing server = connectToServer("//localhost:2016/Invoicing");
		System.out.println(server.getAllProducts());
	}
}
