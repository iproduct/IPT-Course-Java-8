package invoicing.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the contragent database table.
 * 
 */
@Entity
@Table(name="contragent")
@NamedQuery(name="Contragent.findAll", query="SELECT c FROM Contragent c")
public class Contragent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private long idNumber;

	@Column(length=80)
	private String accountablePerson;

	@Column(length=120)
	private String address;

	@Column(length=10)
	private String bic;

	@Column(length=34)
	private String iban;

	@Column(length=120)
	private String name;
	
	@Enumerated
	private ContragentType type;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="issuer")
	private List<Invoice> issuedBy;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="receiver")
	private List<Invoice> receivedBy;

	public Contragent() {
	}

	public long getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public String getAccountablePerson() {
		return this.accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBic() {
		return this.bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContragentType getType() {
		return this.type;
	}

	public void setType(ContragentType type) {
		this.type = type;
	}

	public List<Invoice> getInvoices1() {
		return this.issuedBy;
	}

	public void setInvoices1(List<Invoice> invoices1) {
		this.issuedBy = invoices1;
	}

	public Invoice addInvoices1(Invoice invoices1) {
		getInvoices1().add(invoices1);
		invoices1.setIssuer(this);

		return invoices1;
	}

	public Invoice removeInvoices1(Invoice invoices1) {
		getInvoices1().remove(invoices1);
		invoices1.setIssuer(null);

		return invoices1;
	}

	public List<Invoice> getInvoices2() {
		return this.receivedBy;
	}

	public void setInvoices2(List<Invoice> invoices2) {
		this.receivedBy = invoices2;
	}

	public Invoice addInvoices2(Invoice invoices2) {
		getInvoices2().add(invoices2);
		invoices2.setReceiver(this);

		return invoices2;
	}

	public Invoice removeInvoices2(Invoice invoices2) {
		getInvoices2().remove(invoices2);
		invoices2.setReceiver(null);

		return invoices2;
	}

}