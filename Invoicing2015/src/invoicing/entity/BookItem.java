package invoicing.entity;
import static invoicing.utility.ItemCathegory.*;

public class BookItem extends Item {
	private String author;
	private int year;
	public BookItem(long id, String name, double price,
			double vatRate, String vendor, String measure,
			String author, String publisher, int year) {
		super(id, BOOK, name, price, vatRate, publisher, measure);
		this.author = author;
		this.year = year;
		
	}
	public BookItem(long id, String name, double price,
			String author, String publisher, int year) {
		super(id, BOOK, name, price, publisher);
		this.author = author;
		this.year = year;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookItem [author=");
		builder.append(author);
		builder.append(", year=");
		builder.append(year);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getGroup()=");
		builder.append(getGroup());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getPrice()=");
		builder.append(getPrice());
		builder.append(", getVatRate()=");
		builder.append(getVatRate());
		builder.append(", getVendor()=");
		builder.append(getVendor());
		builder.append(", getMeasure()=");
		builder.append(getMeasure());
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	
}
