package com.fusillade.repository.InMemRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.repository.PizzaRepository;

public class InMemPizzaRepository implements PizzaRepository {

	private static List<Pizza> listOfPizzas = new ArrayList<>();

	public void init() { 
		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian)); 
		listOfPizzas.add(new Pizza(2, "Original",20.50, PizzaType.Sea)); 
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat)); 
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat)); 
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea)); 
	}
	 

	@Override
	public Pizza findById(int id) {
		int index = id - 1;
		return listOfPizzas.get(index);
	}

	public static List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public static void setListOfPizzas(List<Pizza> listOfPizzas) {
		InMemPizzaRepository.listOfPizzas = listOfPizzas;
	}


	@Override
	public void create(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Pizza> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
