package invoicing.entity.old;

public class Book extends Item {
	private String author;
	private String publisher;
	private int year;
	private String isbn;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(long id, String name, double price, 
			String author, String publisher, int year, String isbn) {
		super(id, name, publisher, price, "Books");
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.isbn = isbn;
	}
	public Book(long id, String name, String author, String publisher, int year) {
		super(id, name, "Books");
		this.author = author;
		this.publisher = publisher;
		this.year = year;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [author=").append(author).append(", publisher=").append(publisher).append(", year=")
				.append(year).append(", isbn=").append(isbn).append(", getId()=").append(getId()).append(", getName()=")
				.append(getName()).append(", getVendor()=").append(getVendor()).append(", getPrice()=")
				.append(getPrice()).append("]");
		return builder.toString();
	}
	
	
	
}
