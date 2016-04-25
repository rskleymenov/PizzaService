package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.fusillade.domain.entity.Customer;
import com.fusillade.repository.CustomerRepository;

public class JPACustomerRepository implements CustomerRepository {
	private EntityManager em;

	public JPACustomerRepository() {
	}

	public JPACustomerRepository(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Customer findById(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public void create(Customer customer) {
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
	}

	@Override
	public void update(Customer customer) {
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();

	}

	@Override
	public void delete(int id) {
		Customer customer = findById(id);
		em.getTransaction().begin();
		em.remove(customer);
		em.getTransaction().commit();

	}

	@Override
	public List<Customer> getAll() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}
}
