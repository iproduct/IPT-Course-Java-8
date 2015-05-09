package invoicing.controller;

import invoicing.entity.BookItem;
import invoicing.entity.Contragent;
import invoicing.entity.Invoice;
import invoicing.entity.Item;
import invoicing.entity.Position;
import invoicing.utility.ItemNameComparator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoicingController {
	public static <T extends Item> String getFormattedInvoice(Invoice<T> invoice) {
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
		List<Position<T>> positions = invoice.getPositions();

		//Format positions
		builder.append("\n\n==============Ordered Goods:================");
		for(int i = 0; i < positions.size(); i++){
			builder.append("\n");
			builder.append(formatPosition(positions.get(i)));
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
		Position<Item> position1 = new Position<>(1, item1, 2);
		//Construct item by id, group, name, price, and vendor
		Item item2 = new Item(2, 2, "Web Site Design", 23.30, "Web BG");
		Position<Item> position2 = new Position<>(2, item2, 1);
		Item item3 = new Item(3,  3, "Computer Mouse", 12.50, "Logitech");
		Position<Item> position3 = new Position<>(3, item1, 5);
		List<Position<Item>> positions = new ArrayList<>();
		positions.add(position1);
		positions.add(position2);
		positions.add(position3);
		Item[] items = {item2, item3, item1};
		System.out.println("Before: " + Arrays.toString(items));
		Arrays.sort(items, new ItemNameComparator());
		System.out.println("After: " + Arrays.toString(items));
		//Create Invoice at last :0
		
		List<BookItem> books = new ArrayList<>();
		books.add(new BookItem(1, "Thinking in Java", 15.50, "Bruce Eckel", 
				"Prentice Hall", 2006));
		books.add(new BookItem(2, "UML Distilled", 12.80, "Martin Fowler", 
				"Soft Press", 2005));
		books.add(new BookItem(3, "Effective Java", 18.50, "Joshua Boch", 
				"Addisson Wesley", 2008));
		books.add(new BookItem(4, "Java Concurrency in Practice", 25.30, "Brian Goetz", 
				"Addisson Wesley", 2006));
		List<Position<BookItem>> bookPositions = new ArrayList<>();
		
		for(BookItem bookItem: books){
			bookPositions.add(new Position<>(1, bookItem, 3));
		}
		
		Invoice<BookItem> invoice1 = new Invoice<>(supplier, client1, bookPositions);
		System.out.println(getFormattedInvoice(invoice1));

	}

}
