package points;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.Date;

public class Point {
	private double x, y; 
	
	public Point() {
		x = 0D;
		y = 0D;
	}

	public Point(double valX, double valY) {
		x = valX;
		y = valY;
	}
	
	@Override
	public boolean equals(Object o){
		return (o != null) && o instanceof Point
				&& ((Point)o).x == x && ((Point)o).y == y;
	}
	
	public static double calculateDistance(Point a, Point b){
		return sqrt(pow(a.x-b.x, 2) + pow(a.y-b.y, 2));
	}

	public static void main(String[] args) {
		Point p1 = new Point();
		Point p2 = new Point(3, 4);
		System.out.println(p2.equals(new Point(3, 4.5)));
		System.out.println(calculateDistance(p1, p2));

	}

}
