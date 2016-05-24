package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fusillade.domain.entity.Customer;
import com.fusillade.repository.CustomerRepository;

@Repository
public class JPACustomerRepository implements CustomerRepository {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Customer findById(int id) {
		List<Customer> customers = em
				.createQuery(
						"select c from Customer c LEFT JOIN FETCH c.orders a WHERE c.id = :id", Customer.class)
				.setParameter("id", id).getResultList();
		if (customers.isEmpty()) {
			return null;
		}
		return customers.get(0);
	}

	@Override
	public Customer save(Customer customer) {
		return em.merge(customer);
	}

	@Override
	public void delete(int id) {
		Customer customer = findById(id);
		em.remove(customer);
	}

	@Override
	public List<Customer> getAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}
}
