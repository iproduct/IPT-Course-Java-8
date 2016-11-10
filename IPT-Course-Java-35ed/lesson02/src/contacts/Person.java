package contacts;

/**
 * The class models persons details in a contact book 
 * project.
 * @author Trayan Iliev
 * @version 0.0.1
 * @see contacts.ContactBook
 */
public class Person {
	private String name; //null is default value for Objects
	private int age; // 0 is default for int type
	
	/**
	 * Default no-argument constructor
	 */
	public Person() {}
	
	/**
	 * Create Person with name and age
	 * @param aName the name of person
	 * @param anAge the age of person
	 */
	public Person(String aName, int anAge){
		name = aName;
		age = anAge;
	}
	
	/**
	 * Get the persons name
	 * @return the name of person
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Modify persons name
	 * @param newName new persons name
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Get the persons age
	 * @return the age of the person
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Modify persons age 
	 * @param newAge new age for the person
	 */
	public void setAge(int newAge) {
		age = newAge;
	}
	
	/**
	 * {@inheritDoc}
	 * Returns name and age.
	 */
	@Override
	public String toString() {
		return "Name: " + this.getName() + ", Age: " + this.getAge();
	}
	
}
