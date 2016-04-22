package com.fusillade.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.repository.impl.JDBCPizzaDAO;

public class SpringJDBCApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("rep.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "AppContext.xml" }, repContext);
		
		JDBCPizzaDAO jDBCPizzaDAO = (JDBCPizzaDAO) applicationContext.getBean("JDBCPizzaDAO");
		System.out.println("ALL ==========================================");
		for(Pizza pizzas : jDBCPizzaDAO.getAll()) {
			System.out.println(pizzas);
		}
		
		System.out.println("ADDED NEW ==========================================");
		Pizza pizza = new Pizza("new", 1d, PizzaType.Sea);
		jDBCPizzaDAO.create(pizza);
		for(Pizza pizzas : jDBCPizzaDAO.getAll()) {
			System.out.println(pizzas);
		}
		
		System.out.println("SAASASD " + jDBCPizzaDAO.getPizzaByID(1));
		System.out.println("CHANGED NEW ==========================================");
		
		pizza.setName("newnew");
		jDBCPizzaDAO.update(pizza);
		for(Pizza pizzas : jDBCPizzaDAO.getAll()) {
			System.out.println(pizzas);
		}
		applicationContext.close();

	}

}
