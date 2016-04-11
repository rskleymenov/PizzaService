package com.fusillade.repository.impl;

import org.junit.Test;

import com.fusillade.repository.impl.InMemOrderRepository;

public class InMemOrderRepositoryTest {

	@Test(expected = NullPointerException.class)
	public void saveOrderShoudRiseNullPointerException() {
		new InMemOrderRepository().saveOrder(null);
	}
}
