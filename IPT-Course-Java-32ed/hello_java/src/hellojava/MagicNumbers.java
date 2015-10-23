package hellojava;

import java.util.Arrays;

public class MagicNumbers {
	public static final int NUMBER_DIGITS = 4;

	public static void main(String[] args) {
		for (int d0 = 1; d0 < 10; d0++) {
			for (int d1 = 0; d1 < 10; d1++) {
				for (int d2 = 0; d2 < 10; d2++) {
					for (int d3 = 0; d3 < 10; d3++) {
						int[] d = new int[4];
						d[0] = d0;
						d[1] = d1;
						d[2] = d2;
						d[3] = d3;
						int n = digitsToDecimalNumber(d);
						int[] otherDigits = new int[d.length - 1];
						int[] otherDigits2 = new int[d.length - 2];
						// Test 1 digit
						for (int i = 0; i < d.length; i++) {
							if (d[i] > 0 && n % d[i] == 0) {
								int result = n / d[i];
								int otherDigitsNumber = 0;
								for (int j = 0; j < d.length; j++)
									if (j != i)
										otherDigits[otherDigitsNumber++] = d[j];
								if (isDigitsPermutation(result, otherDigits, otherDigitsNumber))
									System.out.println(n);

								// Test 2 digits
								for (int j = 0; j < otherDigitsNumber; j++) {
									int divider = 10 * d[i] + otherDigits[j];
									int result2 = n / divider; 
									if (n % divider == 0) {
										int otherDigitsNumber2 = 0;
										for (int k = 0; k < otherDigitsNumber; k++)
											if (k != j)
												otherDigits2[otherDigitsNumber2++] = otherDigits[k];
										if(n == 1827 && divider == 21) {
											System.out.println("!!!!!!! " + result2);											
										}
										if (isDigitsPermutation(result2, otherDigits2, otherDigitsNumber2))
											System.out.println(n);

									}
									
									divider = d[i] + 10*otherDigits[j];
									result2 = n / divider; 
									if (otherDigits[j] != 0 && n % divider == 0) {
										int otherDigitsNumber2 = 0;
										for (int k = 0; k < otherDigitsNumber; k++)
											if (k != j)
												otherDigits2[otherDigitsNumber2++] = otherDigits[k];
										if(n == 1827 && divider == 21) {
											System.out.println("+++++++ " + result2);											
										}
										if (isDigitsPermutation(result2, otherDigits2, otherDigitsNumber2))
											System.out.println(n);

									}
								}
							}
						}
					}
				}
			}
		}

	}

	static boolean isDigitsPermutation(int number, int[] otherDigits, int otherDigitsNumber) {
		int[] digits = decimalNumberToDigits(number);
		if (digits.length != otherDigitsNumber)
			return false;
		for (int i = 0; i < otherDigitsNumber; i++) {
			int position;
			if ((position = contains(digits, otherDigits[i])) >= 0) // digit
																	// found
				digits[position] = -1;
			else
				return false;
		}
		return true;
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

	static int contains(int[] a, int elem) {
		for (int i = 0; i < a.length; i++)
			if (a[i] == elem)
				return i; // return position
		return -1;
	}

}
