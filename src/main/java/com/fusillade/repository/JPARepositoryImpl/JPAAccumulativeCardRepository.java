package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.repository.AccumulativeCardRepository;

@Repository
public class JPAAccumulativeCardRepository extends JPA_GENERIC_SUKA implements AccumulativeCardRepository{
	
	@Override
	@Transactional(readOnly = true)
	public AccumulativeCardDiscount findById(int id) {
		return em.find(AccumulativeCardDiscount.class, id);
	}

	@Override
	public void create(AccumulativeCardDiscount accumulativeCard) {
		em.persist(accumulativeCard);	
	}

	@Override
	public void update(AccumulativeCardDiscount accumulativeCard) {
		em.merge(accumulativeCard);
		
	}

	@Override
	public void delete(int id) {
		AccumulativeCardDiscount accumulativeCard = findById(id);
		em.remove(accumulativeCard);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccumulativeCardDiscount> getAll() {
		return em.createQuery("select a from AccumulativeCardDiscount a", AccumulativeCardDiscount.class).getResultList();
	}
	
}
