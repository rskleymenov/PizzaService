package com.fusillade.repository.InMemRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import com.fusillade.domain.entity.Order;
import com.fusillade.repository.OrderRepository;

public class InMemOrderRepository implements OrderRepository {

	private List<Order> orders = new ArrayList<>();

	@Override
	public void create(Order newOrder) {
		orders.add(newOrder);
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
