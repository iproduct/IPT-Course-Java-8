
public class Encryption {
	
	public static String encrypt(String data, int key) {
		char[] letters = data.toCharArray();
		for(int i = 0; i < letters.length; i++){
			letters[i] += key;
		}
		char[] result = new char[letters.length];
		for(int i = 0; i < letters.length; i++){
			result[(i + key) % letters.length] = letters[i];
		}
		return new String(result);
	}

	public static String decrypt(String data, int key) {
		char[] letters = data.toCharArray();
		for(int i = 0; i < letters.length; i++){
			letters[i] -= key;
		}
		char[] result = new char[letters.length];
		for(int i = 0; i < letters.length; i++){
			int diff = i - key;
			while(diff < 0){
				diff += letters.length;
			}
			result[diff % letters.length] = letters[i];
		}
		return new String(result);
	}

	public static void main(String[] args) {
		String data = "I use JAVA 8.";
		int key = 1456;
		String encrypted = encrypt(data, key);
		System.out.println(encrypted);
		String result = decrypt(encrypted, key);
		System.out.println(result);
	}

}
