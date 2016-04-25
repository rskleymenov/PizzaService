package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.repository.AccumulativeCardRepository;

public class JPAAccumulativeCardRepository implements AccumulativeCardRepository{
	private EntityManager em;

	public JPAAccumulativeCardRepository() {
	}

	public JPAAccumulativeCardRepository(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public AccumulativeCardDiscount findById(int id) {
		return em.find(AccumulativeCardDiscount.class, id);
	}

	@Override
	public void create(AccumulativeCardDiscount accumulativeCard) {
		em.getTransaction().begin();
		em.persist(accumulativeCard);
		em.getTransaction().commit();		
	}

	@Override
	public void update(AccumulativeCardDiscount accumulativeCard) {
		em.getTransaction().begin();
		em.merge(accumulativeCard);
		em.getTransaction().commit();	
		
	}

	@Override
	public void delete(int id) {
		AccumulativeCardDiscount accumulativeCard = findById(id);
		em.getTransaction().begin();
		em.remove(accumulativeCard);
		em.getTransaction().commit();	
		
	}

	@Override
	public List<AccumulativeCardDiscount> getAll() {
		return em.createQuery("select a from AccumulativeCardDiscount a", AccumulativeCardDiscount.class).getResultList();
	}
	
}
