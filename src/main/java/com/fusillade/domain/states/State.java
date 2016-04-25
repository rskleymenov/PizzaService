package com.fusillade.domain.states;

import com.fusillade.domain.entity.Order;

public interface State {
	boolean changeState(Order order);
}
