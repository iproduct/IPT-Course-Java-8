package invoicing.utility;

import invoicing.entity.Item;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {

	@Override
	public int compare(Item i0, Item i1) {
		return i0.getName().compareTo(i1.getName());
	}

}
