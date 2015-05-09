package invoicing.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice<T extends Item> {
	private static final double VAT_RATE = 0.2;

	private static long invoiceCount = 0;
	
	private long number = ++invoiceCount;
	private Date issuingDate = new Date();
	private Contragent issuer, receiver;
	private List<Position<T>> positions = new ArrayList<>();
	private String issuedBy;
	
	public Invoice() {
	}

	public Invoice(Contragent issuer, Contragent receiver) {
		this.issuer = issuer;
		this.receiver = receiver;
	}

	public Invoice(Contragent issuer, Contragent receiver,	List<Position<T>> positions) {
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}
	public Invoice(Date issuingDate, Contragent issuer, Contragent receiver,
			List<Position<T>> positions, String issuedBy) {
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

	public List<Position<T>> getPositions() {
		return positions;
	}

	public void setPositions(List<Position<T>> positions) {
		this.positions = positions;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	
	public double getTotal() {
		double total = 0;
		for(Position<T> p: positions)
			total += p.getTotal();
		return total;
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
		builder.append(positions);
		builder.append(", issuedBy=");
		builder.append(issuedBy);
		builder.append("]");
		return builder.toString();
	}

	public double getVat() {
		return getTotal() * VAT_RATE;
	}
	
	public double getTotalWithVat() {
		return getTotal() + getVat();
	}
	
	
	
}
