package invoicing.utility;

import invoicing.entity.Item;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUtilities {
	private static Scanner sc = new Scanner(System.in);
	
	public static Item inputItem() {
		Item item = new Item();

		// input idNumber
		do {
			System.out.print("Input Item ID Number:");
			String input = sc.nextLine();
			if (Pattern.matches("\\d{1,10}", input)) {
				try {
					item.setId(Long.parseLong(input));
				} catch (NumberFormatException e) {
					System.out.println("Invalid number. Please try again.");
				}
			} else {
				System.out.println("Invalid ID number. Please try again.");
			}
		} while (item.getId() <= 0);

		// input name
		do {
			System.out.print("Item Name:");
			String input = sc.nextLine();
			item.setName(input.trim());
		} while (item == null || item.getName().isEmpty());
		
		// input price
		do {
			System.out.print("Input Item Price:");
			String input = sc.nextLine();
			if (Pattern.matches("\\d{1,10}\\.?\\d{0,5}", input)) {
				try {
					item.setPrice(Double.parseDouble(input));
				} catch (NumberFormatException e) {
					System.out.println("Invalid number. Please try again.");
				}
			} else {
				System.out.println("Invalid Price. Please try again.");
			}
		} while (item.getPrice() <= 0D);

		// input name
		do {
			System.out.print("Item Vendor:");
			String input = sc.nextLine();
			item.setVendor(input.trim());
		} while (item == null || item.getVendor().isEmpty());

		return item;
	}
}
