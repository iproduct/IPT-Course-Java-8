package invoicing.util;

public enum InvoiceType {
	SIMPLE("Simple invoice that does not add VAT"), 
	VAT ("VAT invoice issued by VAT registered companies"), 
	DEBIT_NOTE ("Buyer's request for credit note document"), 
	CREDIT_NOTE ("Seller's document in response to buyer's debit note");
	
	private String description;
	private InvoiceType(String descr){
		description = descr;
	}
	public String getDescription() {
		return description;
	}
}
