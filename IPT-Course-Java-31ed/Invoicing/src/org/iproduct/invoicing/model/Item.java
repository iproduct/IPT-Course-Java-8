package org.iproduct.invoicing.model;

import static org.iproduct.invoicing.model.Measure.*;

public class Item implements Comparable<Item>{
	private static long productCount = 0;
	private long id = productCount++;
	private ItemCategory category;
	private String name;
	private String supplier;
	private double price;
	private Measure measure = PCS;
	
	public Item() {
	}

	public Item(ItemCategory category, String name) {
		this.category = category;
		this.name = name;
	}

	public Item(ItemCategory category, String name, String supplier,
			double price, Measure measure) {
		this.category = category;
		this.name = name;
		this.supplier = supplier;
		this.price = price;
		this.measure = measure;
	}

	public static long getProductCount() {
		return productCount;
	}

	public static void setProductCount(long productCount) {
		Item.productCount = productCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
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
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=").append(id).append(", category=")
				.append(category).append(", name=").append(name)
				.append(", supplier=").append(supplier).append(", price=")
				.append(price).append(", measure=").append(measure).append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}
	
	
	
	
}
