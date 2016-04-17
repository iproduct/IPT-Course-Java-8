package library.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import library.model.Book;
import library.view.LibraryView;

public class LibraryController {
	private Map<String, Book> books = new HashMap<>();
	
	public void addBook(String inventaryNumber, Book book){
		books.put(inventaryNumber, book);
	}
	
	public Map<String, Book> getBooksMap(){
		return books;
	}
	
	public List<Book> getBooks(){
		return new ArrayList<>(books.values());
	}

	public static void main(String[] args) {
		List<String> authors = new LinkedList<String>();
		authors.add("Bruce Eckel");
		Book b1 = new Book("Thinking in Java 4ed.", authors, 
				"Prentince Hall", 2013);
		Book b2 = new Book("Effective Java", 
				Arrays.asList(new String[] {"Joshua Bloch"}), 
				"Wesley", 2008);	
		Book b3 = new Book("Design Patterns in Java", 
				Arrays.asList(
					new String[] {"Steven Metsker", "William C. Wake"}), 
					"Wesley", 2006);
		Book b4 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", 
				Arrays.asList(new String[] {
					" Erich Gamma", "Ralph Johnson", "John Vlissides",
					"Richard Helm"}), 
					"Wesley", 2008);
		Book b5 = new Book("Applied Java Patterns", 
				Arrays.asList(new String[] {
						"Stephen Stelting", "Olav Maassen"}), 
					"Sun", 2009);
			
		//Library controller
		LibraryController library = new LibraryController();
		library.addBook("IT000000001", b1);
		library.addBook("IT000000002", b2);
		library.addBook("IT000000003", b3);
		library.addBook("IT000000004", b4);
		library.addBook("IT000000005", b5);
//		System.out.println(library.getBooks());
//		System.out.println(library.getBooksCollection());
		
		LibraryView view = new LibraryView(library);
		System.out.println(view.formatBookList(
			(Book book1, Book book2) -> {
				String a1 = book1.getAuthors().get(0);
				String a2 = book2.getAuthors().get(0);
				a1 = (a1 != null) ? a1 : "";
				a2 = (a2 != null) ? a2 : "";
				String[] names1 = a1.split(" ");
				String lastName1 = (names1.length > 0) ? names1[names1.length-1]: "";
				String[] names2 = a2.split(" ");
				String lastName2 = (names2.length > 0) ? names2[names2.length-1]: "";
				return lastName1.compareToIgnoreCase(lastName2);
			}
		));
		
		//Streaming API demo
		List<String> titles = library.getBooks().parallelStream()
			.sorted(Comparator.comparing(Book::getYear))
			.filter(book -> book.getYear() > 2008)
			.map(book -> {System.out.println(book); return book;})
			.map(Book::getTitle)
			.collect(Collectors.toList());
		System.out.println("\nTitles only:");
		titles.stream().forEach(t -> System.out.println(t));
		
	}

}
