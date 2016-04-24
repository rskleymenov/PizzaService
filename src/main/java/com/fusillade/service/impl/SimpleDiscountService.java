package com.fusillade.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Order;
import com.fusillade.service.AccumulativeCardService;
import com.fusillade.service.DiscountService;

@Service
public class SimpleDiscountService implements DiscountService {

	private AccumulativeCardService accumulativeCardService;
	private Set<Discount> discountSet = new LinkedHashSet<>();

	@Autowired
	public SimpleDiscountService(AccumulativeCardService accumulativeCardService) {
		super();
		this.accumulativeCardService = accumulativeCardService;
	}

	@Override
	public Double calculateDiscount(Order order) {
		Double totalDiscount = 0d;
		Double cardDiscount = 0d;
		totalDiscount = calculateTotalDiscount(order, totalDiscount);
		cardDiscount = calculateAccumulativeCardDiscount(order.getTotalPrice(), order.getCustomer());
		return totalDiscount + cardDiscount;
	}

	private Double calculateAccumulativeCardDiscount(Double totalPrice, Customer customer) {
		AccumulativeCard accumulativeCard = accumulativeCardService.getCardByCustomer(customer);
		if (accumulativeCardService != null && accumulativeCard != null) {
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

}
