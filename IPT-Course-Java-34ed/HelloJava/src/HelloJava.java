import java.util.Date;
import java.util.Scanner;

public class HelloJava {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now + ": Hello from Java 8!");
		Scanner sc = new Scanner(System.in);
		System.out.println("What's your name?");
//		String name = sc.nextLine();
//		System.out.println("Hello " + name + ", from Java 8!");
		
		String myName;
		myName = new String("Ivan Petrov");
		System.out.println(myName);
//		String name = "Ivan Petrov";
		
		Person person = new Person(8210733456L, "Ivan Petrov", false);
		Person p2 = new Person(7512733476L, "Dimitar Dimitrov", 29, false,
				"dimitar@gmail.com", "+3592 3244354");
//		person.idNumber = 8210733456L;
//		person.name = "Ivan Petrov";
//		person.age = 33;
//		person.female = false;
//		person.email = "ivan.petrov@abv.bg";
//		person.phone = "+3592 4444322";
		
		System.out.println("Person Id: " + person.getIdNumber()
			+ ": " + person.getName()
			+ ", age: " + person.getAge()			
		);
		
		System.out.println(p2);
		
		
	}

}
