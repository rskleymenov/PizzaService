package com.fusillade.domain.discounts.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;

public class MaxPricePizzaDiscount implements Discount {
	private final static double PIZZA_PERCENT_DISCOUNT = 0.3;
	private final static int PIZZA_TRESHOLD = 4;

	@Override
	public Double calculateDiscount(Order order) {
		Double maxPriceOfPizza = 0d;
		Double discount = 0d;

		if (order.getListOfPizzas().isEmpty() || order.getListOfPizzas().size() <= PIZZA_TRESHOLD)
			return 0d;

		maxPriceOfPizza = getMaxPriceOfPizza(order, maxPriceOfPizza);

		discount = checkConditionOfCard(order, maxPriceOfPizza, discount);
		return discount;
	}

	private Double checkConditionOfCard(Order order, Double maxPriceOfPizza, Double discount) {
		if (order.getListOfPizzas().size() > PIZZA_TRESHOLD) {
			discount = maxPriceOfPizza * PIZZA_PERCENT_DISCOUNT;
		}
		return discount;
	}

	private Double getMaxPriceOfPizza(Order order, Double maxPriceOfPizza) {
		Iterator<Entry<Pizza, Integer>> it = order.getListOfPizzas().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> pair = (Map.Entry<Pizza, Integer>) it.next();
			Double pizzaPrice = pair.getKey().getPrice();
			if (maxPriceOfPizza <= pizzaPrice) {
				maxPriceOfPizza = pizzaPrice;
			}
		}
		return maxPriceOfPizza;
	}
}
