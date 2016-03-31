package roman.kleimenov.repository.orderrepo;

import roman.kleimenov.domain.Order;

public interface OrderRepository {
	int saveOrder(Order newOrder);
}
