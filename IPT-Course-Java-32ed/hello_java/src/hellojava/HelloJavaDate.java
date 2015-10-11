package hellojava;

import java.util.Date;
import java.util.Scanner;

public class HelloJavaDate {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String myName;
		System.out.println("What is your name?");
		myName = sc.nextLine();
		Date now = new Date();
		System.out.println(now + ": Hello, " + myName + " from JAVA 8!");
		

	}

}
