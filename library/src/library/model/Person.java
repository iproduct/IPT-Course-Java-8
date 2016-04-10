package library.model;

/**
 * This class models a real person with:
 * <ul>
 * <li> id number
 * <li> name
 * <li> contact data
 * </ul>
 * 
 * The method {@link #toString() toString} is overriden to
 * return person details as string.
 * 
 * @author Trayan iliev
 * @author tiliev@iproduct.org
 * @see HelloJava#main(String[])
 * @version 0.0.1
 */
public class Person extends Object {
	private long idNumber;
	private String name;
	private int age;
	private boolean female = true;
	private String email;
	private String phone;
	
	/**
	 * No argument constructor - initializes instance 
	 * with default values.
	 */
	public Person(){} //no arguments constructor
	
	/**
	 * Recuired arguments only constructor.
	 * @param idNumber identification number (EGN in BG)
	 * @param name person's real name
	 * @param isFemale person's sex - true by default
	 */
	public Person(long idNumber, String name, boolean isFemale) {
		this.idNumber = idNumber;
		this.name = name;
		this.female = isFemale;
	}

	/**
	 * Full arguments constructor.
	 * @param idNumber identification number (EGN in BG)
	 * @param name person's real name
	 * @param age person's age (optional attribute)
	 * @param female person's sex - true by default
	 * @param email person's email (optional attribute)
	 * @param phone person's phone (optional attribute)
	 * @since 0.0.1
	 */
	public Person(long idNumber, String name, int age, 
			boolean female, String email, String phone) {
		this.idNumber = idNumber;
		this.name = name;
		this.age = age;
		this.female = female;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * Returns person's id number as long value
	 * @return person's id number as long value
	 */
	public long getIdNumber(){ //accessor
		return idNumber;
	}
	
	/**
	 * Sets person's id number from long argument
	 * @param anIdNumber person's id number as long value
	 */
	public void setIdNumber(long anIdNumber){  //mutator
		idNumber = anIdNumber;
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
	public boolean isFemale() {
		return female;
	}
	public void setFemale(boolean female) {
		this.female = female;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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

	/**
	 * Returns string representation of person object
	 * {@inheritDoc} 
	 * 
	 * @see java.lang.Object#toString()
	 * @return string representation of person
	 */
	@Override
	public String toString() {
		return "Person [idNumber=" + idNumber + ", name=" + name 
				+ ", age=" + age + ", female=" + female + ", email="
				+ email + ", phone=" + phone + "]";
	}
	
	
}
