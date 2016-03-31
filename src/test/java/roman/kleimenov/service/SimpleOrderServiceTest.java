package roman.kleimenov.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import roman.kleimenov.domain.Discount;
import roman.kleimenov.domain.Order;
import roman.kleimenov.domain.enums.PizzaType;
import roman.kleimenov.domain.impl.AccumulativeCardTaskImplementation;
import roman.kleimenov.domain.impl.Address;
import roman.kleimenov.domain.impl.Customer;
import roman.kleimenov.domain.impl.MaxPricePizzaDiscount;
import roman.kleimenov.domain.impl.Pizza;

public class SimpleOrderServiceTest {

	@Test
	public void placeNewOrderShouldReturnSameObject() {
		List<Pizza> listOfPizzas = new ArrayList<>();

		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
		
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order expectedOrder = new Order(customer, listOfPizzas, discount);
		
		OrderService orderService = new SimpleOrderService();
		Order actualOrder = orderService.placeNewOrder(customer, discount, 1, 2, 3, 4, 5);
		actualOrder.setId(2);
		expectedOrder.setId(2);
		assertEquals(expectedOrder, actualOrder);
	}
}
