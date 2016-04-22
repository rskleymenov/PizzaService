package com.fusillade.repository;

import com.fusillade.domain.entity.Pizza;

public interface PizzaDAO {
	public void insert(Pizza pizza);
	public Pizza getPizzaByID(int id);
}
