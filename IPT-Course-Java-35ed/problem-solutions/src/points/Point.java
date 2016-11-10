package points;

public class Point {
	double x = 0, y = 0;
	
	public Point(double valX, double valY){
		x = valX;
		y = valY;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	
	
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
		return (obj != null) && (obj instanceof Point)
			&& Double.doubleToLongBits(x) == Double.doubleToLongBits(((Point) obj).x)
			&& Double.doubleToLongBits(y) == Double.doubleToLongBits(((Point) obj).y);
	}

	public static double claculateDistance(Point p1, Point p2) {
		double deltaX = p1.x - p2.x;
		double deltaY = p1.y - p2.y;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

	public static void main(String[] args) {
		Point p1  = new Point(1, 11.5);
		Point p2  = new Point(1, 11);
		System.out.println("p1 = " + p1);
		System.out.println("p1 = " + p1);
		System.out.println("p1 == p2 : " + p1.equals(null));
		
		System.out.println("distance = " + claculateDistance(p1, p2));

	}

}
