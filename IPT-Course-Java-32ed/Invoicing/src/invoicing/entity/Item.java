package invoicing.entity;

import java.io.Serializable;

public class Item implements Serializable{
	public static final String[] FIELD_NAMES = {
			"ID", "Name", "Vendor", "Price", "Group"
	};
	private long id;
	private String name;
	private String vendor;
	private double price;
	private String group = "Others";

	public Item() {
	}

	public Item(long id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
	}
	
	public Item(long id, String name, String vendor, double price, String group) {
		this.id = id;
		this.name = name;
		this.vendor = vendor;
		this.price = price;
		this.group = group;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
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
		Item p1 = new Item(1, "Whiteboard Marker", "Hardware"),
				p2 = new Item(2, "USB Flash 16GB", "AData", 8.5, "Hardware");
		
		System.out.println(p1);
		System.out.println(p2);
		
	}

}
