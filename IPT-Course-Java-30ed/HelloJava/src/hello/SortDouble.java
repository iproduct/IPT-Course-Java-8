package hello;

import java.util.Arrays;

public class SortDouble {

	public static void main(String[] args) {
		double[] d = {
			2.14, 3.24, 1.23, 7.5, 0.3, 1.33, 1.2, 5.2	
		};
		System.out.println("Before sorting: " + Arrays.toString(d));
		Arrays.sort(d);
		System.out.println("After sorting: " + Arrays.toString(d));
	}

}
