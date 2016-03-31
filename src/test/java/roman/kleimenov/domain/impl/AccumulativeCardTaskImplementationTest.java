package roman.kleimenov.domain.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccumulativeCardTaskImplementationTest {

	@Test
	public void addMoneyShoudAdd10() {
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation();
		accumulativeCard.addMoney(10d);
		assertEquals(Double.valueOf(10d), accumulativeCard.getSum());
	}
	
	@Test
	public void addMoneyShoudReturn0() {
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation();
		assertEquals(Double.valueOf(0d), accumulativeCard.getSum());
	}
	
	@Test 
	public void calculateCardDiscountShoudReturn1Dot5() {
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation();
		accumulativeCard.setSum(100d);
	    assertEquals(Double.valueOf(1.5d), accumulativeCard.calculateCardDiscount(5d));
	}
	
	@Test 
	public void calculateCardDiscountShoudReturn1() {
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation();
		accumulativeCard.setSum(10d);
	    assertEquals(Double.valueOf(1d), accumulativeCard.calculateCardDiscount(5d));
	}
	
	@Test
	public void calculateCardDiscountShoudReturn0() {
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation();
		accumulativeCard.setSum(10d);
	    assertEquals(Double.valueOf(0d), accumulativeCard.calculateCardDiscount(0d));
	}
	
	@Test(expected = NullPointerException.class)
	public void calculateCardDiscoundShoudRiseException() {
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation();
		accumulativeCard.setSum(10d);
	    accumulativeCard.calculateCardDiscount(null);
	}
}
