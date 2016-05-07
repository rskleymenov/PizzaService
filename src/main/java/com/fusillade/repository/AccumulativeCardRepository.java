package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.discounts.impl.DiscountCard;

public interface AccumulativeCardRepository {
	
	DiscountCard findById(int id);

	DiscountCard save(DiscountCard accumulativeCard);

	void delete(int id);

	List<DiscountCard> getAll();
}
