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

public class Company extends Contragent {
	private boolean vatRegistered;
	private String accountablePerson;
	private String bic;
	private String iban;
	
	public Company() {
	}

	public Company(long idNumber, String name, String address) {
		super(idNumber, name, address);
	}

	public Company(long idNumber, String name, String address, boolean organization) {
		super(idNumber, name, address, organization);
	}

	public Company(long idNumber, String name, String address, String phone, boolean organization) {
		super(idNumber, name, address, phone, organization);
	}

	public Company(long idNumber, String name, String address, String phone, boolean organization, 
			boolean vatRegistered, String accountablePerson, String bic, String iban) {
		super(idNumber, name, address, phone, organization);
		this.vatRegistered = vatRegistered;
		this.accountablePerson = accountablePerson;
		this.bic = bic;
		this.iban = iban;
	}

	public boolean isVatRegistered() {
		return vatRegistered;
	}

	public void setVatRegistered(boolean vatRegistered) {
		this.vatRegistered = vatRegistered;
	}

	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [vatRegistered=").append(vatRegistered).append(", accountablePerson=")
				.append(accountablePerson).append(", bic=").append(bic).append(", iban=").append(iban)
				.append(", isVatRegistered()=").append(isVatRegistered()).append(", getAccountablePerson()=")
				.append(getAccountablePerson()).append(", getBic()=").append(getBic()).append(", getIban()=")
				.append(getIban()).append("]");
		return builder.toString();
	}
	
	public void input(InputStream inStream) {
		Scanner in = new Scanner(inStream);
		String input;
		
		super.input(inStream);

		//is VAT registered
		System.out.println("Is VAT registered [yes OR no]: ");
		boolean valid = false;
		do {
			input = in.nextLine().trim().toLowerCase();		
			if ( input.matches("yes|no") ) {
			 	setVatRegistered(input.equals("yes"));
				valid = true;
			} else
				System.err.println("yes or no");
		} while (!valid);
		
		//input accountable person name
		System.out.println("Accountable Person Name: ");
		do {
			input = in.nextLine();
			if ( !input.isEmpty() )
			 	setAccountablePerson(input);
			else
				System.err.println("Name should not be empty.");
		} while (getAccountablePerson() == null);
		
		//input BIC
		System.out.println("BIC: ");
		do {
			input = in.nextLine();
			if (input.matches("\\w{6}") )
			 	setBic(input);
			else
				System.err.println("BIC should be 6 characters.");
		} while (getBic() == null);
		
		//input IBAN
		System.out.println("IBAN: ");
		do {
			input = in.nextLine();
			if (input.matches("\\w{10,16}") )
			 	setIban(input);
			else
				System.err.println("IBAN should be 10 to 16 characters.");
		} while (getIban() == null);
				
	}

}
