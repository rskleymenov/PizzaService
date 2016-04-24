package com.fusillade.service;

import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.entity.impl.Order;

public interface DiscountService {
	
	Double calculateDiscount(Order order);
	
	boolean addDiscounts(Discount... discounts);
	
	boolean deleteDiscount(Discount discount);
}
