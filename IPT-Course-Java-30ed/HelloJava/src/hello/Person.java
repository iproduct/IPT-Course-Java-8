package hello;

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
	
	public Person(long id, String name, String address, int age) {
		idNumber = id;
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	public String toString(){
		return "Id Number: " + idNumber
			+ ", Name: " + name
			+ ", Address: " + address
			+ ", Age: " + age;
	}
}
