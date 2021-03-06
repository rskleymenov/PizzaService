package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Pizza;

public interface PizzaRepository {
	
	Pizza findById(int id);

	Pizza save(Pizza pizza);

	void delete(int id);

	List<Pizza> getAll();

}
