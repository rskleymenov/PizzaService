package com.fusillade.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.domain.entity.impl.Address;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Order;
import com.fusillade.domain.entity.impl.Pizza;

public class JPAWithoutSpring {
	public static Customer findById(EntityManager em, int id) {
		em.getTransaction().begin();
		Customer customer = em.find(Customer.class, id);
		em.getTransaction().commit();
		return customer;
	}
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		Pizza pizza1 = new Pizza("Hot meat", 100.25, PizzaType.Meat);
		Pizza pizza2 = new Pizza("Hot fish", 10.25, PizzaType.Sea);
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
		
		AccumulativeCard accumulativeCard1 = new AccumulativeCardDiscount(5d);
		AccumulativeCard accumulativeCard2 = new AccumulativeCardDiscount(55d);
		accumulativeCard1.setCustomer(customer);
		accumulativeCard2.setCustomer(customer);
		
		customer.setAccumulativeCards(Arrays.asList(accumulativeCard1, accumulativeCard2));
		
		try {
			em.getTransaction().begin();
			em.persist(pizza1);
			em.persist(pizza2);
			em.persist(address);
			em.persist(customer);
			Order order = new Order(10d, 5d, customer, address);
			Map<Pizza, Integer> pizzasInOrder = new HashMap<Pizza, Integer>();
			pizzasInOrder.put(pizza1, 5);
			pizzasInOrder.put(pizza2, 3);
			order.setPizzasInOrder(pizzasInOrder);
			em.persist(order);
			em.getTransaction().commit();
		} finally {
			em.close();
			entityManagerFactory.close();
		}
		
		
	}
}
