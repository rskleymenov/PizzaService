package com.fusillade.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.repository.AccumulativeCardRepository;
import com.fusillade.service.DiscountService;

@Service
public class SimpleDiscountService implements DiscountService {
	
	@Autowired
	private AccumulativeCardRepository accumulativeCardRepository;

	private Set<Discount> discountSet = new LinkedHashSet<>();

	@Override
	public Double calculateDiscount(Order order) {
		Double totalDiscount = 0d;
		Double cardDiscount = 0d;
		totalDiscount = calculateTotalDiscount(order, totalDiscount);
		cardDiscount = calculateAccumulativeCardDiscount(order.getTotalPrice(), order.getCustomer());
		return totalDiscount + cardDiscount;
	}

	private Double calculateAccumulativeCardDiscount(Double totalPrice, Customer customer) {
		AccumulativeCard accumulativeCard = customer.getAccumulativeCard();
		if (accumulativeCard != null) {
			return accumulativeCard.calculateCardDiscount(totalPrice);
		}
		return 0d;
	}

	private Double calculateTotalDiscount(Order order, Double totalDiscount) {
		for (Discount discount : discountSet) {
			totalDiscount += discount.calculateDiscount(order);
		}
		return totalDiscount;
	}

	@Override
	public boolean addDiscounts(Discount... discounts) {
		if (discounts != null)
			if (discounts.length != 0) {
				for (Discount discount : discounts) {
					discountSet.add(discount);
				}
				return true;
			}
		return false;
	}

	@Override
	public boolean deleteDiscount(Discount discount) {
		return discountSet.remove(discount);
	}

	@Override
	public DiscountCard findById(int id) {
		return accumulativeCardRepository.findById(id);
	}

	@Override
	public DiscountCard save(DiscountCard accumulativeCard) {
		return accumulativeCardRepository.save(accumulativeCard);
	}

	@Override
	public void delete(int id) {
		accumulativeCardRepository.delete(id);
	}

	@Override
	public List<DiscountCard> getAll() {
		return accumulativeCardRepository.getAll();
	}

}
