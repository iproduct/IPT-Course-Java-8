package hellojava;

public class Person {
	private String name;
	private int age;

	public String changeNameAndAge(String aName, int anAge) {
		this.name = aName;
		this.age = anAge;
		return name + ": " + age + " years";
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static void main(String[] args) {
		Person p1;
		p1 = new Person();
		p1.name = "Trayan";
		p1.age = 42;

		System.out.println(p1.name);
		System.out.println(p1.age);

		System.out.println(p1.changeNameAndAge("Ivan Petrov", 34));

		p1.setName("Dimitar Petkov");
		System.out.println(p1.getName());

		p1.setAge(24);
		System.out.println(p1.getAge());

	}

}
