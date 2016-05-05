package com.fusillade.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;
import com.fusillade.service.OrderService;

public class JPAWithSpringApp {
	public static void main(String args[]) {
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryMySQLContext.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "AppContext.xml" }, repContext);
		
		
		try {
/*			OrderRepository orderRepository = (OrderRepository) applicationContext.getBean("JPAOrderRepository");
			PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("JPAPizzaRepository");
			AddressRepository addressRepository = (AddressRepository) applicationContext.getBean("JPAAddressRepository");*/
			
			OrderService orderService = (OrderService) applicationContext.getBean("simpleOrderService");
			PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("JPAPizzaRepository");
			
			Customer customer = new Customer("Roman2", "Kleimenov2");
			Address address = new Address("Parkova2", "Kyiv2");
			
			Pizza pizza1 = pizzaRepository.findById(1);
			Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
			pizzas.put(pizza1, 2);
			
			orderService.placeNewOrder(customer, address, pizzas);
		}catch (Exception e) {
				e.printStackTrace();
		} finally {
			applicationContext.close();
			repContext.close();
		}
	}
}
