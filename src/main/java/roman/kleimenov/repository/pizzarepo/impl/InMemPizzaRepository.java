package roman.kleimenov.repository.pizzarepo.impl;

import java.util.ArrayList;
import java.util.List;

import roman.kleimenov.domain.enums.PizzaType;
import roman.kleimenov.domain.impl.Pizza;
import roman.kleimenov.repository.pizzarepo.PizzaRepository;

public class InMemPizzaRepository implements PizzaRepository {

	private static List<Pizza> listOfPizzas = new ArrayList<>();

	static {
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
