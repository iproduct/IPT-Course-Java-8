package hello;

/** 
 * Models a physical person
 * 
 * @author admin
 * @version 1.0
 * @since 1.0
 * 
 * @see PersonController
 * @see PersonController#inputPerson()
 * 
 */
public class Person {
	private String name;
	private int age;
	private String address = "Sofia 1000";
	private long idNumber;
	
	public Person() {
	}
	
	public Person(long id, String name) {
		idNumber = id;
		this.name = name;
	}
	
	/**
	 * Constructs new person with all attributes
	 * @param id person Id number
	 * @param name person name
	 * @param address person home address
	 * @param age person age
	 */
	public Person(long id, String name, String address, int age) {
		idNumber = id;
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public String toString(){
		return "Id Number: " + idNumber
			+ ", Name: " + name
			+ ", Address: " + address
			+ ", Age: " + age;
	}
	
	public static void incrementAgeBy10(Person p){
		p.age = p.age + 10;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idNumber ^ (idNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return (obj instanceof Person && idNumber == ((Person) obj).idNumber) ?
			true : false;
	}
	
	
}
