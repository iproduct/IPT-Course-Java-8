package setutils;

import java.util.Arrays;

public class IntArrayCombinationsGenerator {
	private int[] array;
	private int k;
	private int[] indices, combination;
	int position, index;

	public IntArrayCombinationsGenerator(int[] a, int klass) {
		array = a;
		k = klass;
		indices = new int[k];
		combination = new int[k];		
	}
	
	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
	public void reset(int[] a, int klass) {
		array = a;
		k = klass;
		indices = new int[k];
		combination = new int[k];	
		position = 0;
		index = 0;	
	}
	
	public boolean hasMoreCombinations() {
		return position >= 0;
	}
	
	public int[] getNextCombination(){
		while(true){
			if (position >= 0){
				if(index <= array.length - k + position) {
					indices[position] = index;
					if(position == k-1){
						index++;
						return getCombination();
					} else {
						index = indices[position++] + 1;
					}
				} else {
					position--;
					if(position >= 0)
						index = indices[position]+1;
				}
			} else {
				return null;
			}
		}
	}
	
	private int[] getCombination() {
		for(int i = 0; i < combination.length; i++)
			combination[i] = array[indices[i]];
		return combination;
	}

	public static void main(String[] args) {
		int[] a = {101, 102, 103, 104, 105};
		IntArrayCombinationsGenerator gen = new IntArrayCombinationsGenerator(a, 3);
		while(gen.hasMoreCombinations()) {
			System.out.println(Arrays.toString(gen.getNextCombination()));
		}
	}

}
