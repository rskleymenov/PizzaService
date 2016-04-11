package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.entity.Customer;
import com.fusillade.service.AccumulativeCardService;
import com.fusillade.service.impl.SimpleAccumulativeCardService;

public class SimpleAccumulativeCardServiceTest {

	@Test
	public void getCardByCustomerShoudReturnAddedCard() {
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		Customer customer = mock(Customer.class);
		AccumulativeCard accumulativeCard = mock(AccumulativeCard.class);
		accumulativeCardService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		assertEquals(accumulativeCard, accumulativeCardService.getCardByCustomer(customer));
	}
}
