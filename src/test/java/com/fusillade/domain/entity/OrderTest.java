package com.fusillade.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.discounts.impl.MaxPricePizzaDiscount;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.domain.states.State;
import com.fusillade.domain.states.impl.CancelledOrderState;
import com.fusillade.domain.states.impl.DoneOrderState;
import com.fusillade.domain.states.impl.InProgressOrderState;
import com.fusillade.domain.states.impl.NewOrderState;
import com.fusillade.service.OrderService;

public class OrderTest {
/*	private static List<Pizza> listOfPizzas = new ArrayList<>();
	ConfigurableApplicationContext applicationContext;
	OrderService orderService;

	static {
		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
	}
	
	@Before
	public void init() {
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("rep.xml");
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "AppContext.xml" }, repContext);
		orderService = (OrderService) applicationContext.getBean("simpleOrderService");
	}
	
	@After
	public void close() {
		applicationContext.close();
	}

	@Test
	public void newOrderMustHaveNewState() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		
		assertEquals(new NewOrderState().getClass(), order.getState().getClass());
	}

	@Test
	public void getOrderPriceShouldReturn60dot55() {
		AccumulativeCard accumulativeCard = new AccumulativeCardDiscount(1000d);
		Customer customer = mock(Customer.class);
		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		Order order = orderService.placeNewOrder(customer, 1, 2, 3, 4, 5);
		orderService.addDiscounts(new MaxPricePizzaDiscount());
		orderService.applyDiscountsToOrder(order);
		
		assertEquals(Double.valueOf(56.5), order.getPriceWithDiscounts());
	}

	@Test
	public void getOrderPriceShouldReturn81dot5() {
		AccumulativeCard accumulativeCard = new AccumulativeCardDiscount(50d);
		Customer customer = mock(Customer.class);
		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		Order order = orderService.placeNewOrder(customer, 1, 2, 3, 4, 5);
		orderService.addDiscounts(new MaxPricePizzaDiscount());
		orderService.applyDiscountsToOrder(order);

		assertEquals(Double.valueOf(81.5d), order.getPriceWithDiscounts());
	}

	@Test
	public void changeCurrentOrderMustReturnTrue() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));
		
		assertTrue(order.changeCurrentOrder(listOfPizzas));
	}

	@Test
	public void changeCurrentOrderMustReturnFalse() {
		List<Pizza> listOfPizzas = new ArrayList<>();
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		State state = new InProgressOrderState();
		state.changeState(order);
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));

		assertFalse(order.changeCurrentOrder(listOfPizzas));
	}

	@Test
	public void setInProgressOrderStatusShouldReturnTrue() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		assertTrue(new InProgressOrderState().changeState(order));
	}

	@Test
	public void setInProgressOrderStatusShouldReturnFalse() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		State state = new CancelledOrderState();
		state.changeState(order);
		
		assertFalse(new InProgressOrderState().changeState(order));
	}

	@Test
	public void setCanceledOrderStatusShouldReturnTrueByFirstCondition() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		
		assertTrue(new CancelledOrderState().changeState(order));
	}

	@Test
	public void setCanceledOrderStatusShouldReturnTrueBySecondCondition() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);

		new InProgressOrderState().changeState(order);

		assertTrue(new CancelledOrderState().changeState(order));
	}

	@Test
	public void setCanceledOrderStatusShouldReturnFalse() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		new InProgressOrderState().changeState(order);
		new DoneOrderState().changeState(order);

		assertFalse(new CancelledOrderState().changeState(order));
	}

	@Test
	public void setDoneOrderStatusShouldReturnTrue() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		new InProgressOrderState().changeState(order);
		
		assertTrue(new DoneOrderState().changeState(order));
	}

	@Test
	public void setDoneOrderStatusShouldReturnFalse() {
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		Order order = new Order(50d, 50d, customer);
		
		assertFalse(new DoneOrderState().changeState(order));
	}*/

}
