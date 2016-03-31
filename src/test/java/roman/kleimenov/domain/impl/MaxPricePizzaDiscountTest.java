package roman.kleimenov.domain.impl;

import org.junit.Test;

import roman.kleimenov.domain.Discount;
import roman.kleimenov.domain.enums.PizzaType;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MaxPricePizzaDiscountTest {

	@Test
	public void calculateDiscountShoudReturnNotZeroValue() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original",20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
		
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		assertEquals(Double.valueOf(13.5), discount.calculateDiscount());
	}
	
	@Test
	public void calculateDiscountShoudReturnZero() {
		Discount discount = new MaxPricePizzaDiscount(new ArrayList<Pizza>());
		assertEquals(Double.valueOf(0), discount.calculateDiscount());
	}
	
	@Test(expected = NullPointerException.class)
	public void calculateDiscountShoudRiseException() {
		Discount discount = new MaxPricePizzaDiscount(null);
		discount.calculateDiscount();
	}
	
	@Test
	public void calculateDiscountMustReturn0() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		listOfPizzas.add(new Pizza(1, "Vegan", 20.00, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original",30.00, PizzaType.Sea));

		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		assertEquals(Double.valueOf(0), discount.calculateDiscount());
	}
	
}
