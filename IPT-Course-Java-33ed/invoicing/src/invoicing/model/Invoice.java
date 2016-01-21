package invoicing.model;

import java.util.Date;


public class Invoice {
	private static long invoiceCount = 0;
	private long number = ++ invoiceCount;
	private Contragent issuer; 
	private Contragent receiver;
	private Date date = new Date();
	private Position[] positions = new Position[0];
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [number=").append(number).append(", issuer=")
				.append(issuer).append(", receiver=").append(receiver)
				.append(", date=").append(date).append(", positions=")
				.append(positions).append(", getTotal()=")
				.append(getTotal()).append(", getVAT()=").append(getVAT())
				.append(", getTotalPlusVAT()=").append(getTotalPlusVAT())
				.append("]");
		return builder.toString();
	}

	public Invoice() {
	}

	public Invoice(Contragent issuer, Contragent receiver, Position[] positions) {
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}

	public Invoice(long number, Contragent issuer, Contragent receiver, Date date,
			Position[] positions) {
		this.number = number;
		this.issuer = issuer;
		this.receiver = receiver;
		this.date = date;
		this.positions = positions;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Position[] getPositions() {
		return positions;
	}

	public void setPositions(Position[] positions) {
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
	
	public double getTotal() {
		double sum = 0;
		for(Position p: positions){
			sum += p.getTotalPrice();
		}
		return sum;
	}
	
	public double getVAT() {
		return getTotal() * Position.VAT_RATE;
	}
	
	public double getTotalPlusVAT() {
		return getTotal() +  getVAT();
	}

	
	
}
