package com.fusillade.view;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.repository.JPARepositoryImpl.JPAAccumulativeCardRepository;

public class JPAWithoutSpring {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = entityManagerFactory.createEntityManager();
		

		Customer customer = new Customer("Roman", "Kleimenov");
		Address address1 = new Address("1", "ADR");
		Address address2 = new Address("2", "ADR");
		customer.setAddresses(Arrays.asList(address1, address2));

		AccumulativeCardDiscount accumulativeCard1 = new AccumulativeCardDiscount(5d);
		AccumulativeCardDiscount accumulativeCard2 = new AccumulativeCardDiscount(55d);
		accumulativeCard1.setCustomer(customer);
		accumulativeCard2.setCustomer(customer);

		customer.setAccumulativeCards(Arrays.asList(accumulativeCard1, accumulativeCard2));

		try {
			for (AccumulativeCardDiscount accumulativeCardDiscount : new JPAAccumulativeCardRepository().getAll()){
				System.out.println(accumulativeCardDiscount);
			}
		} finally {
			em.close();
			entityManagerFactory.close();
		}

	}
}
