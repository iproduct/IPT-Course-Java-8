package invoicing.util;

import java.util.Comparator;

import invoicing.model.Product;

public class ProductComparatorByName implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

}
