package invoicing.util;

import java.util.Comparator;

import invoicing.model.Product;

public class ProductComparatorByCode implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		return o1.getCode().compareToIgnoreCase(o2.getCode());
	}

}
