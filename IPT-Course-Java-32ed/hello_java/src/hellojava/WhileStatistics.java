package hellojava;

public class WhileStatistics {
	public static final int NUMBER_EXPERIMENTS = 1000000;
	
	public static void main(String[] args) {
		double n = 0;
		long sum = 0; //initialize sum
		for(int k = 0; k < NUMBER_EXPERIMENTS; k++){
			int i = 0;
			do {
//				System.out.println(++i + ": " + n);
				i++;
			} while((n = Math.random()) < 0.99);
//			System.out.println("i = " + i);
			sum += i;
		}
		System.out.println("Average: " + (double)sum / NUMBER_EXPERIMENTS);
	}
	
	
}

