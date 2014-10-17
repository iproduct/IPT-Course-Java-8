package invoicing.controller;

import invoicing.entity.Measure;
import invoicing.entity.Product;
import invoicing.util.ProductValidationException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController {
	public static final int PRODUCT_COLUMNS = 6; 
	public static final String[] COLUMN_NAMES = {"PID", "Name", "Price", "Supplier", "Measure", "Description"};
	private static final String DB_PRODUCTS = "products_db.dat";
	private static Scanner sc = new Scanner(System.in);
	private List<Product> products = new ArrayList<>();
	private Logger logger = Logger.getLogger(ProductController.class.getName());

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(String pidStr, String name, String supplier,
			String priceStr, String description, Measure measure) 
			throws ProductValidationException{
		try {
			long pid = Long.parseLong(pidStr);
			double price = Double.parseDouble(priceStr);
			products.add(new Product(pid, name, supplier, price, measure ,description));
		} catch (NumberFormatException e){
			logger.log(Level.SEVERE, "Invalid Product data.", e);
			throw new ProductValidationException("Product data is not valid.");
		}
	}

	public void inputProduct() {
		Product newProduct = new Product(getNextPid());
		do {
			System.out.print("Product Name: ");
			newProduct.setName(sc.nextLine());
		} while (newProduct.getName().length() == 0);
		do {
			System.out.print("Supplier: ");
			newProduct.setSupplier(sc.nextLine());
		} while (newProduct.getSupplier().length() == 0);
		do {
			System.out.print("Price: ");
			String s = sc.nextLine();
			try{
				newProduct.setPrice(Double.parseDouble(s));
			} catch (NumberFormatException e){
				System.err.println("Error: Please enter a valid price (number).");
			}	
		} while (newProduct.getPrice() <= 0);
		do {
			System.out.print("Measure [");
			for(Measure m: Measure.values())
				System.out.print(m + ", ");
			System.out.print("]: ");
			String s = sc.nextLine();
			try{
				newProduct.setMeasure(
					Measure.valueOf(Measure.class, s.toUpperCase()));
			} catch (IllegalArgumentException e){
				System.err.println("Error: Please enter a valid measure.");
			}	
		} while (newProduct.getMeasure() == null);
		System.out.print("Description: ");
		newProduct.setDescription(sc.nextLine());
		products.add(newProduct);
	}
	
	public void writeProductsToFile(String fileName) throws IOException{
		try( DataOutputStream os = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName))) ){
			for(Product p: products){
				os.writeLong(p.getPid());
				os.writeUTF(p.getName());
				os.writeUTF(p.getSupplier());
				os.writeDouble(p.getPrice());
				os.writeUTF(p.getMeasure().toString());
				os.writeUTF(p.getDescription());		
			}
		}
	}
	
	public void readProductsFromFile(String fileName) throws IOException{
		try( DataInputStream os = new DataInputStream(
				new BufferedInputStream(new FileInputStream(fileName))) ){
			try{
				products.clear();
				while(true){
					Product p = new Product();
					p.setPid(os.readLong());
					p.setName(os.readUTF());
					p.setSupplier(os.readUTF());
					p.setPrice(os.readDouble());
					p.setMeasure(Measure.valueOf(Measure.class, os.readUTF()));
					p.setDescription(os.readUTF());	
					products.add(p);
				}
			} catch (EOFException e){}
		}
	}
	
	protected long getNextPid(){
		OptionalLong maxId = 
			products.stream().mapToLong(p -> p.getPid()).max();
		if (maxId.isPresent())
			return maxId.getAsLong() + 1;
		else
			return 1;
	}
	
	public static void main(String[] args) {
		ProductController pc = new ProductController();
		try{
			pc.readProductsFromFile(DB_PRODUCTS);
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
		pc.inputProduct();
		for(Product p: pc.getProducts()){
			System.out.println(p);
		}
		try{
			pc.writeProductsToFile(DB_PRODUCTS);
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
		

	}

}
