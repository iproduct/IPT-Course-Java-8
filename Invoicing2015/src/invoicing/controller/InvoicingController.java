package invoicing.controller;

import invoicing.entity.Contragent;
import invoicing.entity.Invoice;
import invoicing.entity.Item;
import invoicing.entity.Position;
import invoicing.utility.ItemNameComparator;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Formatter;

public class InvoicingController {
	public static String getFormattedInvoice(Invoice invoice) {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice Number: ");
		builder.append(String.format("%010d", invoice.getNumber()));
		builder.append("\nIssue Date: ");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		builder.append(sdf.format(invoice.getIssuingDate()));
		builder.append("\n\n===============Issuer==============");
		builder.append(String.format("\n%-12s", "ID Number:"));
		builder.append(invoice.getIssuer().getIdNumber());
		builder.append(String.format("\n%-12s", "Name:"));
		builder.append(invoice.getIssuer().getName());
		builder.append(String.format("\n%-12s", "Address:"));
		builder.append(invoice.getIssuer().getAddress());
		builder.append("\n\n===============Receiver==============");
		builder.append(String.format("\n%-12s", "ID Number:"));
		builder.append(invoice.getReceiver().getIdNumber());
		builder.append(String.format("\n%-12s", "Name:"));
		builder.append(invoice.getReceiver().getName());
		builder.append(String.format("\n%-12s", "Address:"));
		builder.append(invoice.getReceiver().getAddress());
		Position[] positions = invoice.getPositions();

		//Format positions
		builder.append("\n\n==============Ordered Goods:================");
		for(int i = 0; i < positions.length; i++){
			builder.append("\n");
			builder.append(formatPosition(positions[i]));
		}
		
		builder.append("\n\nTotal: ").append(invoice.getTotal())
			.append("\nVAT: ").append(invoice.getVat())
			.append("\nTotal with VAT: ").append(invoice.getTotalWithVat());		

		return builder.toString();
	}

	private static String formatPosition(Position p) {
		return String.format("|%3s | %-30s | " 
				+ ((p.getItem().getMeasure().equals("qt")) ? "%6.0f" : "%6.2f")
				+ " | %2s | %6.2f | %9.2f |",
			p.getNumber(), p.getItem().getName(), p.getQuantity(), 
			p.getItem().getMeasure(), p.getPrice(), p.getTotal());
	}

	public static void main(String[] args) {
		Contragent supplier = 
				new Contragent(123456789L, "Best IT Books Ltd.", "Sofia 1000");
		Contragent client1 = 
				new Contragent(8211159034L, "Ivan Petrov", "Tzar Osvoboditel, 18");
		//Construct item by id, name and price
		Item item1 = new Item(1, "Thinking in Java", 23.25);
		//Construct position by number, item and quantity
		Position position1 = new Position(1, item1, 2);
		//Construct item by id, group, name, price, and vendor
		Item item2 = new Item(2, 2, "Web Site Design", 23.30, "Web BG");
		Position position2 = new Position(2, item2, 1);
		Item item3 = new Item(3,  3, "Computer Mouse", 12.50, "Logitech");
		Position position3 = new Position(3, item1, 5);
		Position[] positions = new Position[]{position1, position2, position3};
		Item[] items = {item2, item3, item1};
		System.out.println("Before: " + Arrays.toString(items));
		Arrays.sort(items, new ItemNameComparator());
		System.out.println("After: " + Arrays.toString(items));
		//Create Invoice at last :0
		Invoice invoice1 = new Invoice(supplier, client1, positions);
//		System.out.println(getFormattedInvoice(invoice1));

	}

}
