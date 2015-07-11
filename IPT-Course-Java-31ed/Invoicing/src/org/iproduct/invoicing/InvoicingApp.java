package org.iproduct.invoicing;

import static org.iproduct.invoicing.model.ItemCategory.*;
import static org.iproduct.invoicing.model.Measure.*;

import org.iproduct.invoicing.model.Invoice;
import org.iproduct.invoicing.model.Item;
import org.iproduct.invoicing.model.ItemCategory;
import org.iproduct.invoicing.model.Position;

public class InvoicingApp {
	
	public static String formatInvoice(Invoice inv){
		StringBuilder builder = new StringBuilder();
		builder.append("No.: ").append(inv.getNumber())
			.append("\nDate: ").append(inv.getDate())
			.append("\nIssuer: ").append(inv.getIssuer())
			.append("\nReceiver: ").append(inv.getReceiver());

		builder.append(
				String.format("\n\n| %3s | %-30s | %8s | %8s | %7s | %10s |", 
					"No.", "Item", "Price", "Quantity", "Measure", "Total")
			);

		for(int i = 0; i < inv.getPositions().length; i++){
			Position pos = inv.getPositions()[i];
			builder.append(
				String.format("\n| %3d | %-30s | %8.2f | %8.2f | %7s | %10.2f |",
					pos.getPosition(), pos.getItem().getName(), 
					pos.getPrice(), pos.getQuantity(), pos.getItem().getMeasure(),
					pos.getTotalPrice())
			);
		}
			
		return builder.toString();
	}

	public static void main(String[] args) {
		Item i1 = new Item(BOOK, "Thinking in Java", "Prentice Hall", 15.7, PCS);
		Item i2 = new Item(HARDWARE, "Logitech Optical Mouse", "Logitech", 8.75, PCS);
		System.out.println(i1);
		Position p1 = new Position(1, i1, 5);
		Position p2 = new Position(2, i2, 3);
		System.out.println(p1);
		
		//Create positions array
		//Position[] positions = {p1, p2};
		Position[] positions = new Position[2];
		positions[0] = p1;
		positions[1] = p2;
		//Create invoice
		Invoice invoice = new Invoice("ABC Ltd.", "Ivan Petrov", positions);
		System.out.println(formatInvoice(invoice));
		
	}

}
