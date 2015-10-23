package hellojava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class MagicNumbersTest {


	@Test
	public void testDigitsToDecimalNumber() {
		int[] sampleDigits = { 4, 3, 7, 5 };
		int result = MagicNumbers.digitsToDecimalNumber(sampleDigits);
		assertEquals(4375, result);
	}

	@Test
	public void testIsContainingAllDigitsTrue() {
		// Setup
		int number = 210;
		int[] otherDigits = { 1, 0, 2 };
		// Test
		boolean result = MagicNumbers.isDigitsPermutation(number, otherDigits, otherDigits.length);
		// Assertion
		assertTrue("Expected true but was false", result);
	}


	@Test
	public void testIsContainingAllDigitsFalse() {
		// Setup
		int number = 250;
		int[] otherDigits = { 1, 0, 2 };
		// Test
		boolean result = MagicNumbers.isDigitsPermutation(number, otherDigits, otherDigits.length);
		// Assertion
		assertFalse("Expected false but was true", result);
	}
	
	@Test 
	public void testContainsTrue(){
		//Setup
		int[] a= {3,4,5,7,1,0};
		int elem = 0;
		//Call
		int position = MagicNumbers.contains(a, elem);
		//Test
		assertEquals("Element exists but not found", 5, position);		
	}

	@Test 
	public void testContainsFalse() {
		//Setup
		int[] a= {3,4,5,7,1,0};
		int elem = 2;
		//Call
		int position = MagicNumbers.contains(a, elem);
		//Test
		assertEquals("Element not exists but reported found", -1, position);		
	}

}
