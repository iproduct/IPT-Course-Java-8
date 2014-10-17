package invoicing.entity;

import static invoicing.entity.Measure.*;

public class Product {
	private long pid;
	private String name;
	private String supplier;
	private double price;
	private String description;
	private Measure measure;
	
	public Product() {
	}

	
	public Product(long pid) {
		this.pid = pid;
	}

	public Product(long pid, String name, String supplier, double price) {
		this.pid = pid;
		this.name = name;
		this.supplier = supplier;
		this.price = price;
	}

	public Product(long pid, String name, String supplier, double price,
			Measure measure, String description) {
		this.pid = pid;
		this.name = name;
		this.supplier = supplier;
		this.price = price;
		this.measure = measure;
		this.description = description;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (pid ^ (pid >>> 32));
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
		Product other = (Product) obj;
		if (pid != other.pid)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [pid=").append(pid).append(", name=")
				.append(name).append(", supplier=").append(supplier)
				.append(", price=").append(price).append(", measure=")
				.append(measure).append(", description=")
				.append(description).append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Product p1= new Product(1, "Thinking in Java", "Prentice Hall", 25.5, 
				PCS, "Good introduction to Java.");
		System.out.println(p1);

	}

}
