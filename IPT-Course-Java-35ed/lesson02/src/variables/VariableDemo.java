package variables;

import java.util.Date;

public class VariableDemo {

	public static void main(String[] args) {
		String name = new String("Trayan Iliev");
		Date currentDate = new Date();
		System.out.println(currentDate + ": "  
				+ getSalutation(name));
	}
	
	public static String getSalutation(String person) {
		return "Hi, " + person + "!";
	}
	

}
