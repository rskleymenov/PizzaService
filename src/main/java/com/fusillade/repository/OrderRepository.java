package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Order;

public interface OrderRepository {

	Order findById(int id);

	Order save(Order order);

	void delete(int id);

	List<Order> getAll();
}
