package org.iproduct.invoicing.model;

public class Position {
	public static final double VAT_RATE = 0.2d;
	private Item item;
	private double quantity;
	private double price;
	
	public Position() {
	}
	
	public Position( Item item) {
		this.item = item;
		this.quantity = 1;
		this.price = item.getPrice();
	}

	public Position( Item item, double quantity) {
		this.item = item;
		this.quantity = quantity;
		this.price = item.getPrice();
	}

	public Position(Item item,
			double quantity, double price) {
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotalPrice(){
		return quantity * price;
	}
	
	public double getVAT() {
		return getTotalPrice() * VAT_RATE;
	}
	
	public double getTotalPlusVAT() {
		return getTotalPrice()  +  getVAT();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Position other = (Position) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(quantity) != Double
				.doubleToLongBits(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [item=").append(item).append(", quantity=")
				.append(quantity).append(", price=").append(price).append("]");
		return builder.toString();
	}

	
}
