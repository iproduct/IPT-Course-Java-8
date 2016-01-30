package invoicing.entity.old;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Contragent {
	private long idNumber;
	private String name;
	private String address;
	private CotragentType type;
	private String accountablePerson;
	private String iban;
	private String bic;
	
	public Contragent() {
	}

	public Contragent(long idNumber, String name, String address) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
	}

	public Contragent(long idNumber, String name, String address, CotragentType type, String accountablePerson) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.type = type;
		this.accountablePerson = accountablePerson;
	}

	public Contragent(long idNumber, String name, String address, CotragentType type, String accountablePerson,
			String iban, String bic) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.type = type;
		this.accountablePerson = accountablePerson;
		this.iban = iban;
		this.bic = bic;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CotragentType getType() {
		return type;
	}

	public void setType(CotragentType type) {
		this.type = type;
	}

	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idNumber ^ (idNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Contragent))
			return false;
		Contragent other = (Contragent) obj;
		if (idNumber != other.idNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contragent [idNumber=").append(idNumber).append(", name=").append(name).append(", address=")
				.append(address).append(", type=").append(type).append(", accountablePerson=").append(accountablePerson)
				.append(", iban=").append(iban).append(", bic=").append(bic).append("]");
		return builder.toString();
	}
	
	
}
