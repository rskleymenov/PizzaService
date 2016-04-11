package com.fusillade.domain.discounts;

public interface AccumulativeCard {
	void addMoney(Double money);
	Double getSum();
	Double calculateCardDiscount(Double totalCostOfOrder);
}
