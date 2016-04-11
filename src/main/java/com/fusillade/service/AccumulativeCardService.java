package com.fusillade.service;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.entity.Customer;

public interface AccumulativeCardService {

	void addAccumulativeCardToCustomer(Customer customer, AccumulativeCard accumulativeCard);

	AccumulativeCard getCardByCustomer(Customer customer);

}