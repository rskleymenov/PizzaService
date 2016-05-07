package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Order;
import com.fusillade.repository.OrderRepository;

@Repository
@Transactional
public class JPAOrderRepository implements OrderRepository {
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public Order findById(int id) {
		return em.find(Order.class, id);
	}


	@Override
	public Order save(Order order) {
		return em.merge(order);
	}

	@Override
	public void delete(int id) {
		Order order = findById(id);
		em.remove(order);
	}

	@Override
	public List<Order> getAll() {
		return em.createQuery("select o from Order o", Order.class).getResultList();
	}

}
