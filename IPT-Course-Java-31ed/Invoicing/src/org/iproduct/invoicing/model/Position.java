package org.iproduct.invoicing.model;

public class Position {
	public static final double VAT_RATE = 0.2d;
	private int position;
	private Item item;
	private double quantity;
	private double price;
	
	public Position() {
	}
	
	public Position(int position, Item item, double quantity) {
		this.position = position;
		this.item = item;
		this.quantity = quantity;
	}

	public Position(int position, Item item,
			double quantity, double price) {
		this.position = position;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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
		result = prime * result + position;
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
		if (position != other.position)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [")
				.append(", position=").append(position).append(", item=")
				.append(item).append(", quantity=").append(quantity)
				.append(", price=").append(price).append(", getTotalPrice()=")
				.append(getTotalPrice()).append(", getVAT()=").append(getVAT())
				.append(", getTotalPlusVAT()=").append(getTotalPlusVAT())
				.append("]");
		return builder.toString();
	}

	
	
	
	
	
}
