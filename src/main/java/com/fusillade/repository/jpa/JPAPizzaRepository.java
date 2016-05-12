package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;

@Repository
public class JPAPizzaRepository implements PizzaRepository {
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public Pizza findById(int id) {
		List<Pizza> pizzas =  em.createQuery("select p from Pizza p where p.id = :id", Pizza.class).setParameter("id", id).getResultList();
		if (pizzas.isEmpty()) {
			return null;
		}
		return pizzas.get(0);
	}

	@Override
	public Pizza save(Pizza pizza) {
		return em.merge(pizza);
	}

	@Override
	public void delete(int id) {
		Pizza pizza = findById(id);
		em.remove(pizza);
	}

	@Override
	public List<Pizza> getAll() {
		return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
	}

}
