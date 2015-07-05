package hello;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonUtils {
	private static Scanner sc; 
	
	public static Person inputPerson(InputStream in){
		sc = new Scanner(in);
		Person p = new Person();
		String line;
		
		//read SSN
		p.setIdNumber(inputLongNumber("SSN", 10, 10));
		p.setName(inputString("Name", 5, 100, false));
		p.setAge((int)inputLongNumber("Age", 1, 3));
		p.setAddress(inputString("Address", 5, 100, true));
		p.setPhone(inputString("Phone", 5, 20, true));
		
		return p;
	}

	public static String inputString(String fieldName, int minChars, int maxChars, 
			boolean hasDigits) {
		String line;
		while(true) {
			System.out.print("Input your " + fieldName + ": ");
			line = sc.nextLine();
			if (Pattern.matches("[a-zA-Z"
					+ (hasDigits ? "\\d\\(\\)\\+" : "") 
					+ "\\s]{" + minChars + "," + maxChars + "}", line)) {
				return line.trim();
			} else {
				System.err.println("Invalid " + fieldName + ". Try again.");
			}
		}
	}

	public static long inputLongNumber(String fieldName, int minDigits, int maxDigits) {
		String line;
		while(true) {
			System.out.print("Input your " + fieldName + ": ");
			line = sc.nextLine();
			if (Pattern.matches("\\d{" + minDigits + "," + maxDigits +  "}", line)) {
				return Long.parseLong(line);
			} else {
				System.err.println("Invalid " + fieldName + ". Try again.");
			}
		} 
	}

	public static void main(String[] args) {
		Person p = inputPerson(System.in);
		System.out.println(p);

	}

}
