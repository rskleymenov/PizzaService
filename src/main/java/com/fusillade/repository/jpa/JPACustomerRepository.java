package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Customer;
import com.fusillade.repository.CustomerRepository;

@Repository
@Transactional
public class JPACustomerRepository implements CustomerRepository {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public Customer findById(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public void create(Customer customer) {
		em.persist(customer);
	}

	@Override
	public void update(Customer customer) {
		em.merge(customer);
	}

	@Override
	public void delete(int id) {
		Customer customer = findById(id);
		em.remove(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> getAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}
}
