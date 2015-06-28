package hello;

public class Person {
	private long idNumber;
	private String name;
	private int age;
	private String address;
	private String phone;
	
	public Person(long id, String name, int age, String address, String phone) {
		idNumber = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}
	
	public String toString(){
		return "Person[Id Number: " + idNumber + ", Name: " + name + "]";
	}
}
