package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Order;
import com.fusillade.repository.OrderRepository;

@Repository
public class JPAOrderRepository extends JPA_GENERIC_SUKA implements OrderRepository {

	@Override
	public Order findById(int id) {
		return em.find(Order.class, id);
	}

	@Override
	public void create(Order newOrder) {
		em.persist(newOrder);
	}

	@Override
	public void update(Order order) {
		em.merge(order);
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
