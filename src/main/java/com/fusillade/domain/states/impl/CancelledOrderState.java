package com.fusillade.domain.states.impl;

import com.fusillade.domain.entity.Order;
import com.fusillade.domain.states.State;

public class CancelledOrderState implements State {

	@Override
	public boolean changeState(Order order) {
		Class<?> clazz = order.getState().getClass();
		if (clazz.equals(NewOrderState.class)
				|| clazz.equals(InProgressOrderState.class)) {

			order.setState(this);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "CancelledOrderState";
	}

	public boolean equals(Object object) {
		return this.getClass() == object.getClass();
	}

}
