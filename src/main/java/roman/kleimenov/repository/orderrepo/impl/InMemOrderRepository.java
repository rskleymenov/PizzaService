package roman.kleimenov.repository.orderrepo.impl;

import java.util.ArrayList;
import java.util.List;

import roman.kleimenov.domain.Order;
import roman.kleimenov.repository.orderrepo.OrderRepository;

public class InMemOrderRepository implements OrderRepository {
	
	private List<Order> orders = new ArrayList<>();

	@Override
	public int saveOrder(Order newOrder) {
		orders.add(newOrder);
		return newOrder.getId();
	}

}
