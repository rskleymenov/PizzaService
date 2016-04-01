package roman.kleimenov.domain.impl;

import java.util.List;

import roman.kleimenov.domain.Discount;

public class MaxPricePizzaDiscount implements Discount {
	private final static double PIZZA_PERCENT_DISCOUNT = 0.3;
	private final static int PIZZA_TRESHOLD = 4;
	private List<Pizza> listOfPizzas;

	public MaxPricePizzaDiscount() {
		super();
	}

	public MaxPricePizzaDiscount(List<Pizza> listOfPizzas) {
		super();
		this.listOfPizzas = listOfPizzas;
	}

	@Override
	public Double calculateDiscount() {
		Double maxPriceOfPizza = 0d;
		Double discount = 0d;

		if (listOfPizzas.isEmpty())
			return 0d;

		maxPriceOfPizza = getMaxPriceOfPizza(maxPriceOfPizza);

		if (listOfPizzas.size() > PIZZA_TRESHOLD) {
			discount = maxPriceOfPizza * PIZZA_PERCENT_DISCOUNT;
		}
		return discount;
	}

	private Double getMaxPriceOfPizza(Double maxPriceOfPizza) {
		for (Pizza pizza : listOfPizzas) {
			Double pizzaPrice = pizza.getPrice();
			if (maxPriceOfPizza <= pizzaPrice) {
				maxPriceOfPizza = pizzaPrice;
			}
		}
		return maxPriceOfPizza;
	}

	public List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public void setListOfPizzas(List<Pizza> listOfPizzas) {
		this.listOfPizzas = listOfPizzas;
	}
}
