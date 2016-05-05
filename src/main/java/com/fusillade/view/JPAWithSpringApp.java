package com.fusillade.view;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.repository.AddressRepository;
import com.fusillade.repository.OrderRepository;
import com.fusillade.repository.PizzaRepository;
import com.fusillade.repository.JPARepositoryImpl.JPAPizzaRepository;
import com.fusillade.service.OrderService;

public class JPAWithSpringApp {
	public static void main(String args[]) {
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryMySQLContext.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "AppContext.xml" }, repContext);
		repContext.refresh();
		
		
		try {
/*			OrderRepository orderRepository = (OrderRepository) applicationContext.getBean("JPAOrderRepository");
			PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("JPAPizzaRepository");
			
			AddressRepository addressRepository = (AddressRepository) applicationContext.getBean("JPAAddressRepository");
			*/
			
			Address address = new Address("street55", "city55");
			
			
			
			Customer customer = new Customer("newCust", "New");
			
/*			Order order = new Order(15d, 15d, customer);
			order.setAddress(address);
			orderRepository.create(order);
			
			System.out.println(pizzaRepository.findById(1));
			
			
			order = orderRepository.findById(1);
			System.out.println(pizzaRepository.findById(1));*/
			
			
			OrderService orderService = (OrderService) applicationContext.getBean("simpleOrderService");
			//address = addressRepository.findById(2);
			orderService.placeNewOrder(customer, address, 1);
		}catch (Exception e) {
				e.printStackTrace();
		} finally {
			applicationContext.close();
			repContext.close();
		}
	}
}
