/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* InvoiceRegister project is a simple invoicing demo.
* 
* Copyright (c) 2012 - 2016 IPT - Intellectual Products & Technologies Ltd.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Position;
import invoicing.model.Product;
import invoicing.util.ProductComparatorByCode;
import invoicing.util.ProductComparatorByPrice;

/**
 * Top level invoicing application class.
 * @author Trayan Iliev
 * @version 1.0
 * @since 1.0
 * @see org.iproduct.invoicing.model.Invoice
 */
public class InvoiceRegister {
	private Map<String, Product> products = new HashMap<>();
	private Map<Long, Contragent> contragents = new HashMap<>();
	private Map<Long, Invoice> invoices= new HashMap<>();
	
	public void initialize(Collection<? extends Product> products, 
			Collection<? extends Contragent> contragents){
		this.products.clear();
		for(Product p : products)
			this.products.put(p.getCode(), p);
		this.contragents.clear();
		for(Contragent p : contragents)
			this.contragents.put(p.getIdNumber(), p);
	}
	
	public void printAllProductsSorted(Comparator<Product> pc){
		List<Product> plist = new ArrayList<>(products.values());
		Collections.sort(plist, pc);
		for(Product p : plist)
			System.out.println(p);
	}
	
	public Product findProductByProductCode(String pCode){
		return products.get(pCode);
	}

	public Contragent findContragentByIdNumber(long idNumber){
		return contragents.get(idNumber);
	}
	
	/**
	 * This method prints the invoice as text.
	 * @param inv invoice to be printed
	 * @return formatted text layout of the inoce
	 */
	public static String formatInvoice(Invoice inv){
		StringBuilder builder = new StringBuilder();
		builder.append("No.: ").append(inv.getNumber())
			.append("\nDate: ").append(inv.getDate())
			.append("\n\nIssuer: ").append(inv.getIssuer())
			.append("\n\nReceiver: ").append(inv.getReceiver());

		builder.append(
				String.format("\n\n| %3s | %-30s | %8s | %8s | %7s | %10s |", 
					"No.", "Product", "Price", "Quantity", "Measure", "Total")
			);

		int n = 1;
		for(Position pos : inv.getPositions()){
			builder.append(
				String.format("\n| %3d | %-30.30s | %8.2f | %8.2f | %7s | %10.2f |",
					n++, pos.getProduct().getName(), 
					pos.getPrice(), pos.getQuantity(), "PCS",
					pos.getTotalPrice())
			);
		}
		
		builder.append(String.format("\n\n%66sTotal: %10.2f", "", inv.getTotal()))
		.append(String.format("\n%68sVAT: %10.2f", "", inv.getVAT()))
		.append(String.format("\n%84s", "-----------------"))
		.append(String.format("\n\n%53sTotal VAT included: %10.2f", "", inv.getTotalPlusVAT()));
			
			
		return builder.toString();
	}

	public static void main(String[] args) {
		Product[] products = {
			new Product("BK001", "Thinking in Java", 15.7),
			new Product("HD001", "Logitech Optical Mouse", 8.75),
			new Product("SF001", "Photoshop CC", 2000),
			new Product("HD002", "Raspberry Pi 2", 80),
			new Product("SV001", "Raspbian Installation", 20),
			new Product("HD003", "3dIMU - 3D Acceelerometr, Gyrospone & Comapss", 35)
		};
		
		Position[] positions = {
				new Position(products[0]), 
				new Position(products[1], 5), 
				new Position(products[3], 10)
				};
		
		Contragent[] contragents = {			
				new Contragent(122222222, "ABC Ltd.", "Plovdiv"),
				new Contragent(1234567891, "Ivan Petrov", "Sofia 1000")
		};
		
		InvoiceRegister register = new InvoiceRegister();
		register.initialize(Arrays.asList(products), Arrays.asList(contragents));
		
		register.printAllProductsSorted(new ProductComparatorByCode());
		System.out.println();
		register.printAllProductsSorted(new ProductComparatorByPrice());
		
		//Create invoice
		
//		Contragent issuer = new Company();
//		issuer.input(System.in);
//		
//		Contragent receiver = new Contragent();
//		receiver.input(System.in);
//		
//		Invoice invoice = new Invoice(issuer, receiver, positions);
//		System.out.println(formatInvoice(invoice));
	}

}
