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

package invoicing.entity;

import static invoicing.entity.Measure.PCS;
import static java.lang.Math.round;
import invoicing.util.InvoiceDetailTotalComparator;

import java.util.Arrays;
import java.util.Date;
/** This class models an invoice document including 
 * multiple {@link InvoiceDetail invoice detail positions}
 * @author Trayan Iliev
 * @version 1.0
 * @see Contragent
 * @see java.lang.String#format(String, Object...)
 */
public class Invoice_old {
	public static final int MAX_POSITIONS = 50;
	public static final double VAT_RATE = 0.2;
	private static long invoiceCount = 0;
	private long number = ++invoiceCount;
	private Date issueDate = new Date();
	private Date operationDate = new Date();
	private Contragent issuer;
	private Contragent receiver;
	private InvoiceDetail[] positions = new InvoiceDetail[MAX_POSITIONS];
	private int numberPositions = 0;
	public Invoice_old() {
	}
	public Invoice_old(Contragent issuer, Contragent receiver) {
		this.issuer = issuer;
		this.receiver = receiver;
	}
	public Invoice_old(Date operationDate, Contragent issuer, Contragent receiver) {
		this.operationDate = operationDate;
		this.issuer = issuer;
		this.receiver = receiver;
	}
	public Invoice_old(long number, Date issueDate, Date operationDate,
			Contragent issuer, Contragent receiver, InvoiceDetail[] positions,
			int numberPositions) {
		this.number = number;
		this.issueDate = issueDate;
		this.operationDate = operationDate;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
		this.numberPositions = numberPositions;
	}
	public long getNumber() {
		return number;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public Contragent getIssuer() {
		return issuer;
	}
	public void setIssuer(Contragent issuer) {
		this.issuer = issuer;
	}
	public Contragent getReceiver() {
		return receiver;
	}
	public void setReceiver(Contragent receiver) {
		this.receiver = receiver;
	}
	public InvoiceDetail[] getPositions() {
		return positions;
	}
	public void setPositions(InvoiceDetail[] positions) {
		this.positions = positions;
	}
	public int getNumberPositions() {
		return numberPositions;
	}
	public void setNumberPositions(int numberPositions) {
		this.numberPositions = numberPositions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (number ^ (number >>> 32));
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
		Invoice_old other = (Invoice_old) obj;
		if (number != other.number)
			return false;
		return true;
	}
	/** The method returns 
	 * {@link java.lang.String#format(String, Object...) formatted String}
	 * representation of the invoice ready for printing
	 * @return formatted invoice String representation
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Number: " + number);
		builder.append("\nIssue Date: " + issueDate);
		builder.append("\nOperation Date: " + operationDate);
		builder.append("\n\nIssuer:\n");
		builder.append(issuer);
		builder.append("\n\nReceiver:\n");
		builder.append(receiver);

		//print invoice details
		Arrays.sort(positions,  0, getNumberPositions(), 
				new InvoiceDetailTotalComparator());
		builder.append("\n\n" + InvoiceDetail.getDetailsHeader());
		for(int i = 0; i < numberPositions; i++)
			builder.append("\n" + positions[i].getFormatted(i+1));
		builder.append("\n" + InvoiceDetail.getSeparatorLine());
		
		//
		builder.append(String.format("\n\n%-15s%9.2f", 
				"Total:", round(100 * getTotal())/100D));
		builder.append(String.format("\n%-15s%9.2f", 
				"VAT:", round(100 * getVAT())/100D));
		builder.append(String.format("\n%-15s%9.2f",
				"Amount to pay:", round(100 * (getTotal() + getVAT()))/100D));
		return builder.toString();
	}
	private double getTotal() {
		double sum = 0;
		for(int i = 0; i < numberPositions; i++)
			sum += positions[i].getTotal();
		return sum;
	}
	private double getVAT() {
		return VAT_RATE  * getTotal();
	}
	
	/* This method adds new {@link InvoiceDetail invoice detail position}
	 * to the Invoice object
	 * @param position new new position object to be added
	 */ 
	public void addPosition(InvoiceDetail position){
		positions[numberPositions++] = position;
	}
	
	public static void main(String[] args){
		Product p1= new Product(1, "Thinking in Java", "Prentice Hall", 25.5, 
				PCS, "Good introduction to Java.");
		Product p2= new Product(2, "UML Distilled", "Prentice Hall", 15.7, 
				PCS, "Good introduction to UML.");
		Product p3= new Product(3, "Whiteboard Marker", "Velleda", 1.15, 
				PCS, "Red color whiteboard marker of good quality.");
		Product p4= new Product(3, "Another product", "Velleda", 1.50, 
				PCS, "Another product");

		
		Contragent issuer = new Contragent(123456789, "Best Softoware AD", "Sofia 1000");
		Contragent receiver = new Contragent(999999999, "ABC Ltd", "Slatinska 44");
		Invoice_old i1 = new Invoice_old(issuer, receiver);
		i1.addPosition(new InvoiceDetail(p2, 5));
		i1.addPosition(new InvoiceDetail(p1, 20));
		i1.addPosition(new InvoiceDetail(p3, 100, 0.95));
		i1.addPosition(new InvoiceDetail(p4, 5));
		System.out.println(i1);
		
		Invoice_old i2 = new Invoice_old(issuer, receiver);
		i2.addPosition(new InvoiceDetail(p1, 20));
		i2.addPosition(new InvoiceDetail(p3, 100, 0.95));
		i2.addPosition(new InvoiceDetail(p2, 5));
		
		System.out.println("\n" + Arrays.toString(i1.getPositions()));
		System.out.println(Arrays.toString(i2.getPositions()));

		System.out.println(
				Arrays.equals(i1.getPositions(), i2.getPositions())
		);
	}
	
	
	
	
	
}
