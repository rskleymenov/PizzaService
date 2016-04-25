package com.fusillade.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.domain.entity.impl.Address;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Order;
import com.fusillade.domain.entity.impl.Pizza;
import com.fusillade.service.OrderService;

public class SimpleOrderServiceTest {
	ConfigurableApplicationContext applicationContext;
	OrderService orderService;
	
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
	public void placeNewOrderShouldReturnSameObject() {
		List<Pizza> listOfPizzas = new ArrayList<>();

		listOfPizzas.add(new Pizza(1, "Vegan", 19.50, PizzaType.Vegetarian));
		listOfPizzas.add(new Pizza(2, "Original", 20.50, PizzaType.Sea));
		listOfPizzas.add(new Pizza(3, "AllMeat", 10.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(4, "MeatBalls", 45.00, PizzaType.Meat));
		listOfPizzas.add(new Pizza(5, "SeaGod", 5.00, PizzaType.Sea));

		Address address = new Address("Kyiv", "12334");
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount(50d);
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order expectedOrder = new Order(customer, listOfPizzas);

		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		Order actualOrder = orderService.placeNewOrder(customer, 1, 2, 3, 4, 5);
		actualOrder.setId(2);
		expectedOrder.setId(2);
		assertEquals(expectedOrder, actualOrder);
	}
	
	@Test
	public void setOrderInProgressStateShouldReturnTrue() {
		Address address = new Address("Kyiv", "12334");
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order order = orderService.placeNewOrder(customer, 1, 2);
		assertTrue(orderService.setOrderInProgressState(order));
	}
	
	@Test
	public void setOrderInProgressStateShouldReturnFalse() {
		Address address = new Address("Kyiv", "12334");
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order order = orderService.placeNewOrder(customer, 1, 2);
		orderService.setOrderInCanceledState(order);
		assertFalse(orderService.setOrderInProgressState(order));
	}
	
	@Test
	public void setOrderInCanceledShouldReturnTrue() {
		Address address = new Address("Kyiv", "12334");
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order order = orderService.placeNewOrder(customer, 1, 2);
		assertTrue(orderService.setOrderInCanceledState(order));
	}
	
	@Test
	public void setOrderInCanceledShouldReturnFalse() {
		Address address = new Address();
		address.setId(1);
		address.setCity("Kyiv");
		address.setStreet("K-18");
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order order = orderService.placeNewOrder(customer, 1, 2);
		orderService.setOrderInProgressState(order);
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount(50d);
		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		orderService.setOrderInDoneState(order);
		assertFalse(orderService.setOrderInCanceledState(order));
	}
	
	@Test
	public void setOrderInDoneStatusShouldReturnTrue() {
		Address address = new Address("Kyiv", "12334");
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order order = orderService.placeNewOrder(customer, 1, 2);
		orderService.setOrderInProgressState(order);
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount(50d);
		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		assertTrue(orderService.setOrderInDoneState(order));
	}
	
	@Test
	public void setOrderInDoneStatusShouldReturnFalse() {
		Address address = new Address("Kyiv", "12334");
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		Order order = orderService.placeNewOrder(customer, 1, 2);
		AccumulativeCardDiscount accumulativeCard = new AccumulativeCardDiscount(50d);
		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		assertFalse(orderService.setOrderInDoneState(order));
	}
	
	
	
}
