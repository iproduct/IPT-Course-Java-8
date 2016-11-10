
public class FindNumber {
	
	public static int search(int[] a, int n) {
		int foundIndex = -1;
		the_loop_label:
		for(int i = 0; i < a.length; i++){
			if(n == a[i]) {
				foundIndex = i;
				break the_loop_label;
			}
		}
		return foundIndex;
	}
	
	public static long sum(int[] a){
		//TODO 
		return 0;
	}

	public static void main(String[] args) {
		int[] a = {45, 23, 12, 34, 25, 12, 56, 23, 67, 15};
		System.out.println(search(a, 67 ));
		
		System.out.println(sum(a));

	}

}
