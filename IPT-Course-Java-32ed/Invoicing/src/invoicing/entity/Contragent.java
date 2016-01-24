package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	@Column(length=255)
	private String accountablePerson;

	@Column(length=255)
	private String address;

	@Column(length=255)
	private String bic;

	@Column(length=255)
	private String iban;

	@Column(length=255)
	private String name;

	private int type;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="contragent1")
	private List<Invoice> invoices1;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="contragent2")
	private List<Invoice> invoices2;

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

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Invoice> getInvoices1() {
		return this.invoices1;
	}

	public void setInvoices1(List<Invoice> invoices1) {
		this.invoices1 = invoices1;
	}

	public Invoice addInvoices1(Invoice invoices1) {
		getInvoices1().add(invoices1);
		invoices1.setContragent1(this);

		return invoices1;
	}

	public Invoice removeInvoices1(Invoice invoices1) {
		getInvoices1().remove(invoices1);
		invoices1.setContragent1(null);

		return invoices1;
	}

	public List<Invoice> getInvoices2() {
		return this.invoices2;
	}

	public void setInvoices2(List<Invoice> invoices2) {
		this.invoices2 = invoices2;
	}

	public Invoice addInvoices2(Invoice invoices2) {
		getInvoices2().add(invoices2);
		invoices2.setContragent2(this);

		return invoices2;
	}

	public Invoice removeInvoices2(Invoice invoices2) {
		getInvoices2().remove(invoices2);
		invoices2.setContragent2(null);

		return invoices2;
	}

}