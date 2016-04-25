package com.fusillade.domain.discounts;

import com.fusillade.domain.entity.impl.Customer;

public interface AccumulativeCard {
	void addMoney(Double money);
	Double getSum();
	Double calculateCardDiscount(Double totalCostOfOrder);
	void setCustomer(Customer customer);
}
