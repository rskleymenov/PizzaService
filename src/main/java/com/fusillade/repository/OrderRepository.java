package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Order;

public interface OrderRepository {

	Order findById(int id);

	void create(Order newOrder);

	void update(Order order);

	void delete(int id);

	List<Order> getAll();
}
