package com.fusillade.repository.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.repository.InMemRepositoryImpl.InMemPizzaRepository;

public class InMemPizzaRepositoryTest {

	private static List<Pizza> listOfPizzas = new ArrayList<>();

	@Before
	public void init() {
		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
	}
	
	@Test
	public void getPizzaByIDShoudReturnSecondPizza() {
		InMemPizzaRepository inMemPizzaRepository = new InMemPizzaRepository();
		inMemPizzaRepository.init();
		assertEquals(new Pizza(2, "Original", 20.50, PizzaType.Sea), inMemPizzaRepository.findById(2));
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getPizzaByIDShoudRiseException() {
		 new InMemPizzaRepository().findById(0);
	}
}
