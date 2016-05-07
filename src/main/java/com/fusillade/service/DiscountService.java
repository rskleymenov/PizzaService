package com.fusillade.service;

import java.util.List;

import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.domain.entity.Order;

public interface DiscountService {
	
	Double calculateDiscount(Order order);
	
	boolean addDiscounts(Discount... discounts);
	
	boolean deleteDiscount(Discount discount);
	
	//repo
	
	DiscountCard findById(int id);

	DiscountCard save(DiscountCard accumulativeCard);

	void delete(int id);

	List<DiscountCard> getAll();
}
