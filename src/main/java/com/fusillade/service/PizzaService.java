package com.fusillade.service;

import java.util.List;

import com.fusillade.domain.entity.Pizza;

public interface PizzaService {
	
	Pizza findById(int id);

	Pizza save(Pizza pizza);

	void delete(int id);

	List<Pizza> getAll();
	
}
