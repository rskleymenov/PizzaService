package com.fusillade.view;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.discounts.impl.MaxPricePizzaDiscount;
import com.fusillade.domain.entity.impl.Address;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Order;
import com.fusillade.service.OrderService;

public class PizzaApp {
	public static void main(String args[]) {
		
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("rep.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "AppContext.xml" }, repContext);
		
		OrderService orderService = (OrderService) applicationContext.getBean("simpleOrderService");
		
		Address address = new Address("Kyiv", "12334");
		AccumulativeCard accumulativeCard = new AccumulativeCardDiscount(0d);
		Customer customer = new Customer("Roman", "Kleimenov", Arrays.asList(address));
		orderService.addAccumulativeCardToCustomer(customer, accumulativeCard);

		Order order = orderService.placeNewOrder(customer, 1, 2, 3, 4, 5);
		orderService.addDiscounts(new MaxPricePizzaDiscount());
		orderService.applyDiscountsToOrder(order);

		System.out.println("Total price: " + order.getTotalPrice());
		System.out.println("Price with discount: " + order.getPriceWithDiscounts());
		System.out.println("Cash on card in progress: " + orderService.getCardByCustomer(customer).getSum());
		orderService.setOrderInProgressState(order);
		orderService.setOrderInDoneState(order);
		System.out.println("Cash on card in done: " + orderService.getCardByCustomer(customer).getSum());

		System.out.println("=============================================");

		order = orderService.placeNewOrder(customer, 1);
		orderService.applyDiscountsToOrder(order);
		System.out.println("Total price: " + order.getTotalPrice());
		System.out.println("Price with discount: " + order.getPriceWithDiscounts());
		System.out.println("Cash on card in progress: " + orderService.getCardByCustomer(customer).getSum());
		orderService.setOrderInProgressState(order);
		orderService.setOrderInDoneState(order);
		System.out.println("Cash on card in done: " + orderService.getCardByCustomer(customer).getSum());
		
		applicationContext.close();

	}
}
