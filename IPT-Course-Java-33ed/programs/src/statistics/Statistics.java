package statistics;

public class Statistics {
	public static final long NUMBER_ITERATIONS = 10000000L;

	public static void main(String[] args) {
		double n;
		long count, sum = 0;
		for (long iter = 0; iter < NUMBER_ITERATIONS; iter++) {
			count = 0;
			do {
//				System.out.println(++count + " : " + n);
				count++;
				n = Math.random();
			} while (n < 0.9);
			sum += count;
		}
		//Calculate average
		System.out.println("Average is : " + (double)sum / NUMBER_ITERATIONS);
	}

}
