package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;

public interface AccumulativeCardRepository {
	
	AccumulativeCardDiscount findById(int id);

	void create(AccumulativeCardDiscount accumulativeCard);

	void update(AccumulativeCardDiscount accumulativeCard);

	void delete(int id);

	List<AccumulativeCardDiscount> getAll();
}
