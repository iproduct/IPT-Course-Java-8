package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the position database table.
 * 
 */
@Embeddable
public class PositionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int number;

	@Column(name="invoice_number", insertable=false, updatable=false, unique=true, nullable=false)
	private String invoiceNumber;

	public PositionPK() {
	}
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PositionPK)) {
			return false;
		}
		PositionPK castOther = (PositionPK)other;
		return 
			(this.number == castOther.number)
			&& this.invoiceNumber.equals(castOther.invoiceNumber);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.number;
		hash = hash * prime + this.invoiceNumber.hashCode();
		
		return hash;
	}
}