package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;

public class JPAPizzaRepository implements PizzaRepository {
	private EntityManager em;

	public JPAPizzaRepository() {
	}

	public JPAPizzaRepository(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Pizza findById(int id) {
		return em.find(Pizza.class, id);
	}

	@Override
	public void create(Pizza pizza) {
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
	}

	@Override
	public void update(Pizza pizza) {
		em.getTransaction().begin();
		em.merge(pizza);
		em.getTransaction().commit();
	}

	@Override
	public void delete(int id) {
		Pizza pizza = findById(id);
		em.getTransaction().begin();
		em.remove(pizza);
		em.getTransaction().commit();
	}

	@Override
	public List<Pizza> getAll() {
		return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
	}

}
