package invoicing.entity;

import java.util.ArrayList;
import java.util.List;

public class Invoice <T extends Item> {
	private String issuer, receiver;
	private List<Position<T>> positions;
	
	public Invoice() {
	}

	public Invoice(String issuer, String receiver) {
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = new ArrayList<>();
	}
	
	public Invoice(String issuer, String receiver, List<Position<T>> positions) {
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}
	
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public List<Position<T>> getPositions() {
		return positions;
	}

	public void setPositions(List<Position<T>> positions) {
		this.positions = positions;
	}

	public void addPosition(T item, double quantity){
		addPosition(item, quantity, item.getPrice());
	}
	
	public void addPosition(T item, double quantity, double price){
		Position p = new Position<Item>(positions.size() + 1 , item, quantity);
		positions.add(p);
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nIssuer: ").append(issuer)
			.append("\nReceiver=").append(receiver);
		for(Position<T> p : getPositions())
			builder.append(String.format("\n| %4d | %30s | %7.2f | %7.2f | %9.2f |", 
					p.getNumber(), p.getItem().getName(), p.getPrice(), 
					p.getQuantity(), p.getPrice() * p.getQuantity()));
		
		builder.append("\nPrice: ").append(getPrice())
			   .append("\nVAT:   ").append(getVAT())
			   .append("\nTotal: ").append(getTotal());
		return builder.toString();
	}

	private double getTotal() {
		return getPrice() + getVAT();
	}

	private double getVAT() {
		return 0.2 * getPrice();
	}

	private double getPrice() {
		double sum = 0;
		for(Position<T> item: positions){
			sum += item.getPrice();
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
