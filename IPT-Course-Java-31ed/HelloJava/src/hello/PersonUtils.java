package hello;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonUtils {
	public static Person inputPerson(InputStream in){
		Scanner sc = new Scanner(in);
		Person p = new Person();
		String line;
		do {
			System.out.print("Input your SSN: ");
			line = sc.nextLine();
//			Pattern.matches("\\d{10}", line)
		} while(p.getIdNumber() > 0);
		return null;
	}

	public static void main(String[] args) {
		

	}

}
