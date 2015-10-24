package hellojava;

import java.util.Arrays;

import setutils.IntArrayCombinationsGenerator;
import setutils.IntArrayPermutationsGenerator;

public class MagicNumbers2 {
	public static final int NUMBER_DIGITS = 6;
	private static IntArrayCombinationsGenerator cGen = new IntArrayCombinationsGenerator(new int[NUMBER_DIGITS], 1);
	private static IntArrayPermutationsGenerator pGen = new IntArrayPermutationsGenerator(new int[NUMBER_DIGITS]);

	public static void main(String[] args) {
		System.out.println(Arrays.toString(subtractArrays(new int[]{3,5,2,8},
		new int[]{8,2,5,3})));
		 
		int[] dividerDigits, restDigits, nextPermutation;
		for (int n = (int)Math.pow(10,NUMBER_DIGITS-1); n < (int)Math.pow(10,NUMBER_DIGITS); n++) {
			int[] digits = decimalNumberToDigits(n);
			for (int k = 1; k <= NUMBER_DIGITS / 2; k++) {
				cGen.reset(digits, k);
				combinations_loop:
				while ((dividerDigits = cGen.getNextCombination()) != null) {
					restDigits = subtractArrays(digits, dividerDigits);

					pGen.reset(dividerDigits);
					while ((nextPermutation = pGen.getNextPermutation()) != null) {
						int divider = digitsToDecimalNumber(nextPermutation);
						if (divider > 0 && n % divider == 0
								&& subtractArrays(decimalNumberToDigits(n / divider), restDigits).length == 0) {
							System.out.println(n + " = " + divider + " * " + n / divider);
							break combinations_loop;
						}
					}
				}

			}
		}
	}

	static int digitsToDecimalNumber(int[] d) {
		int n = 0;
		int tenPower = 1;
		for (int i = d.length - 1; i >= 0; i--) {
			n += d[i] * tenPower;
			tenPower *= 10;
		}
		return n;
	}

	static int[] decimalNumberToDigits(int number) {
		int[] resultDigits = new int[NUMBER_DIGITS];
		int numberDigits = 0;
		while (number > 0) {
			resultDigits[numberDigits++] = number % 10;
			number /= 10;
		}
		int[] result = new int[numberDigits];
		for (int i = 0; i < numberDigits; i++)
			result[i] = resultDigits[numberDigits - 1 - i];
		return result;
	}

	static int[] subtractArrays(int[] a, int[] b) {
		int number = a.length;
		boolean[] deleted = new boolean[a.length]; 
		
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (!deleted[j] && a[j] == b[i]) {
					deleted[j] = true;
					number--;
					break;
				}
			}
		}
		int[] result = new int[number];
		for (int j = 0; j < deleted.length; j++) {
			if (!deleted[j])
				result[--number] = a[j];
		}
		return result;
	}

}
