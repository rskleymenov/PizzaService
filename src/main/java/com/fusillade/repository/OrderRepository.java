package com.fusillade.repository;

import com.fusillade.domain.entity.Order;

public interface OrderRepository {
	int saveOrder(Order newOrder);
}
