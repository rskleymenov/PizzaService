package com.fusillade.domain.discounts.impl;

import org.junit.Test;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;

import static org.junit.Assert.*;

public class AccumulativeCardTaskImplementationTest {

	@Test
	public void addMoneyShoudAdd10() {
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount();
		accumulativeCard.addMoney(10d);
		assertEquals(Double.valueOf(10d), accumulativeCard.getSum());
	}
	
	@Test
	public void addMoneyShoudReturn0() {
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount();
		assertEquals(Double.valueOf(0d), accumulativeCard.getSum());
	}
	
	@Test 
	public void calculateCardDiscountShoudReturn1Dot5() {
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount();
		accumulativeCard.setSum(100d);
	    assertEquals(Double.valueOf(1.5d), accumulativeCard.calculateCardDiscount(5d));
	}
	
	@Test 
	public void calculateCardDiscountShoudReturn1() {
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount();
		accumulativeCard.setSum(10d);
	    assertEquals(Double.valueOf(1d), accumulativeCard.calculateCardDiscount(5d));
	}
	
	@Test
	public void calculateCardDiscountShoudReturn0() {
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount();
		accumulativeCard.setSum(10d);
	    assertEquals(Double.valueOf(0d), accumulativeCard.calculateCardDiscount(0d));
	}
	
	@Test(expected = NullPointerException.class)
	public void calculateCardDiscoundShoudRiseException() {
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount();
		accumulativeCard.setSum(10d);
	    accumulativeCard.calculateCardDiscount(null);
	}
}
