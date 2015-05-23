package invoicing.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class PositionTest {
	private static final double BOOK_PRICE = 20.3;
	private static final double PRECISION = 1E-5;
	private static final Item item1 = new Item(12, "Thinking in Java", BOOK_PRICE);
	private static final BookItem bookItem1 = 
		new BookItem(12, "Thinking in Java", BOOK_PRICE, 
			"Bruce Eckel", "Prentice Hall", 2007);
	
	private double quantity;
	private double price;
	private double expectedTotal;
	
	@Parameters(name = "{index}: GCD({0}+{1})={2}")
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[][] { 
            {3, 28.50, 85.5},
            {1, 19.8, 19.8},
            {10, 17.0000000000000001, 170}
       });
    }
    

	public PositionTest(double quantity, double price, double expectedTotal) {
		this.quantity = quantity;
		this.price = price;
		this.expectedTotal = expectedTotal;
	}


    @Ignore
	@Test
	public void testPositionConstructorWithoutPrice_BookItemWithPrice_PositionTakesPriceFromBookItem() {
		//Call behavior
		Position<BookItem> pos = new Position<>(1, bookItem1, 3);
		
		//Assert successful
		assertEquals("Price not set from Item", BOOK_PRICE, pos.getPrice(), PRECISION);
	}

    @Ignore
	@Test
	public void testGetTotal_3Books18$5_return55$5() {
		//Setup
		Position<BookItem> pos = new Position<>(1, bookItem1, 3, 18.5);
		
		//Call behaviour
		double actual = pos.getTotal();
		
		//Assert successful
		assertEquals(55.5, actual, PRECISION);
		
	}

	@Test
	public void testGetTotal_PriceAndQuantityFromParamets_returnTotalFromParameters() {
		//Setup
		Position<BookItem> pos = new Position<>(1, bookItem1, quantity, price);
		
		//Call behaviour
		double actualTotal = pos.getTotal();
		
		//Assert successful
		assertEquals(expectedTotal, actualTotal, PRECISION);
		
	}

}
