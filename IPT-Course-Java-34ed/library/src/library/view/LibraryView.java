package library.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import library.controller.LibraryController;
import library.model.Book;

public class LibraryView {
	private LibraryController controller;
	
	public LibraryView(LibraryController controller){
		this.controller = controller;
	}
	
	public String formatBookList(){
		return formatBookList(null);
	}
		
	public String formatBookList(Comparator<Book> comparator){
		List<Book> books = controller.getBooks();
		if(comparator == null)
			Collections.sort(books);
		else
			Collections.sort(books, comparator);
		StringBuilder builder = new StringBuilder();
		for(Book b: books){
			StringBuilder authors = new StringBuilder();
			for(String author: b.getAuthors()) {
				authors.append(author + " ");
			}
			builder.append(String.format(
					"| %5d | %-30.30s | %-30.30s | %4d |\n", 
				b.getId(), b.getTitle(), authors.toString(), 
				b.getYear()));
		}
		return builder.toString();
	};
}
