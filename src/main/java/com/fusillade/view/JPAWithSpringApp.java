package com.fusillade.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.service.CustomerService;
import com.fusillade.service.DiscountService;
import com.fusillade.service.OrderService;
import com.fusillade.service.PizzaService;

public class JPAWithSpringApp {
	public static void main(String args[]) {
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryMySQLContext.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "AppContext.xml" }, repContext);
		
		
		try {
			
			OrderService orderService = applicationContext.getBean(OrderService.class);
			PizzaService pizzaService = applicationContext.getBean(PizzaService.class);
			CustomerService customerService = applicationContext.getBean(CustomerService.class);
			DiscountService discountService = applicationContext.getBean(DiscountService.class);
			
			Pizza pizza = new Pizza("new", 55d, PizzaType.Meat);
			pizza = pizzaService.save(pizza);
			Map<Pizza, Integer> pizzas = new HashMap<>();
			pizzas.put(pizza, 2);
			Customer customer = new Customer();
			customer.setName("ROMAN"); customer.setSurname("KLMN");
			customer = customerService.save(customer);
			
			
			DiscountCard discountCard = new DiscountCard(40d, customer);
			discountService.save(discountCard);
			
			Order order = orderService.placeNewOrder(customer, new Address(), pizzas);
			
			order = orderService.findById(order.getId());
			System.out.println(order);
			orderService.setOrderInProgressState(order);
			System.out.println(order);
			orderService.applyDiscountsToOrder(order);
			orderService.setOrderInDoneState(order);
			System.out.println(order);
			System.out.println(order.getCustomer().getAccumulativeCard());
			
		}catch (Exception e) {
				e.printStackTrace();
		} finally {
			applicationContext.close();
			repContext.close();
		}
	}
}
