package hello;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonController {
	private Scanner sc;

	public PersonController(InputStream inputStream) {
		sc = new Scanner(inputStream);
	}

	public Person inputPerson() {
		Person person = new Person();

		// input idNumber
		do {
			System.out.print("Input Person ID Number:");
			String input = sc.nextLine();
			if (Pattern.matches("\\d{10}", input)) {
				printDate(input);
				try {
					person.setIdNumber(Long.parseLong(input));
				} catch (NumberFormatException e) {
					System.out.println("Invalid number. Please try again.");
				}
			} else {
				System.out.println("Invalid ID number. Please try again.");
			}
		} while (!isValidIdNumber(person.getIdNumber()));

		// input name
		do {
			System.out.print("Input Person Name:");
			String input = sc.nextLine();
			person.setName(input.trim());
		} while (!isValidName(person.getName()));

		return person;
	}

	private boolean isValidName(String name) {
		return name.length() > 2;
	}

	private boolean isValidIdNumber(long idNumber) {
		return idNumber > 1000000000L && idNumber < 9999999999L;
	}

	public static void main(String[] args) {
		PersonController pController = new PersonController(System.in);
		Person p1 = pController.inputPerson();
		System.out.println(p1);
	}
	
	private static void printDate(String idNumberStr) {
		Pattern p = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})(.*)");
		Matcher m = p.matcher(idNumberStr);
		m.matches();
		for(int i = 0; i <= m.groupCount(); i++){
			System.out.println("Group(" + i + ")["+ m.start(i) + "," + m.end(i) 
					+ "]: " + m.group(i));
		}
	}

}
