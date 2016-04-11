package com.fusillade.repository;

import com.fusillade.domain.entity.Pizza;

public interface PizzaRepository {
	Pizza getPizzaByID(int id);
}
