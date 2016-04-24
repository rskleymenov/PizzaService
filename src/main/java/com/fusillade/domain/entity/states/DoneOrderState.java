package com.fusillade.domain.entity.states;

import com.fusillade.domain.entity.State;
import com.fusillade.domain.entity.impl.Order;

public class DoneOrderState implements State {

	@Override
	public boolean changeState(Order order) {
		if (order.getState().getClass().equals(InProgressOrderState.class)) {
			order.setState(this);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DoneOrderState";
	}
	
	public boolean equals(Object object) {
		return this.getClass() == object.getClass();
	}

}
