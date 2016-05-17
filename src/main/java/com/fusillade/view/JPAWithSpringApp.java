/*package com.fusillade.view;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.entity.Customer;
import com.fusillade.service.AddressService;
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
			AddressService addressService = applicationContext.getBean(AddressService.class);
			
			Pizza pizza = new Pizza("new", 55d, PizzaType.Meat);
			pizza = pizzaService.save(pizza);
			Map<Pizza, Integer> pizzas = new HashMap<>();
			pizzas.put(pizza, 2);
			Customer customer = new Customer();
			customer.setName("ROMAN"); customer.setSurname("KLMN");
			customer = customerService.save(customer);
			
			
			DiscountCard discountCard = new DiscountCard(40d, customer);
			discountService.save(discountCard);
			
			Customer customer = new Customer("123new", "das", Arrays.asList(addressService.findById(16)));
			

			customerService.save(customer);

			
			
		}catch (Exception e) {
				e.printStackTrace();
		} finally {
			applicationContext.close();
			repContext.close();
		}
	}
}
*/