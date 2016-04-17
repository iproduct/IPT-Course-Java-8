package library.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Comparable<Book>{
	private static long count = 0;
	private long id = ++count;
	private String title;
	private List<String> authors = new ArrayList<>();
	private String publisher;
	private String isbn;
	private int year;

	public Book() {
	}
	
	public Book(String title, List<String> authors, String publisher, int year) {
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}

	public Book(long id, String title, List<String> authors, String publisher, String isbn, int year) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.isbn = isbn;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=").append(id).append(", title=")
			.append(title).append(", authors=").append(authors)
			.append(", publisher=").append(publisher).append(", isbn=")
			.append(isbn).append(", year=").append(year).append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Book other) {
		return Long.compare(getId(), other.getId());
	}

	public static void main(String[] args) {
		List<String> authors = new ArrayList<String>();
		authors.add("Bruce Eckel");
		Book b1 = new Book("Thinking in Java 4ed.", authors, 
				"Prentince Hall", 2013);
		Book b2 = new Book("Effective Java", 
			Arrays.asList(new String[] {"Joshua Bloch"}), 
			"Wesley", 2008);
		System.out.println(b1);
		System.out.println(b2);

	}

}
