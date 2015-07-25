package org.iproduct.invoicing;

import static org.iproduct.invoicing.model.ItemCategory.BOOK;
import static org.iproduct.invoicing.model.ItemCategory.HARDWARE;
import static org.iproduct.invoicing.model.ItemCategory.SERVICE;
import static org.iproduct.invoicing.model.ItemCategory.SOFTWARE;
import static org.iproduct.invoicing.model.Measure.HOUR;
import static org.iproduct.invoicing.model.Measure.PCS;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.iproduct.invoicing.model.Invoice;
import org.iproduct.invoicing.model.Item;
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

		int n = 1;
		for(Position pos : inv.getPositions()){
			builder.append(
				String.format("\n| %3d | %-30s | %8.2f | %8.2f | %7s | %10.2f |",
					n++, pos.getItem().getName(), 
					pos.getPrice(), pos.getQuantity(), pos.getItem().getMeasure(),
					pos.getTotalPrice())
			);
		}
			
		return builder.toString();
	}

	public static void main(String[] args) {
		Item[] items = {
			new Item(BOOK, "Thinking in Java", "Prentice Hall", 15.7, PCS),
			new Item(HARDWARE, "Logitech Optical Mouse", "Logitech", 8.75, PCS),
			new Item(SOFTWARE, "Photoshop CC", "Adobe", 2000, PCS),
			new Item(HARDWARE, "Raspberry Pi 2", "Raspberry Foundation", 80, PCS),
			new Item(SERVICE, "Raspbian Installation", "Logitech", 20, HOUR),
			new Item(HARDWARE, "3dIMU - 3D Acceelerometr, Gyrospone & Comapss", 
				"Pololu", 35, PCS)
		};
		
		List<Position> positions = Arrays.asList(items).stream()
			.map( (Item item) -> { return new Position(item, 1);})
			.map( item -> {System.out.println(item); return item;})
			.collect(Collectors.toList());
		
		//Create invoice
		Invoice invoice = new Invoice("ABC Ltd.", "Ivan Petrov", positions);
		System.out.println(formatInvoice(invoice));
		
	}

}
