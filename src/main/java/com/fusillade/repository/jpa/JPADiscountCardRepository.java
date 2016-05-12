package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.repository.AccumulativeCardRepository;

@Repository
public class JPADiscountCardRepository implements AccumulativeCardRepository{
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public DiscountCard findById(int id) {
		List<DiscountCard> discounts =  em.createQuery("select d from DiscountCard d where d.id = :id", DiscountCard.class).setParameter("id", id).getResultList();
		if (discounts.isEmpty()) {
			return null;
		}
		return discounts.get(0);
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
	public List<DiscountCard> getAll() {
		return em.createQuery("select a from DiscountCard a", DiscountCard.class).getResultList();
	}
	
}
