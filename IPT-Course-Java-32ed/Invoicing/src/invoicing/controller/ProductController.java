package invoicing.controller;

import invoicing.entity.Book;
import invoicing.entity.Invoice;
import invoicing.entity.Item;

public class ProductController {
	public static final double VAT_RATE = .2;
	public double calcualteTotalPrice(Item[] products) {
		double total = 0;
		for(Item p: products)
			total += p.getPrice(); 
		return total;
	}
	
	public double calculateVat(double price){
		return price * VAT_RATE;
	}
	
	public static void main(String[] args) {
		ProductController pc = new ProductController();
//		Item[] hardwareItems = new Item[] {
//				new Item(1, "Whiteboard Marker", "", .89, "Hardware"),
//				new Item(2, "USB Flash 16GB", "AData", 8.5, "Hardware"),
//				new Item(3, "Computer Mouse", "Logitech", 12.33, "Hardware")
//		};
		Book[] books = new Book[] {
				new Book(1001, "Thinking in Java", 24.5, 
						"Bruce Eckel", "Prenctice Hall", 2011, "007-6092039389"),
				new Book(1002, "UML Distilled", 18.5, 
						"Martin Fowler", "Prenctice Hall", 2010, "010-643589389")
		};
		Invoice<Book> bookstoreInvoice = 
				new Invoice<>("BG21332123121, Best Books Ltd., Sofia", "Ivan Petrov");
		for(Book b : books)
			bookstoreInvoice.addPosition(b, 3);
		System.out.println(bookstoreInvoice);
		
//		double total = pc.calcualteTotalPrice(hardwareItems);
//		System.out.println(total);
	}

}
