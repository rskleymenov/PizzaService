package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.impl.Pizza;

public interface PizzaRepository {
	
	Pizza getPizzaByID(int id);

	void create(Pizza pizza);

	void update(Pizza pizza);

	void delete(int id);

	List<Pizza> getAll();

}
