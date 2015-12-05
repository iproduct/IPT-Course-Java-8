package invoicing.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import invoicing.entity.Book;
import invoicing.entity.Item;

public class ItemController<T extends Item> {
	public static final double VAT_RATE = .2;
	public static final String PRODUCTS_FILENAME = "products.db";
	private List<T> items = new ArrayList<>();

	public List<T> getItems() {
		return items;
	}

	public double calcualteTotalPrice(Item[] products) {
		double total = 0;
		for (Item p : products)
			total += p.getPrice();
		return total;
	}

	public double calculateVat(double price) {
		return price * VAT_RATE;
	}

	public void writeProductsToFile(String fileName) {
		Path filePath = Paths.get(fileName);
		try (ObjectOutputStream out = 
				new ObjectOutputStream(Files.newOutputStream(filePath))) {
			out.writeObject(items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readProductsfromFile(String fileName) {
		Path filePath = Paths.get(fileName);
		try (ObjectInputStream in = 
				new ObjectInputStream(Files.newInputStream(filePath))) {
			Object obj = in.readObject();
			if (obj instanceof List) {
				items = (List<T>) obj;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ItemController<Book> pc = new ItemController<>();
		// Item[] hardwareItems = new Item[] {
		// new Item(1, "Whiteboard Marker", "", .89, "Hardware"),
		// new Item(2, "USB Flash 16GB", "AData", 8.5, "Hardware"),
		// new Item(3, "Computer Mouse", "Logitech", 12.33, "Hardware")
		// };
		Book[] books = new Book[] {
				new Book(1001, "Thinking in Java", 24.5, "Bruce Eckel", "Prenctice Hall", 2011, "007-6092039389"),
				new Book(1002, "UML Distilled", 18.5, "Martin Fowler", "Prenctice Hall", 2010, "010-643589389") };

		// Serialize to file
		Arrays.asList(books).stream().forEach(book -> pc.getItems().add(book));
		System.out.println(pc.getItems());
		pc.writeProductsToFile(PRODUCTS_FILENAME);
		pc.getItems().clear();
		pc.readProductsfromFile(PRODUCTS_FILENAME);

		// Print products
		pc.getItems().stream().forEach(System.out::println);

		// Invoice<Book> bookstoreInvoice =
		// new Invoice<>("BG21332123121, Best Books Ltd., Sofia", "Ivan
		// Petrov");
		// for(Book b : books)
		// bookstoreInvoice.addPosition(b, 3);
		// System.out.println(bookstoreInvoice);

		// double total = pc.calcualteTotalPrice(hardwareItems);
		// System.out.println(total);
	}

}
