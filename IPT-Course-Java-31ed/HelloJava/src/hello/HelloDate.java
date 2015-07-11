package hello;

import java.util.Date;
import static hello.Person.*;

public class HelloDate {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("Today is: " + date);
		Person p = new Person(750724123L, "Ivan Petrov", 38, 
				"Sofia 1000","(+3592) 973 38 38", EMPLOYEE);
		Person p2 = new Person(750724123L, "Ivan Petrov", 38, 
				"Sofia 1000","(+3592) 973 38 38", STUDENT);
		Person p3 = new Person(750724123L, "Ivan Petrov", 38, 
				"Sofia 1000","(+3592) 973 38 38", SELF_EMPLOYED);
		System.out.println("SSN:" +  p.getIdNumber());
//		p.setIdNumber(760724123L);
		System.out.println("SSN:" +  p.getIdNumber());
		p.setAddress("Plovdiv");
		p.setPhone("(+35932) 973 56 39");
		System.out.println(p);
		Person.incrementAgeBy10(p);
		System.out.println(!p2.equals(p));
	}

}
