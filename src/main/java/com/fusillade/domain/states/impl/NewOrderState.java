package com.fusillade.domain.states.impl;

import com.fusillade.domain.entity.Order;
import com.fusillade.domain.states.State;

public class NewOrderState implements State {

	@Override
	public boolean changeState(Order order) {
		if (order.getState() == null) {
			order.setState(this);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "NewOrderState";
	}

	public boolean equals(Object object) {
		return this.getClass() == object.getClass();
	}

}
