package invoicing.controller;

import invoicing.model.Company;
import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Position;
import invoicing.model.Product;

/**
 * Top level invoicing application class.
 * @author Trayan Iliev
 * @version 1.0
 * @since 1.0
 * @see org.iproduct.invoicing.model.Invoice
 */
public class InvoiceRegister {
	private Product[] products = new Product[0];
	private Contragent[] contragents = new Contragent[0];
	private Invoice[] invoices= new Invoice[0];
	
	public void initialize(Product[] products, Contragent[] contragents){
		this.products = products;
		this.contragents = contragents;
	}
	
	/**
	 * This method prints the invoice as text.
	 * @param inv invoice to be printed
	 * @return formatted text layout of the inoce
	 */
	public static String formatInvoice(Invoice inv){
		StringBuilder builder = new StringBuilder();
		builder.append("No.: ").append(inv.getNumber())
			.append("\nDate: ").append(inv.getDate())
			.append("\n\nIssuer: ").append(inv.getIssuer())
			.append("\n\nReceiver: ").append(inv.getReceiver());

		builder.append(
				String.format("\n\n| %3s | %-30s | %8s | %8s | %7s | %10s |", 
					"No.", "Product", "Price", "Quantity", "Measure", "Total")
			);

		int n = 1;
		for(Position pos : inv.getPositions()){
			builder.append(
				String.format("\n| %3d | %-30.30s | %8.2f | %8.2f | %7s | %10.2f |",
					n++, pos.getProduct().getName(), 
					pos.getPrice(), pos.getQuantity(), "PCS",
					pos.getTotalPrice())
			);
		}
		
		builder.append(String.format("\n\n%66sTotal: %10.2f", "", inv.getTotal()))
		.append(String.format("\n%68sVAT: %10.2f", "", inv.getVAT()))
		.append(String.format("\n%84s", "-----------------"))
		.append(String.format("\n\n%53sTotal VAT included: %10.2f", "", inv.getTotalPlusVAT()));
			
			
		return builder.toString();
	}

	public static void main(String[] args) {
		Product[] products = {
			new Product("BK001", "Thinking in Java", 15.7),
			new Product("HD001", "Logitech Optical Mouse", 8.75),
			new Product("SF001", "Photoshop CC", 2000),
			new Product("HD002", "Raspberry Pi 2", 80),
			new Product("SV001", "Raspbian Installation", 20),
			new Product("HD003", "3dIMU - 3D Acceelerometr, Gyrospone & Comapss", 35)
		};
		
		Position[] positions = {
				new Position(products[0]), 
				new Position(products[1], 5), 
				new Position(products[3], 10)
				};
		
		Contragent[] contragents = {			
				new Contragent(122222222, "ABC Ltd.", "Plovdiv"),
				new Contragent(1234567891, "Ivan Petrov", "Sofia 1000")
		};
		
		//Create invoice
		
		Contragent issuer = new Company();
		issuer.input(System.in);
		
		Contragent receiver = new Contragent();
		receiver.input(System.in);
		
		Invoice invoice = new Invoice(issuer, receiver, positions);
		System.out.println(formatInvoice(invoice));
	}

}
