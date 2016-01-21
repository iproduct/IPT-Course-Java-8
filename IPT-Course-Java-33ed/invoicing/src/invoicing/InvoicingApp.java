package invoicing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
public class InvoicingApp {
	
	/**
	 * This method prints the invoice as text.
	 * @param inv invoice to be printed
	 * @return formatted text layout of the inoce
	 */
	public static String formatInvoice(Invoice inv){
		StringBuilder builder = new StringBuilder();
		builder.append("No.: ").append(inv.getNumber())
			.append("\nDate: ").append(inv.getDate())
			.append("\nIssuer: ").append(inv.getIssuer())
			.append("\nReceiver: ").append(inv.getReceiver());

		builder.append(
				String.format("\n\n| %3s | %-30s | %8s | %8s | %7s | %10s |", 
					"No.", "Product", "Price", "Quantity", "Measure", "Total")
			);

		int n = 1;
		for(Position pos : inv.getPositions()){
			builder.append(
				String.format("\n| %3d | %-30s | %8.2f | %8.2f | %7s | %10.2f |",
					n++, pos.getProduct().getName(), 
					pos.getPrice(), pos.getQuantity(), "PCS",
					pos.getTotalPrice())
			);
		}
			
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
		
		Contragent[] contrag
		
		//Create invoice
		Invoice invoice = new Invoice("ABC Ltd.", "Ivan Petrov", positions);
		System.out.println(formatInvoice(invoice));
		
	}

}
