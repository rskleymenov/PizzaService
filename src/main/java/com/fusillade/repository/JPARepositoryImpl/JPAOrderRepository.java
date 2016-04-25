package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.fusillade.domain.entity.Order;
import com.fusillade.repository.OrderRepository;

public class JPAOrderRepository implements OrderRepository {
	private EntityManager em;

	public JPAOrderRepository() {
	}

	public JPAOrderRepository(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Order findById(int id) {
		return em.find(Order.class, id);
	}

	@Override
	public void create(Order newOrder) {
		em.getTransaction().begin();
		em.persist(newOrder);
		em.getTransaction().commit();
	}

	@Override
	public void update(Order order) {
		em.getTransaction().begin();
		em.merge(order);
		em.getTransaction().commit();

	}

	@Override
	public void delete(int id) {
		Order order = findById(id);
		em.getTransaction().begin();
		em.remove(order);
		em.getTransaction().commit();

	}

	@Override
	public List<Order> getAll() {
		return em.createQuery("select o from Order o", Order.class).getResultList();
	}

}
