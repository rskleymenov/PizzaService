package roman.kleimenov.service;

import roman.kleimenov.domain.Discount;
import roman.kleimenov.domain.Order;
import roman.kleimenov.domain.impl.Customer;

public interface OrderService {

	Order placeNewOrder(Customer customer, Discount discount, Integer... pizzasID);
	Double getOrderPrice(Order order);
	boolean changeOrder(Order order, Integer... pizzasID);
	boolean setOrderInProgressState(Order order);
	boolean setOrderInCanceledState(Order order);
	boolean setOrderInDoneState(Order order);

}