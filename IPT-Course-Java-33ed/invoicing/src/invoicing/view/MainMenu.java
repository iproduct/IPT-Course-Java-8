package invoicing.view;

import static invoicing.view.MenuCommand.ADD_PRODUCT;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoicing.controller.InvoiceRegister;
import invoicing.exception.EntityAlreadyExistsException;
import invoicing.model.Contragent;
import invoicing.model.Product;
import invoicing.util.ProductComparatorByCode;

public class MainMenu {
	private static final String[] menuItemStrings = { "Add product = ADD_PRODUCT",
			"List all products = LIST_ALL_PRODUCTS", "Add new contragent = ADD_CONTRAGENT",
			"List all contragents = LIST_ALL_CONTRAGENTS", "Choose company issuing invoices = SET_ISSUER",
			"Issue new invoice = ADD_INVOICE",
			"Print last invoice to file (invoices/invoice_XXXXXXXXXX.txt) = PRINT_LAST_INVOICE_TO_FILE",
			"List all invoices = LIST_ALL_INVOICES",
			"Generate invoicing report as CSV file = GENERATE_INVOICING_REPORT_AS_CSV_FILE" };

	private static final List<MenuItem> menuItems = new ArrayList<>();
	private static final Map<MenuCommand, Command> commands = new EnumMap<MenuCommand, Command>(MenuCommand.class);
	private static final InvoiceRegister register = new InvoiceRegister(); // Singleton
	private final Scanner in;

	public MainMenu(InputStream inStream) {
		this.in = new Scanner(inStream);
		List<Contragent> contragents = new ArrayList<>(Arrays.asList(InvoiceRegister.SAMPLE_CONTRAGENTS));
		contragents.addAll(Arrays.asList(InvoiceRegister.SAMPLE_COMPANIES));

		// Initialize the InvoiceRegister singleton
		register.initialize(Arrays.asList(InvoiceRegister.SAMPLE_PRODUCTS), contragents);

		// Parse menu items
		for (String s : menuItemStrings) {
			menuItems.add(parseMenuItemString(s));
		}
		
		// Add commands to map.
		commands.put(ADD_PRODUCT, new Command() {
			@Override
			public boolean action() {
				Product newProduct = new Product();
				newProduct.input(in);
				try {
					register.addProduct(newProduct);
					return true;
				} catch (EntityAlreadyExistsException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
		});
		commands.put(MenuCommand.LIST_ALL_PRODUCTS, new Command() {
			@Override
			public boolean action() {
				register.printAllProductsSorted(new ProductComparatorByCode());
				return true;
			}
		});

	}

	public void start() {
		boolean finish = false;
		do {
			System.out.println("           M A I N    M E N U");
			System.out.println("=========================================");
			for (int i = 0; i < menuItems.size(); i++)
				System.out.println("<" + (i+1) + "> " + menuItems.get(i));
			String answer;
			int chosenOption = 0;
			do {
				System.out.println("Please select operation [1 to " + menuItems.size() + "]:");
				answer = in.nextLine();
				try {
					chosenOption = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.err.println("Invalid option - should be [1 to " + menuItems.size() + "].");
				}
			} while (chosenOption < 1 && chosenOption > menuItems.size());

			// execute command
			Command commandToExecute = commands.get(menuItems.get(chosenOption-1).getCommand());
			if (commandToExecute != null) {
				commandToExecute.action();
			}
			System.out.println("\n");
		} while (!finish);
	}

	public static MenuItem parseMenuItemString(String menuItemString) {
		Pattern p = Pattern.compile("\\s*(.+)\\s*=\\s*(.+)\\s*");
		Matcher m = p.matcher(menuItemString);
		if (m.matches()) {
			try {
				return new MenuItem(m.group(1), Enum.valueOf(MenuCommand.class, m.group(2)));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
		System.err.println("Incorrect menu item string: " + menuItemString);
		return new MenuItem();
	}

	public static void main(String[] args) {
		MainMenu menu = new MainMenu(System.in);
		menu.start();
	}
}
