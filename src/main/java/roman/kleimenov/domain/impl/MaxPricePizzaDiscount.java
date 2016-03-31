package roman.kleimenov.domain.impl;

import java.util.List;

import roman.kleimenov.domain.Discount;

public class MaxPricePizzaDiscount implements Discount {
	private int id;
	private final static double PIZZA_PERCENT_DISCOUNT = 0.3;
	private final static int PIZZA_TRESHOLD = 4;
	private List<Pizza> listOfPizzas;

	public MaxPricePizzaDiscount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaxPricePizzaDiscount(List<Pizza> listOfPizzas) {
		super();
		this.listOfPizzas = listOfPizzas;
	}

	@Override
	public Double calculateDiscount() {
		Double maxPriceOfPizza = 0d;
		Double discount = 0d;

		if (listOfPizzas.isEmpty()) return 0d;
			
		for (Pizza pizza : listOfPizzas) {
			Double pizzaPrice = pizza.getPrice();
			if (maxPriceOfPizza <= pizzaPrice) {
				maxPriceOfPizza = pizzaPrice;
			}
		}

		if (listOfPizzas.size() > PIZZA_TRESHOLD) {
			discount = maxPriceOfPizza * PIZZA_PERCENT_DISCOUNT;
		}
		return discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public void setListOfPizzas(List<Pizza> listOfPizzas) {
		this.listOfPizzas = listOfPizzas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((listOfPizzas == null) ? 0 : listOfPizzas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaxPricePizzaDiscount other = (MaxPricePizzaDiscount) obj;
		if (id != other.id)
			return false;
		if (listOfPizzas == null) {
			if (other.listOfPizzas != null)
				return false;
		} else if (!listOfPizzas.equals(other.listOfPizzas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaxPricePizzaDiscount [id=" + id + ", listOfPizzas=" + listOfPizzas + "]";
	}

}
