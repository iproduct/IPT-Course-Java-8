package invoicing.util;

import invoicing.entity.InvoiceDetail;
import java.util.Comparator;

public class InvoiceDetailTotalComparator implements Comparator<InvoiceDetail> {

	@Override
	public int compare(InvoiceDetail id0, InvoiceDetail id1) {
		return -(new Double(id0.getTotal()).compareTo(new Double(id1.getTotal())));
	}

}
