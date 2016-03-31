package roman.kleimenov.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import roman.kleimenov.domain.enums.OrderStatus;
import roman.kleimenov.domain.enums.PizzaType;
import roman.kleimenov.domain.impl.AccumulativeCardTaskImplementation;
import roman.kleimenov.domain.impl.Address;
import roman.kleimenov.domain.impl.Customer;
import roman.kleimenov.domain.impl.MaxPricePizzaDiscount;
import roman.kleimenov.domain.impl.Pizza;

public class OrderTest {

	@Test
	public void newOrderMustHaveNewState() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 1000d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);
		assertEquals(OrderStatus.NEW, order.getOrderStatus());
	}

	@Test
	public void getOrderPriceShouldReturn60dot55() {
		List<Pizza> listOfPizzas = new ArrayList<>();

		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");

		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 1000d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);

		assertEquals(Double.valueOf(60.55), order.getOrderPrice());
	}

	@Test
	public void getOrderPriceShouldReturn81dot5() {
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
		Order order = new Order(customer, listOfPizzas, discount);

		assertEquals(Double.valueOf(81.5d), order.getOrderPrice());
	}

	@Test
	public void changeCurrentOrderMustReturnTrue() {
		List<Pizza> listOfPizzas = new ArrayList<>();

		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));

		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);

		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
		assertTrue(order.changeCurrentOrder(listOfPizzas));
	}

	@Test
	public void changeCurrentOrderMustReturnFalse() {
		List<Pizza> listOfPizzas = new ArrayList<>();

		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));

		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);

		order.setInProgressOrderStatus();
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));

		assertFalse(order.changeCurrentOrder(listOfPizzas));
	}

	@Test
	public void setInProgressOrderStatusShouldReturnTrue() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);

		assertTrue(order.setInProgressOrderStatus());
	}

	@Test
	public void setInProgressOrderStatusShouldReturnFalse() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);
		order.setCanceledOrderStatus();

		assertFalse(order.setInProgressOrderStatus());
	}

	@Test
	public void setCanceledOrderStatusShouldReturnTrueByFirstCondition() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);

		assertTrue(order.setCanceledOrderStatus());
	}

	@Test
	public void setCanceledOrderStatusShouldReturnTrueBySecondCondition() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);
		order.setInProgressOrderStatus();

		assertTrue(order.setCanceledOrderStatus());
	}

	@Test
	public void setCanceledOrderStatusShouldReturnFalse() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);
		order.setInProgressOrderStatus();
		order.setDoneOrderStatus();
		assertFalse(order.setCanceledOrderStatus());
	}

	@Test
	public void setDoneOrderStatusShouldReturnTrue() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);
		order.setInProgressOrderStatus();
		assertTrue(order.setDoneOrderStatus());
	}

	@Test
	public void setDoneOrderStatusShouldReturnFalse() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount(listOfPizzas);
		Order order = new Order(customer, listOfPizzas, discount);
		assertFalse(order.setDoneOrderStatus());
	}

}
