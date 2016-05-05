package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;

@Repository
public class JPAPizzaRepository extends JPA_GENERIC_SUKA implements PizzaRepository {
	
	@Override
	public Pizza findById(int id) {
		return em.find(Pizza.class, id);
	}

	@Override
	public void create(Pizza pizza) {
		em.persist(pizza);
	}

	@Override
	public void update(Pizza pizza) {
		em.merge(pizza);
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
