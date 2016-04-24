package com.fusillade.service;

import java.util.List;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.entity.impl.Customer;
import com.fusillade.domain.entity.impl.Order;
import com.fusillade.domain.entity.impl.Pizza;

public interface OrderService {

	List<Pizza> pizzasByArrOfId(Integer... pizzasID);

	Order placeNewOrder(Customer customer, Integer... pizzasID);

	boolean addDiscounts(Discount... discounts);

	boolean deleteDiscount(Discount discount);

	boolean changeOrder(Order order, Integer... pizzasID);

	boolean setOrderInProgressState(Order order);

	boolean setOrderInCanceledState(Order order);

	boolean setOrderInDoneState(Order order);

	void applyDiscountsToOrder(Order newOrder);
	
	public void addAccumulativeCardToCustomer(Customer customer, AccumulativeCard accumulativeCard);
	
	public AccumulativeCard getCardByCustomer(Customer customer);

}