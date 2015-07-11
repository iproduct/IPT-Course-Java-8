package org.iproduct.invoicing;

import static org.iproduct.invoicing.model.ItemCategory.*;
import static org.iproduct.invoicing.model.Measure.*;

import org.iproduct.invoicing.model.Item;
import org.iproduct.invoicing.model.ItemCategory;
import org.iproduct.invoicing.model.Position;

public class InvoicingApp {

	public static void main(String[] args) {
		Item i1 = new Item(BOOK, "Thinking in Java", "Prentice Hall", 15.7, PCS);
		Item i2 = new Item(HARDWARE, "Logitech Optical Mouse", "Logitech", 8.75, PCS);
		System.out.println(i1);
		Position p1 = new Position(1, i1, 5);
		Position p2 = new Position(2, i2, 3);
		System.out.println(p1);
	}

}
