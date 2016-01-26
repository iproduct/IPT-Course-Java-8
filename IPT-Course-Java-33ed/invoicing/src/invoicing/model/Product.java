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

package invoicing.model;

import java.io.InputStream;
import java.util.Scanner;

public class Product {
	private static int count = 0;
	private int id = ++count;
	private String code;
	private String name;
	private double price = -1;

	public Product() {
	}

	public Product(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Product(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
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

	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	public void input(Scanner in) {
		String input;

		//input code
		System.out.println("Product code: ");
		do {
			input = in.nextLine();
			if (input.matches("(\\d|[a-zA-Z]){5,10}") )
			 	setCode(input);
			else
				System.err.println("Code should be 5 to 10 letters or digits.");
		} while (getCode() == null);
		
		//input name
		System.out.println("Product name: ");
		do {
			input = in.nextLine();
			if ( !input.isEmpty() )
			 	setName(input);
			else
				System.err.println("Name should not be empty.");
		} while (getName() == null);
		
		//input price
		System.out.println("Product price: ");
		do {
			input = in.nextLine().trim();
			if ( input.matches("\\d+(\\.\\d+)?") )
			 	setPrice(Double.parseDouble(input));
			else
				System.err.println("Invalid price. Ex: 9.57");
		} while (getPrice() < 0);
	}
	
	public static void main(String[] args) {
		Product p1 = new Product("BK001", "Thinking in Java 4 ed.", 25.99);
		Product p2 = new Product("BK002", "UML Distilled");
		Product p3 = new Product();
		p3.input(new Scanner(System.in));
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

	}

}
