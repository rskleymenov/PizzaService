package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query query = em.createQuery("select c from Customer c LEFT JOIN FETCH c.addresses a LEFT JOIN FETCH c.orders ord WHERE c.id = :id");
		query.setParameter("id", id);
		return (Customer) query.getSingleResult();
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
	@Transactional(readOnly = true)
	public List<Customer> getAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}
}
