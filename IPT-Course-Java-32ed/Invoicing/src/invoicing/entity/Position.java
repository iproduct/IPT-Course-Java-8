package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the position database table.
 * 
 */
@Entity
@Table(name="position")
@NamedQuery(name="Position.findAll", query="SELECT p FROM Position p")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PositionPK id;

	@Column(nullable=false)
	private double price;

	@Column(nullable=false)
	private double quantity;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_number", nullable=false, insertable=false, updatable=false)
	private Invoice invoice;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	private Item item;

	public Position() {
	}

	public PositionPK getId() {
		return this.id;
	}

	public void setId(PositionPK id) {
		this.id = id;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [id=").append(id).append(", price=").append(price).append(", quantity=")
				.append(quantity).append(", invoice=").append(invoice).append(", item=").append(item).append("]");
		return builder.toString();
	}

}