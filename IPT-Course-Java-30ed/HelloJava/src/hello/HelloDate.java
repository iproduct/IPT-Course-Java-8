package hello;

import java.util.Date;
import static hello.Person.*;

public class HelloDate {

	public static void main(String[] args) {
		System.out.println("Hello from Java 8 at "
				+ new Date());
		String s = new String("Hello");
		String name = "Trayan";
		System.out.printf("Hello, %s!\n\n", name);
	
		// Create Person instance
//		Person p1 = new Person();
//		p1.idNumber = 87081243211L;
//		p1.name = "Ivan Petrov";
//		p1.age  = 35;
		
		// Create Person using constructor with arguments
		Person p1 = new Person(23434345435L, "Petar Georgiev", "Plovdiv", 38);
		Person p2 = new Person(23434345435L, "Petar Georgiev", "Plovdiv", 38);
		p2.setAge(39);
		incrementAgeBy10(p2);
		System.out.println(p2.equals("11"));
	}

}
