package invoicing.entity;

public class Position<T extends Item> {
	private int number;
	private T item;
	private double quantity = 1;
	private double price;
	public Position() {
	}
	public Position(int number, T item, double quantity) {
		this.number = number;
		this.item = item;
		this.quantity = quantity;
		price = item.getPrice();
	}
	public Position(int number, T item, double quantity, double price) {
		this.number = number;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + number;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [number=").append(number).append(", item=").append(item).append(", quantity=")
				.append(quantity).append(", price=").append(price).append("]");
		return builder.toString();
	}
	
	
	
	
}


