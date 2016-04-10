package library.controller;

import library.model.Person;

public class PersonController {

	public static void main(String[] args) {
		Person p1 = new Person(8210733456L, "Ivan Petrov", false);
//		Person p2 = new Person(8210733456L, "Ivan Petrov", false);
		Person p2 = new Person(8210733456L, "Dimitar Dimitrov", 29, false,
				"dimitar@gmail.com", "+3592 3244354");
		System.out.println("Same persons: " + p1.equals(p2));

	}

}
