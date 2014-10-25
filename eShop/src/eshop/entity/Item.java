package eshop.entity;

public class Item {
	private long id;
	private String name;
	private String manufacturer;
	private String category;
	private String description;
	private double price;
	private int stockQuantity;

	/**
	 * 
	 */
	public Item() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param manufacturer
	 */
	public Item(long id, String name, String manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
	}

	/**
	 * @param id
	 * @param name
	 * @param manufacturer
	 * @param category
	 * @param description
	 * @param price
	 * @param stockQuantity
	 */
	public Item(long id, String name, String manufacturer, String category,
			String description, double price, int stockQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the stockQuantity
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * @param stockQuantity
	 *            the stockQuantity to set
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("|%8d |%25s |%15s |%15s |%7.2f |%5d|",
						id, name, manufacturer, category, price, stockQuantity);
	}

	public static void main(String[] args) {
		Item i1 = new Item(1, "Computer Mouse", "Logitech", "Accessoaries",
				"High quality optical mouse", 12.5, 20);
		Item i2 = new Item(2, "Motherboard", "ASUS", "Motherboards",
				"AMD Athlon II x4 A03 motherboard", 125.7, 15);
		System.out.println(i1);
		System.out.println(i2);
	}

}
