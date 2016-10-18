package contacts;

public class Person {
	private String name; //null is default value for Objects
	private int age; // 0 is default for int type
	
	public Person() {}
	
	public Person(String aName, int anAge){
		name = aName;
		age = anAge;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int newAge) {
		age = newAge;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.getName() + ", Age: " + this.getAge();
	}
	
}
