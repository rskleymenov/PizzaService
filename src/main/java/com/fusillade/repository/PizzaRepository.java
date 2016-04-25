package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Pizza;

public interface PizzaRepository {
	
	Pizza findById(int id);

	void create(Pizza pizza);

	void update(Pizza pizza);

	void delete(int id);

	List<Pizza> getAll();

}
