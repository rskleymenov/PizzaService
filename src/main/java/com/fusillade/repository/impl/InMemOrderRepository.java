package com.fusillade.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fusillade.domain.entity.impl.Order;
import com.fusillade.repository.OrderRepository;

@Repository
public class InMemOrderRepository implements OrderRepository {

	private List<Order> orders = new ArrayList<>();

	@Override
	public int saveOrder(Order newOrder) {
		orders.add(newOrder);
		return newOrder.getId();
	}

}
