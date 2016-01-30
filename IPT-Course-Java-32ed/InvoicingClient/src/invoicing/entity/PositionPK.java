package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the position database table.
 * 
 */
public class PositionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int number;

	@Column(name="invoice_number", insertable=false, updatable=true, unique=true, nullable=false)
	private long invoiceNumber;

	public PositionPK() {
	}
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public long getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (invoiceNumber ^ (invoiceNumber >>> 32));
		result = prime * result + number;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PositionPK))
			return false;
		PositionPK other = (PositionPK) obj;
		if (invoiceNumber != other.invoiceNumber)
			return false;
		if (number != other.number)
			return false;
		return true;
	}

}