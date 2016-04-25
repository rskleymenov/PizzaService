package com.fusillade.view;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.domain.entity.impl.Address;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Order;
import com.fusillade.domain.entity.impl.Pizza;

public class JPAWithoutSpring {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		Pizza pizza = new Pizza("Hot meat", 100.25, PizzaType.Meat);

		Address address = new Address("PERSIST_ADDRESS", "ADR");

		Customer roma = new Customer();
		roma.setName("Romka");
		roma.setSurname("Romka");
		roma.setAddresses(Arrays.asList(address));
		Customer katya = new Customer();
		katya.setName("Katya");
		katya.setSurname("Katyha");
		katya.setAddresses(Arrays.asList(address));
		address.setCustomers(Arrays.asList(roma, katya));

		Customer customer = new Customer("Roman", "Kleimenov");
		Address address1 = new Address("1", "ADR");
		Address address2 = new Address("2", "ADR");
		customer.setAddresses(Arrays.asList(address1, address2));
		
		AccumulativeCardDiscount accumulativeCard1 = new AccumulativeCardDiscount(5d);
		AccumulativeCardDiscount accumulativeCard2 = new AccumulativeCardDiscount(55d);
		accumulativeCard1.setCustomer(customer);
		accumulativeCard2.setCustomer(customer);
		
		customer.setAccumulativeCards(Arrays.asList(accumulativeCard1, accumulativeCard2));
		
		
		Order order = new Order(customer, 15d, 15d, address2);
		try {
			em.getTransaction().begin();
			em.persist(customer);
			em.getTransaction().commit();
		} finally {
			em.close();
			entityManagerFactory.close();
		}
	}
}
