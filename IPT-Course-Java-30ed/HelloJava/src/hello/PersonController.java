package hello;

import java.io.InputStream;
import java.util.Scanner;

public class PersonController {
	private Scanner sc;
	public PersonController(InputStream inputStream){
		sc = new Scanner(inputStream);
	}
	public Person inputPerson() {
		Person person = new Person();
		
		// input idNumber
		do {
			System.out.print("Input Person ID Number:");
			String input = sc.nextLine();
			try {
				person.setIdNumber(Long.parseLong(input));
			} catch (NumberFormatException e){
				System.out.println("Invalid number. Please try again.");
			}	
		} while (!isValidIdNumber(person.getIdNumber()));
		
		//input name
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
	
	public static void main(String[] args){
		PersonController pController = new PersonController(System.in);
		Person p1 = pController.inputPerson();
		System.out.println(p1);
	}
	
}
