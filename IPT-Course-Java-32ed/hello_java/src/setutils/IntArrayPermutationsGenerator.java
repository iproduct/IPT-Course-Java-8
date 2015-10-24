package setutils;

import java.util.Arrays;

public class IntArrayPermutationsGenerator {
	private int[] array;
	private int[] indices, permutation;
	int position, index;

	public IntArrayPermutationsGenerator(int[] a) {
		array = a;
		indices = new int[a.length];
		permutation = new int[a.length];		
	}
	
	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public void reset(int[] a) {
		array = a;
		indices = new int[a.length];
		permutation = new int[a.length];	
		position = 0;
		index = 0;	
	}
	
	public boolean hasMorePermutations() {
		return position >= 0;
	}
	
	public int[] getNextPermutation(){
		while(true){
			if (position >= 0){
				if(index < array.length) {
					indices[position] = index;
					if(position == array.length-1){
						index++;
						incrementIndexWhilePresent();
						return getPermutation();
					} else {
						position++;
						index = 0;
						incrementIndexWhilePresent();
					}
				} else { //backtracking
					if(--position >= 0) {
						index = indices[position]+1;
						incrementIndexWhilePresent();
					}
				}
			} else {
				return null;
			}
		}
	}

	private void incrementIndexWhilePresent() {
		outer:
		while(true) {
			for(int i = 0; i < position; i++)
				if(indices[i] == index) {
					index++;
					continue outer;
				}
			break;
		}
	}
	
	private int[] getPermutation() {
		for(int i = 0; i < permutation.length; i++)
			permutation[i] = array[indices[i]];
		return permutation;
	}

	public static void main(String[] args) {
		int[] a = {101, 102, 103, 104};
		IntArrayPermutationsGenerator gen = new IntArrayPermutationsGenerator(a);
		while(gen.hasMorePermutations()) {
			System.out.println(Arrays.toString(gen.getNextPermutation()));
		}
	}

}
