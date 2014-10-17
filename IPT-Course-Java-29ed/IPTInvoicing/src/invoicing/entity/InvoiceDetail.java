package invoicing.entity;

import static invoicing.entity.Measure.PCS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InvoiceDetail implements Comparable<InvoiceDetail> {
	public static final int[] COLUMN_LENGTHS = 
		{3, 30, 9, 7, 5, 9};
	public static final int[] COLUMN_TYPES = 
		{'d', 'l', 'f', 'f', 's', 'f'};
	public static final String[] COLUMN_NAMES = 
		{"Id", "Name", "Price", "Qty", "Msr", "Total"};
	
	private Product product;
	private double quantity;
	private double price;

	public InvoiceDetail() {
	}
	
	public InvoiceDetail(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
		this.price = product.getPrice();
	}

	public InvoiceDetail(Product product, double quantity, double price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		InvoiceDetail other = (InvoiceDetail) obj;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (Double.doubleToLongBits(quantity) != Double
				.doubleToLongBits(other.quantity))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format(
		"InvoiceDetail [product=%1$s, quantity=%2$7.2f, measure=%4$5s, price=%3$9.2f]", 
		product.getName(),
		quantity, 
		price,
		product.getMeasure());
	}
	
	public static String getDetailsHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSeparatorLine());
		sb.append("\n|");
		for(int col = 0; col < COLUMN_TYPES.length; col++){
			String format = "%"+ COLUMN_LENGTHS[col] + "s";
			sb.append(String.format(format, COLUMN_NAMES[col]));
			sb.append("|");
		}
		sb.append("\n");
		sb.append(getSeparatorLine());
		return sb.toString();
	}

	public static String getSeparatorLine() {
		StringBuilder sb = new StringBuilder("+");
		for(int col = 0; col < COLUMN_LENGTHS.length; col++){
			for(int i=0; i < COLUMN_LENGTHS[col]; i++){
				sb.append("-");
			}
			sb.append("+");
		}
		return sb.toString();
	}
	
	public String getFormatted(int lineNumber) {
		StringBuilder sb = new StringBuilder("|");
		sb.append(String.format(getFormatString(0), lineNumber));
		sb.append("|");
		sb.append(String.format(getFormatString(1), product.getName()));
		sb.append("|");
		sb.append(String.format(getFormatString(2), price));
		sb.append("|");
		sb.append(String.format(getFormatString(3), quantity));
		sb.append("|");
		sb.append(String.format(getFormatString(4), product.getMeasure()));
		sb.append("|");
		sb.append(String.format(getFormatString(5), getTotal()));
		sb.append("|");
		return sb.toString();
	}

	public double getTotal() {
		return price * quantity;
	}	
	
	@Override
	public int compareTo(InvoiceDetail other) {
		return getProduct().getName().compareTo(
				other.getProduct().getName());
	}


	private String getFormatString(int colNumber) {
		String format="%s";
		switch(COLUMN_TYPES[colNumber]){
		case 'd': format = "%" + COLUMN_LENGTHS[colNumber] +"d"; break;
		case 's': format = "%" + COLUMN_LENGTHS[colNumber] +"s"; break;
		case 'l': format = "%-" + COLUMN_LENGTHS[colNumber] +"s"; break;
		case 'f': format = "%" + COLUMN_LENGTHS[colNumber] +".2f"; break;
		}
		return format;
	}
	
	
	public static void main(String[] args) {
		Product p1= new Product(2, "UML Distilled", "Prentice Hall", 15.7, 
				PCS, "Good introduction to UML.");
		Product p2= new Product(1, "Thinking in Java", "Prentice Hall", 25.5, 
				PCS, "Good introduction to Java.");
		Product p3= new Product(3, "Whiteboard Marker", "Velleda", 1.15, 
				PCS, "Red color whiteboard marker of good quality.");

		InvoiceDetail id1 = new InvoiceDetail(p3, 5);
		InvoiceDetail id2 = new InvoiceDetail(p2, 3);
		InvoiceDetail id3 = new InvoiceDetail(p1, 20);
		
		List<InvoiceDetail> list = new ArrayList<>();
		list.add(id1);
		list.add(id2);
		list.add(id3);
		
		System.out.println("Before sort:");
		printDetailsList(list);
		
//		Collections.sort(list, new Comparator<InvoiceDetail> () {
//
//			@Override
//			public int compare(InvoiceDetail pos1, InvoiceDetail pos2) {
//				return (int)(pos1.getPrice() - pos2.getPrice());
//			}
//			
//		});	
		
//		list.sort(
//				(pos1, pos2) -> (int)(pos1.getPrice() - pos2.getPrice())
//			);
		list.sort(Comparator.comparing( (invDetail) -> invDetail.getProduct().getName() ));
//		Collections.sort(list);
		System.out.println("\nAfter sort:");
		printDetailsList(list);
		
		
//		System.out.println(getDetailsHeader());
//		System.out.println(id1.getFormatted(1));
//		System.out.println(id2.getFormatted(2));
//		System.out.println(id3.getFormatted(3));
//		System.out.println(getSeparatorLine());

	}

	private static void printDetailsList(List<InvoiceDetail> positions){
//		for(int i = 0; i < positions.size(); i++){
//			System.out.println(positions.get(i));
//		}
		
//		Iterator<InvoiceDetail> it = positions.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		
		for(InvoiceDetail pos : positions){
			System.out.println(pos);
		}
	}

}
