package com.fusillade.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.repository.PizzaRepository;

@Repository
public class InMemPizzaRepository implements PizzaRepository {

	private static List<Pizza> listOfPizzas = new ArrayList<>();

	@PostConstruct
	public void init() {
		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original",20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
	}
	
	@Override
	public Pizza getPizzaByID(int id) {
		int index = id - 1;
		return listOfPizzas.get(index);
	}

}
