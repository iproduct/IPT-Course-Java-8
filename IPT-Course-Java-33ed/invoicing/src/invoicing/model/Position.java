package invoicing.model;

public class Position {
	public static final double VAT_RATE = 0.2d;
	private Product product;
	private double quantity;
	private double price;
	
	public Position() {
	}
	
	public Position( Product product) {
		this.product = product;
		this.quantity = 1;
		this.price = product.getPrice();
	}

	public Position( Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
		this.price = product.getPrice();
	}

	public Position(Product product,
			double quantity, double price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product item) {
		this.product = item;
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
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
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
		builder.append("Position [item=").append(product).append(", quantity=")
				.append(quantity).append(", price=").append(price).append("]");
		return builder.toString();
	}

	
}
