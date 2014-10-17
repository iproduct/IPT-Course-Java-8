package invoicing.entity;

public class Contragent {
	private long idNumber;
	private String name;
	private String address;
	private boolean vatRegistered;
	private boolean organization;
	private String iban;
	private String bic;
	private String contacts;
	
	public Contragent() {
	}

	public Contragent(long idNumber, String name, String address) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
	}

	public Contragent(long idNumber, String name, String address,
			boolean vatRegistered, boolean organization, String iban,
			String bic, String contacts) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.vatRegistered = vatRegistered;
		this.organization = organization;
		this.iban = iban;
		this.bic = bic;
		this.contacts = contacts;
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

	public boolean isVatRegistered() {
		return vatRegistered;
	}

	public void setVatRegistered(boolean vatRegistered) {
		this.vatRegistered = vatRegistered;
	}

	public boolean isOrganization() {
		return organization;
	}

	public void setOrganization(boolean organization) {
		this.organization = organization;
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Identification Number: ");
		sb.append(idNumber).append("\nName=").append(name).append("\nAddress: ")
			.append(address);
		if(isOrganization() && isVatRegistered()){
			sb.append("VAT Number: BG" + getIdNumber());
		}
//		.append(", vatRegistered=").append(vatRegistered)
//			.append(", organization=").append(", iban=").append(iban)
//			.append(", bic=").append(bic).append(", contacts=").append(contacts)
//			.append("]");
		return sb.toString();
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
		return (obj != null) && 
				(obj instanceof Contragent) && 
				(idNumber == ((Contragent)obj).idNumber);
	}

	public static void main(String[] args){
		Contragent c1 = new Contragent(1234567890, "Ivan Petrov", "Sofia 1000");
		Contragent c2 = new Contragent(1234567890, "Ivan Petrov", "Sofia 1000");
//		System.out.println(c1.equals(c2));
		System.out.println(c1);
	}
	
}
