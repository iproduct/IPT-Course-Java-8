import java.util.Date;
import java.util.Scanner;

public class HelloJava {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now + ": Hello from Java 8!");
		Scanner sc = new Scanner(System.in);
		System.out.println("What's your name?");
		String name = sc.nextLine();
		System.out.println("Hello " + name + ", from Java 8!");

	}

}
