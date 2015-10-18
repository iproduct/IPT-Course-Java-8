package points;

import static java.lang.Math.*;

public class Point {
	private double x, y;

	public Point() {
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

//	@Override
//	public boolean equals(Object obj){
//		return x == ((Point)obj).x && y == ((Point)obj).y ;
//	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Point 
				&& x == ((Point) obj).x && y == ((Point) obj).y;
	}

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

	public void translate(double dx, double dy){
		x += dx;
		y += dy;
	}
	
	public static double calculateDistance(Point p1, Point p2) {
		return hypot(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public static void main(String[] args) {
		Point p1 = new Point();
		Point p2 = new Point(4, 3.5);
		Point p3 = new Point(4, 3.5);

		System.out.println(p2.equals("3.5"));
		
//		System.out.println(p1);
//		p2.translate(10, 10);
//		System.out.println(p2);
//		System.out.println("Distance is: " + calculateDistance(p1, p2));

	}

}
