package com.fusillade.service;

import java.util.Map;

import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;

public interface OrderService {
	
	Order placeNewOrder(Customer customer, Address orderAddress, Map<Pizza, Integer> pizzas);

	boolean addDiscounts(Discount... discounts);

	boolean deleteDiscount(Discount discount);

	boolean changeOrder(Order order, Map<Pizza, Integer> pizzas);

	boolean setOrderInProgressState(Order order);

	boolean setOrderInCanceledState(Order order);

	boolean setOrderInDoneState(Order order);

	Order applyDiscountsToOrder(Order newOrder);
	
	Order findById(int id);
	

}