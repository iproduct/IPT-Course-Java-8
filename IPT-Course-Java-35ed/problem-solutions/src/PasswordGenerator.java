import java.util.Random;

public class PasswordGenerator {
	
	public static String generatePassword(String allowedCharacters, int numCharacters){
		char[] chars = allowedCharacters.toCharArray();
		Random rand = new Random();
		// 1. Initialize accumulator BEFORE loop
		StringBuilder password = new StringBuilder("");
		for(int i = 0; i < numCharacters; i++){
			// 2. Accumulate values INSIDE loop
			password.append(chars[rand.nextInt(chars.length)]);
		}
		// 3. Return accumulated result AFTER loop
		return password.toString();
	}

	public static void main(String[] args) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
		System.out.println(generatePassword(chars, 12));
	}
}
