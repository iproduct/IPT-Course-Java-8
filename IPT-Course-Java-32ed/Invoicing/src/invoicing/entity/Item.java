package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the items database table.
 * 
 */
@Entity
@Table(name="items")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false)
	private long id;

	@Column(nullable=false, length=20)
	private String category;

	@Column(nullable=false, length=80)
	private String name;

	private double price;

	@Column(length=80)
	private String vendor;

	//bi-directional many-to-one association to Position
	@OneToMany(mappedBy="item")
	private List<Position> positions;

	public Item() {
	}

	public Item(long id, String name, String group) {
		this.id = id;
		this.name = name;
		this.category = group;
	}
	
	public Item(long id, String name, String vendor, double price, String group) {
		this.id = id;
		this.name = name;
		this.vendor = vendor;
		this.price = price;
		this.category = group;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String group) {
		this.category = group;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Position addPosition(Position position) {
		getPositions().add(position);
		position.setItem(this);

		return position;
	}

	public Position removePosition(Position position) {
		getPositions().remove(position);
		position.setItem(null);

		return position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=").append(id).append(", category=").append(category).append(", name=").append(name)
				.append(", price=").append(price).append(", vendor=").append(vendor).append(", positions=")
				.append(positions).append("]");
		return builder.toString();
	}

}