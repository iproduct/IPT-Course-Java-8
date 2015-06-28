package hello;

import java.util.Date;

public class HelloDate {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("Today is: " + date);
		Person p = new Person(750724123L, "Ivan Petrov", 38, "Sofia 1000","(+3592) 973 38 38");
		System.out.println(p);
	}

}
