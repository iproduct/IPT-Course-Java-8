package hello;

public class Person {
	public static final int NONE = 0;
	public static final int STUDENT = 1;
	public static final int EMPLOYEE = 2;
	public static final int SELF_EMPLOYED = 3;
	
	private long idNumber;
	private String name;
	private int age;
	private String address;
	private String phone;
	private int ocupation;
		
	public Person() {}

	public Person(long id, String name, int age, String address, String phone,
			int ocupation) {
		idNumber = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.ocupation = ocupation;
	}
	
	public long getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(long newId) {
		idNumber = newId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getOcupation() {
		return ocupation;
	}

	public void setOcupation(int ocupation) {
		this.ocupation = ocupation;
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
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (idNumber != other.idNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [idNumber=" + idNumber + ", name=" + name + ", age="
				+ age + ", address=" + address + ", phone=" + phone
				+ ", ocupation=" + ocupation + "]";
	}

	public static void incrementAgeBy10(Person p) {
		p.age += 10;
	}
	
}
