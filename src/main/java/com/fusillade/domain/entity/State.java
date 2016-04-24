package com.fusillade.domain.entity;

import com.fusillade.domain.entity.impl.Order;

public interface State {
	boolean changeState(Order order);
}
