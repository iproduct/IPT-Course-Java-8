
public class PointDemo {
	public Point findNearestNeighbour(Point origin, Point[] neighbours){
		if(neighbours.length == 0) return null;
		double minDistance = Double.MAX_VALUE;
		Point result = null;
		for(Point p: neighbours){
			double currentDistance = origin.distance(p);
			if(currentDistance < minDistance) {
				minDistance = currentDistance;
				result = p;
			}
		}	
		return result;
	}

	public static void main(String[] args) {
		PointDemo demo = new PointDemo();
		Point origin = new Point(0, 0);
		Point[] neighbours = { 
				new Point(5, 7),
				new Point(2, 6),
				new Point(1, 18),
				new Point(3, 5),
				new Point(2, 8),
		};
		Point nearest = 
			demo.findNearestNeighbour(origin, neighbours);
		System.out.println(nearest);
		System.out.println("Distance: " + origin.distance(nearest));

	}

}
