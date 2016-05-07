package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.repository.AccumulativeCardRepository;

@Repository
@Transactional
public class JPADiscountCardRepository implements AccumulativeCardRepository{
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public DiscountCard findById(int id) {
		return em.find(DiscountCard.class, id);
	}

	@Override
	public DiscountCard save(DiscountCard accumulativeCard) {
		return em.merge(accumulativeCard);
	}

	@Override
	public void delete(int id) {
		DiscountCard card = findById(id);
		em.remove(card);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DiscountCard> getAll() {
		return em.createQuery("select a from DiscountCard a", DiscountCard.class).getResultList();
	}
	
}
