package com.fusillade.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.entity.Customer;
import com.fusillade.service.AccumulativeCardService;

@Service
public class SimpleAccumulativeCardService implements AccumulativeCardService {
	private Map<Customer, AccumulativeCard> contextMap = new HashMap<>();

	public SimpleAccumulativeCardService() {

	}

	@Override
	public void addAccumulativeCardToCustomer(Customer customer, AccumulativeCard accumulativeCard) {
		contextMap.put(customer, accumulativeCard);
	}

	@Override
	public AccumulativeCard getCardByCustomer(Customer customer) {
		return contextMap.get(customer);
	}

}
