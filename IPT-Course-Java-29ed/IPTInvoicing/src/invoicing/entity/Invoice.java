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
import static invoicing.util.InvoiceType.VAT;
import static java.lang.Math.round;
import invoicing.util.InvoiceDetailTotalComparator;
import invoicing.util.InvoiceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This class models an invoice document including multiple
 * {@link InvoiceDetail invoice detail positions}
 * 
 * @author Trayan Iliev
 * @version 1.0
 * @see Contragent
 * @see java.lang.String#format(String, Object...)
 */
public class Invoice {
	public static final double VAT_RATE = 0.2;
//	public static final int INVOICE_SIMPLE = 0;
//	public static final int INVOICE_VAT = 1;	
	private static long invoiceCount = 0;
	private long number = ++invoiceCount;
	private InvoiceType kind = VAT;
	private Date issueDate = new Date();
	private Date operationDate = new Date();
	private Contragent issuer;
	private Contragent receiver;
	private List<InvoiceDetail> positions = new ArrayList<InvoiceDetail>();

	public Invoice() {
	}

	public Invoice(Contragent issuer, Contragent receiver) {
		this.issuer = issuer;
		this.receiver = receiver;
	}

	public Invoice(Date operationDate, Contragent issuer, Contragent receiver) {
		this.operationDate = operationDate;
		this.issuer = issuer;
		this.receiver = receiver;
	}

	public Invoice(long number, Date issueDate, Date operationDate,
			InvoiceType kind, Contragent issuer, Contragent receiver,
			List<InvoiceDetail> positions) {
		this.number = number;
		this.issueDate = issueDate;
		this.operationDate = operationDate;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
		this.kind = kind;
	}
	
	public Invoice(long number, Date issueDate, Date operationDate,
			InvoiceType kind, Contragent issuer, Contragent receiver,
			InvoiceDetail... positions) {
		this.number = number;
		this.issueDate = issueDate;
		this.operationDate = operationDate;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = Arrays.asList(positions);
		this.kind = kind;

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

	public InvoiceType getKind() {
		return kind;
	}

	public void setKind(InvoiceType kind) {
		this.kind = kind;
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

	public Collection<InvoiceDetail> getPositions() {
		return positions;
	}

	public void setPositions(List<InvoiceDetail> positions) {
		this.positions = positions;
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
		Invoice other = (Invoice) obj;
		if (number != other.number)
			return false;
		return true;
	}

	/**
	 * The method returns {@link java.lang.String#format(String, Object...)
	 * formatted String} representation of the invoice ready for printing
	 * 
	 * @return formatted invoice String representation
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Number: " + number);
		builder.append("\nInvoice type: " + kind);
		builder.append("\nIssue Date: " + issueDate);
		builder.append("\nOperation Date: " + operationDate);
		builder.append("\n\nIssuer:\n");
		builder.append(issuer);
		builder.append("\n\nReceiver:\n");
		builder.append(receiver);

		// print invoice details
		Collections.sort(positions, new InvoiceDetailTotalComparator());
		builder.append("\n\n" + InvoiceDetail.getDetailsHeader());
		// for(int i = 0; i < numberPositions; i++)
		// builder.append("\n" + positions.ge[i].getFormatted(i+1));
		// Iterator<InvoiceDetail> it = positions.iterator();
		int i = 0;
		for (InvoiceDetail id : positions)
			builder.append("\n" + id.getFormatted(++i));
		// while(it.hasNext()){
		// builder.append("\n" + it.next().getFormatted(++i));
		// }
		builder.append("\n" + InvoiceDetail.getSeparatorLine());

		//
		builder.append(String.format("\n\n%-15s%9.2f", "Total:",
				round(100 * getTotal()) / 100D));
		builder.append(String.format("\n%-15s%9.2f", "VAT:",
				round(100 * getVAT()) / 100D));
		builder.append(String.format("\n%-15s%9.2f", "Amount to pay:",
				round(100 * (getTotal() + getVAT())) / 100D));
		return builder.toString();
	}

	private double getTotal() {
		double sum = 0;
		// for(int i = 0; i < numberPositions; i++)
		// sum += positions[i].getTotal();
		// Iterator<InvoiceDetail> it = positions.iterator();
		// while(it.hasNext())
		// sum += it.next().getTotal();
		// for(int i = 0; i < positions.size(); i++){
		// InvoiceDetail invDetail = positions.get(i);
		// invDetail.setPrice(invDetail.getPrice()*0.50);
		// }
		
		for (InvoiceDetail invDetail : positions)
			sum += invDetail.getTotal();
		return sum;
	}

	public void reducePrice(double percentage) {
		positions.parallelStream().forEach(pos -> {
			pos.setPrice(pos.getPrice() * percentage);
		});
	}

	private double getVAT() {
		return (kind == VAT) ? VAT_RATE * getTotal() : 0;
	}

	/*
	 * This method adds new {@link InvoiceDetail invoice detail position} to the
	 * Invoice object
	 * 
	 * @param position new new position object to be added
	 */
	public void addPosition(InvoiceDetail position) {
		positions.add(position);
	}

	public static void main(String[] args) {
		Product p1 = new Product(1, "Thinking in Java", "Prentice Hall", 25.5,
				PCS, "Good introduction to Java.");
		Product p2 = new Product(2, "UML Distilled", "Prentice Hall", 15.7,
				PCS, "Good introduction to UML.");
		Product p3 = new Product(3, "Whiteboard Marker", "Velleda", 1.15, PCS,
				"Red color whiteboard marker of good quality.");
		Product p4 = new Product(4, "Another product", "Velleda", 1.50, PCS,
				"Another product");

		Contragent issuer = new Contragent(123456789, "Best Softoware AD",
				"Sofia 1000");
		Contragent receiver = new Contragent(999999999, "ABC Ltd",
				"Slatinska 44");
//		Invoice i1 = new Invoice(issuer, receiver);
//		i1.addPosition(new InvoiceDetail(p2, 5));
//		i1.addPosition(new InvoiceDetail(p1, 20));
//		i1.addPosition(new InvoiceDetail(p3, 100, 0.95));
//		i1.addPosition(new InvoiceDetail(p3, 100, 0.95));
//		i1.reducePrice(0.5);
		
		//Using full constructor + varargs
		Invoice i1 = new Invoice(1, new Date(), new Date(), VAT,
				issuer, receiver, 
				new InvoiceDetail(p1, 20), 
				new InvoiceDetail(p2, 5), 
				new InvoiceDetail(p3, 100, 0.95),
				new InvoiceDetail(p3, 100, 0.95));
		System.out.println(i1);

		// Invoice i2 = new Invoice(issuer, receiver);
		// i2.addPosition(new InvoiceDetail(p1, 20));
		// i2.addPosition(new InvoiceDetail(p3, 100, 0.95));
		// i2.addPosition(new InvoiceDetail(p2, 5));
		//
		// System.out.println("\n" + Arrays.toString(i1.getPositions()));
		// System.out.println(Arrays.toString(i2.getPositions()));
		//
		// System.out.println(
		// Arrays.equals(i1.getPositions(), i2.getPositions())
		// );
		System.out.println();
		for(InvoiceType it: InvoiceType.values()){
			System.out.println(it.ordinal() + ": " + it.name() +
					" - " + it.getDescription());
		}
	}

}
