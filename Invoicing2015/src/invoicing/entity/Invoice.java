package invoicing.entity;

import java.util.Arrays;
import java.util.Date;

public class Invoice {
	private static long invoiceCount = 0;
	
	private long number = ++invoiceCount;
	private Date issuingDate = new Date();
	private Contragent issuer, receiver;
	private Position[] positions = new Position[0];
	private String issuedBy;
	
	public Invoice() {
	}

	public Invoice(Contragent issuer, Contragent receiver) {
		this.issuer = issuer;
		this.receiver = receiver;
	}

	public Invoice(Contragent issuer, Contragent receiver,	Position[] positions) {
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}
	public Invoice(Date issuingDate, Contragent issuer, Contragent receiver,
			Position[] positions, String issuedBy) {
		this.issuingDate = issuingDate;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
		this.issuedBy = issuedBy;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getIssuingDate() {
		return issuingDate;
	}

	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
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

	public Position[] getPositions() {
		return positions;
	}

	public void setPositions(Position[] positions) {
		this.positions = positions;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
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
		if (!(obj instanceof Invoice))
			return false;
		Invoice other = (Invoice) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [number=");
		builder.append(number);
		builder.append(", issuingDate=");
		builder.append(issuingDate);
		builder.append(", issuer=");
		builder.append(issuer);
		builder.append(", receiver=");
		builder.append(receiver);
		builder.append(", positions=");
		builder.append(Arrays.toString(positions));
		builder.append(", issuedBy=");
		builder.append(issuedBy);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
