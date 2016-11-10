import java.util.Random;

public class Dice {

	public static void main(String[] args) {
		Random rand = new Random();
		int a = rand.nextInt(6) + 1;
		int b = rand.nextInt(6) + 1;
		int sum = a + b;
		System.out.println("Die 1: " + a);
		System.out.println("Die 2: " + b);
		System.out.println("Die sum is: " + sum);
//		if (a == b) {
//			System.out.println("Numbers are equal");
//			System.out.println("Same player is in turn.");
//		} else {
//			System.out.println("Numbers are different");
//			System.out.println("Other player is in turn.");
//		}
		System.out.println((a == b) ? "Numbers are equal" : "Numbers are different");

	}

}
