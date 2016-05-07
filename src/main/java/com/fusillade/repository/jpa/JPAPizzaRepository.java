package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;

@Repository
@Transactional
public class JPAPizzaRepository implements PizzaRepository {
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public Pizza findById(int id) {
		return em.find(Pizza.class, id);
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
	@Transactional(readOnly = true)
	public List<Pizza> getAll() {
		return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
	}

}
