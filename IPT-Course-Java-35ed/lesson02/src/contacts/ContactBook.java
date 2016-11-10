package contacts;

/**
 * Main class for contact book project
 * @author Trayan Iliev
 * @version 0.0.1
 * @see Person
 */
public class ContactBook {

	public static void main(String[] args) {
		/* Create 2 persons
		 * and print them.
		 */
		Person p1 = new Person("George Smith", 27); // Create first person
		Person p2 = new Person("Samantha Smith", 25); // Create second person
		System.out.println(p1);
		System.out.println(p2);

	}

}
