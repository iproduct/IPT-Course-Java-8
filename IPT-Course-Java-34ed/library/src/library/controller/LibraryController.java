package library.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.model.Book;
import library.view.LibraryView;

public class LibraryController {
	private Map<String, Book> books = new HashMap<>();
	
	public void addBook(String inventaryNumber, Book book){
		books.put(inventaryNumber, book);
	}
	
	public Map<String, Book> getBooks(){
		return books;
	}
	
	public Collection<Book> getBooksCollection(){
		return books.values();
	}
	
	

	public static void main(String[] args) {
		List<String> authors = new ArrayList<String>();
		authors.add("Bruce Eckel");
		Book b1 = new Book("Thinking in Java 4ed.", authors, 
				"Prentince Hall", 2013);
		Book b2 = new Book("Effective Java", 
			Arrays.asList(new String[] {"Joshua Bloch"}), 
			"Wesley", 2008);
		
		//Library controller
		LibraryController library = new LibraryController();
		library.addBook("IT000000001", b1);
		library.addBook("IT000000002", b2);
//		System.out.println(library.getBooks());
//		System.out.println(library.getBooksCollection());
		
		LibraryView view = new LibraryView(library);
		System.out.println(view.formatBookList());
	}

}
