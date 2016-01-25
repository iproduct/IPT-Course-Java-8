package statistics;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
	public static final long NUMBER_ITERATIONS = 100000000L;

	public static void main(String[] args) {
		double n;
		long count, sum = 0;
//		Map<Double, ByteBuffer> temp = new HashMap<>();
		
		for (long iter = 0; iter < NUMBER_ITERATIONS; iter++) {
			count = 0;
			
			do {
//				System.out.println(++count + " : " + n);
				count++;
				n = Math.random();
//				temp.put(n, ByteBuffer.allocate(1000));
			} while (n < 0.99);
			sum += count;
		}
		//Calculate average
		System.out.println("Average is : " + (double)sum / NUMBER_ITERATIONS);
	}

}
