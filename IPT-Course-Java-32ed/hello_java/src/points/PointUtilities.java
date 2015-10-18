package points;

import java.util.Scanner;

public class PointUtilities {
	private static Scanner sc = new Scanner(System.in);
	
	public static Point inputPoint(double minX, double maxX, double minY, double maxY){
		Point point = new Point();
		
		// Input X
		boolean isValid = false;
		do {
			System.out.print("X: ");
			String input = sc.nextLine();
			try {
				point.setX(Double.parseDouble(input));
				isValid = true;
			} catch(NumberFormatException e) {
				System.err.println("Invalid X coordinate value. Please enter again!");
			}
		} while(!isValid);
		
		//Input Y
		isValid = false;
		do {
			System.out.print("Y: ");
			String input = sc.nextLine();
			try {
				point.setY(Double.parseDouble(input));
				isValid = true;
			} catch(NumberFormatException e) {
				System.err.println("Invalid Y coordinate value. Please enter again!");
			}
		} while(!isValid);
		return point;
	}

	public static void main(String[] args) {
		System.out.println(inputPoint(-100, 100, -100, 100));
	}

}
