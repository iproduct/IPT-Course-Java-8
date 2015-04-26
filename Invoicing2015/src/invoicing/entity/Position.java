package invoicing.entity;

public class Position {
	private int number;
	private Item item;
	private double quantity;
	private double price;
	public Position() {
	}
	
	public Position(int number, Item item, double quantity) {
		this.number = number;
		this.item = item;
		this.quantity = quantity;
	}
	
	public Position(int number, Item item, double quantity, double price) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + number;
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
		builder.append("Position [number=");
		builder.append(number);
		builder.append(", item=");
		builder.append(item);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", price=");
		builder.append(price);
		builder.append(", measure=");
		builder.append("]");
		return builder.toString();
	}
}
