package invoicing.controller;

import invoicing.entity.Product;

public class ProductController {
	public static final double VAT_RATE = .2;
	public double calcualteTotalPrice(Product[] products) {
		double total = 0;
		for(Product p: products)
			total += p.getPrice(); 
		return total;
	}
	
	public double calculateVat(double price){
		return price * VAT_RATE;
	}
	
	public static void main(String[] args) {
		ProductController pc = new ProductController();
		double total = pc.calcualteTotalPrice(new Product[] {
				new Product(1, "Whiteboard Marker", "", .89),
				new Product(2, "USB Flash 16GB", "AData", 8.5),
				new Product(3, "Computer Mouse", "Logitech", 12.33),
		});
		System.out.println(total);
	}

}
