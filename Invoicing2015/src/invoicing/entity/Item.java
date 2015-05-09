package invoicing.entity;

public class Item implements Comparable<Item>{
	private long id;
	private int group = 1;
	private String name;
	private double price;
	private double vatRate = 0.2;
	private String vendor;
	private String measure = "qt";
	
	public Item() {
		super();
	}

	public Item(long id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Item(long id, int group, String name, double price, String vendor) {
		super();
		this.id = id;
		this.group = group;
		this.name = name;
		this.price = price;
		this.vendor = vendor;
	}

	public Item(long id, int group, String name, double price, double vatRate,
			String vendor, String measure) {
		super();
		this.id = id;
		this.group = group;
		this.name = name;
		this.price = price;
		this.vatRate = vatRate;
		this.vendor = vendor;
		this.measure = measure;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVatRate() {
		return vatRate;
	}

	public void setVatRate(double vatRate) {
		this.vatRate = vatRate;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
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
		builder.append("Item [id=");
		builder.append(id);
		builder.append(", group=");
		builder.append(group);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append(", vatRate=");
		builder.append(vatRate);
		builder.append(", vendor=");
		builder.append(vendor);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Item item) {
		return (this.id < item.id)? -1: (this.id == item.id)? 0 : 1;
	}
	
	
	
	
	
	
	
}
