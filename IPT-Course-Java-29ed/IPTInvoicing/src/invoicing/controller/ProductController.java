/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoicing project is a simple demo using Swing GUI and local file for DB
* 
* Copyright (c) 2012 - 2014 IPT - Intellectual Products & Technologies Ltd.
* All rights reserved.
*
* E-mail: office@iproduct.org
* Web: http://iproduct.org/
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License (the "License")
* as published by the Free Software Foundation version 2 of the License.
* You may not use this file except in compliance with the License. You can
* obtain a copy of the License at root directory of this project in file
* LICENSE.txt.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* When distributing the software, include this COPYRIGHT & LICENSE HEADER
* in each file, and include the License file LICENSE.txt in the root directory
* of your distributable.
*
* GPL Classpath Exception:
* IPT - Intellectual Products & Technologies (IPT) designates this particular
* file as subject to the "Classpath" exception as provided by IPT in
* the GPL Version 2 License file that accompanies this code.
*
* In case you modify this file,
* please add the appropriate notice below the existing Copyright notices,
* with the fields enclosed in brackets {} replaced by your own identification:
* "Portions Copyright (c) {year} {name of copyright owner}"
*/

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
