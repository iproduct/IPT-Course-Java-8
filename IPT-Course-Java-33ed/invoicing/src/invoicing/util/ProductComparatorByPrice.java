package invoicing.util;

import java.util.Comparator;

import invoicing.model.Product;

public class ProductComparatorByPrice implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		return (int) Math.signum(o1.getPrice()- o2.getPrice());
	}

}
