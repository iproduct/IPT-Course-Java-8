package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@Table(name="invoice")
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private long number;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	//bi-directional many-to-one association to Position
	@OneToMany(mappedBy="invoice")
	private List<Position> positions;

	//bi-directional many-to-one association to Contragent
	@ManyToOne
	@JoinColumn(name="receiver", nullable=false)
	private Contragent contragent1;

	//bi-directional many-to-one association to Contragent
	@ManyToOne
	@JoinColumn(name="issuer", nullable=false)
	private Contragent contragent2;

	public Invoice() {
	}

	public long getNumber() {
		return this.number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Position addPosition(Position position) {
		getPositions().add(position);
		position.setInvoice(this);

		return position;
	}

	public Position removePosition(Position position) {
		getPositions().remove(position);
		position.setInvoice(null);

		return position;
	}

	public Contragent getContragent1() {
		return this.contragent1;
	}

	public void setContragent1(Contragent contragent1) {
		this.contragent1 = contragent1;
	}

	public Contragent getContragent2() {
		return this.contragent2;
	}

	public void setContragent2(Contragent contragent2) {
		this.contragent2 = contragent2;
	}

}