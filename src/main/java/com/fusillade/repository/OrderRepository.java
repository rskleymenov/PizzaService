package com.fusillade.repository;

import com.fusillade.domain.entity.impl.Order;

public interface OrderRepository {
	int saveOrder(Order newOrder);
}
