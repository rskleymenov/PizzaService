package com.fusillade.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.domain.entity.impl.Address;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Pizza;

public class JPAWithoutSpring {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Pizza pizza = new Pizza("Hot meat", 100.25, PizzaType.Meat);
		
		Address address = new Address("Kyiv", "K-18");
		
		Customer customer = new Customer("Roman", "Kleimenov");
		try {
			em.getTransaction().begin();
			em.persist(pizza);
			em.persist(address);
			em.persist(customer);
			em.getTransaction().commit();
		} finally {
			em.close();
			entityManagerFactory.close();
		}
	}
}
