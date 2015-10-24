package points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointUtilities {
	public static final int MAX_POINTS = 100;
	private static Scanner sc = new Scanner(System.in);

	public static Point inputPoint(double minX, double maxX, double minY, double maxY) {
		Point point = new Point();

		// Input X
		boolean isValid = false;
		do {
			System.out.print("X: ");
			String input = sc.nextLine();
			try {
				point.setX(Double.parseDouble(input));
				isValid = true;
			} catch (NumberFormatException e) {
				System.err.println("Invalid X coordinate value. Please enter again!");
			}
		} while (!isValid);

		// Input Y
		isValid = false;
		do {
			System.out.print("Y: ");
			String input = sc.nextLine();
			try {
				point.setY(Double.parseDouble(input));
				isValid = true;
			} catch (NumberFormatException e) {
				System.err.println("Invalid Y coordinate value. Please enter again!");
			}
		} while (!isValid);
		return point;
	}

	public static Point[] parsePoints(String input) {
		Pattern p = Pattern.compile(
		"\\s*\\(\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*\\)\\s*");
		Matcher m = p.matcher(input);
		List<Point> points = new ArrayList<> ();
		while(m.find()) {
			Point point = new Point(Double.parseDouble(m.group(1)), 
					Double.parseDouble(m.group(2)));
			points.add(point);
//			for(int j = 0; j <= m.groupCount(); j++) {
//				System.out.println(m.group(j) + " : " + m.start(j) + " - " + m.end(j));
//			}
		}
//		String[] pointsStr = input.split("\\s*[\\(\\)]\\s*[\\(\\)\\s]*\\s*");
//		Point[] points = new Point[pointsStr.length - 1];
//		int i = 0;
//		for (String pStr : pointsStr) {
//			if (pStr.length() > 0) {
//				String[] numbersStr = pStr.split("\\s*,\\s*");
//				Point p = new Point(Double.parseDouble(numbersStr[0]), Double.parseDouble(numbersStr[1]));
//				points[i++] = p;
//			}
//		}
		return points.toArray(new Point[]{});
	}

	public static void main(String[] args) {
		// System.out.println(inputPoint(-100, 100, -100, 100));
		System.out.println(
		Arrays.toString(parsePoints("   (  1.2  , 3.5  )   	(4.7, 9.8)  (18.2,1.5)")));
	}

}
