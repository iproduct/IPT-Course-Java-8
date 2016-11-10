package invoices;

import java.io.InputStream;
import java.util.Scanner;

public class Product {
	private static long nextId = 0;
	private long id = ++nextId;
	private String code;
	private String name;
	private double price;
	private boolean onSale; //optional defaults to false
	
	public Product() {}
	
	public Product(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public Product(String code, String name, double price, boolean onSale) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.onSale = onSale;
	}

	public long getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	@Override
	public String toString() {
		return String.format("Product [id=%s, code=%s, name=%s, price=%8.2f, onSale=%s]", 
				id, code, name, price, onSale);
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public static Product input(InputStream in){
		Scanner sc = new Scanner(in);
		Product product = new Product();
		
		System.out.print("Code:");
		product.setCode(sc.nextLine());
		
		System.out.print("Name:");
		product.setName(sc.nextLine());
		
		double price = -1;
		do {
			System.out.print("Price:");
			String str = sc.nextLine();
			try {
				price = Double.parseDouble(str);
			} catch(NumberFormatException ex) {
				System.out.println("Error: Invalid number. Please enter again.");
			};
		} while (price < 0);
		product.setPrice(price);
		
		return product;
	}

	public static void main(String[] args) {
		Product p1 = new Product("BK-00001", "Thinking in Java", 25.50);
		Product p3 = new Product("BK-00003", "UML Distilled", 45.9);
		p1.setPrice(12.50);
		p3.setPrice(15.50);
		System.out.println(p1);
//		Product p2 = input(System.in);
//		System.out.println(p2);
	}

}
