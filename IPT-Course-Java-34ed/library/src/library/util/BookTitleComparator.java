package library.util;

import java.util.Comparator;

import library.model.Book;

public class BookTitleComparator implements Comparator<Book> {

	@Override
	public int compare(Book b1, Book b2) {
		return b1.getTitle().compareToIgnoreCase(b2.getTitle());
	}

}
