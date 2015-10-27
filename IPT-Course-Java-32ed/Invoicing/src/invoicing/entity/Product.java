package invoicing.entity;

public class Product {
	private long id;
	private String name;
	private String vendor;
	private double price;

	public Product() {
	}

	public Product(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Product(long id, String name, String vendor, double price) {
		this.id = id;
		this.name = name;
		this.vendor = vendor;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=").append(id).append(", name=").append(name)
				.append(", vendor=").append(vendor)
				.append(", price=").append(price).append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Product p1 = new Product(1, "Whiteboard Marker"),
				p2 = new Product(2, "USB Flash 16GB", "AData", 8.5);
		
		System.out.println(p1);
		System.out.println(p2);
		
	}

}
