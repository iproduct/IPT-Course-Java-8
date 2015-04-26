package invoicing.controller;

import invoicing.entity.Contragent;
import invoicing.entity.Invoice;
import invoicing.entity.Item;
import invoicing.entity.Position;

import java.text.SimpleDateFormat;
import java.util.Formatter;

public class InvoicingController {
	public static String getFormattedInvoice(Invoice invoice) {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice Number: ");
		Formatter fmt = new Formatter();
		builder.append(fmt.format("%010d", invoice.getNumber()));
		builder.append("\nIssue Date: ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		builder.append(sdf.format(invoice.getIssuingDate()));
		builder.append("\n issuer=");
		builder.append(invoice.getIssuer());
		builder.append(", receiver=");
//		builder.append(invoice.receiver);
//		builder.append(", positions=");
//		builder.append(Arrays.toString(positions));
//		builder.append(", issuedBy=");
//		builder.append(issuedBy);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Contragent supplier = 
				new Contragent(123456789L, "Best IT Books Ltd.", "Sofia 1000");
		Contragent client1 = 
				new Contragent(8211159034L, "Ivan Petrov", "Tzar Osvoboditel, 18");
		//Construct item by id, name and price
		Item item1 = new Item(1, "Thinking in Java", 23);
		//Construct position by number, item and quantity
		Position position1 = new Position(1, item1, 2);
		//Construct item by id, group, name, price, and vendor
		Item item2 = new Item(2, 2, "Web Site Design", 23, "Web BG");
		Position position2 = new Position(2, item2, 1);
		Item item3 = new Item(3,  3, "Computer Mouse", 12.50, "Logitech");
		Position position3 = new Position(3, item1, 5);
		
		//Create Invoice at last :0
		Invoice invoice1 = new Invoice(supplier, client1, 
				new Position[]{position1, position2, position3});
		System.out.println(getFormattedInvoice(invoice1));

	}

}
