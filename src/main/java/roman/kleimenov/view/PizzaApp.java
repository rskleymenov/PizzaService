package roman.kleimenov.view;

import roman.kleimenov.domain.Discount;
import roman.kleimenov.domain.Order;
import roman.kleimenov.domain.impl.AccumulativeCardTaskImplementation;
import roman.kleimenov.domain.impl.Address;
import roman.kleimenov.domain.impl.Customer;
import roman.kleimenov.domain.impl.MaxPricePizzaDiscount;
import roman.kleimenov.service.OrderService;
import roman.kleimenov.service.SimpleOrderService;

public class PizzaApp {
	public static void main(String args[]) {
		Address address = new Address(1, "K-18", "Vokzalna", "Kyiv", "12334");
		AccumulativeCardTaskImplementation accumulativeCard = new AccumulativeCardTaskImplementation(1, 50d);
		Customer customer = new Customer(1, "Roman", address, accumulativeCard);
		Discount discount = new MaxPricePizzaDiscount();
		Order order;
		
		OrderService orderService = new SimpleOrderService();
		
		order = orderService.placeNewOrder(customer, discount, 1, 2, 3, 4, 5);
		
		System.out.println("sum on card before: " + accumulativeCard.getSum());
		System.out.println("Order cost: " + order.getOrderPrice());
		System.out.println("sum on card after without done status: " + accumulativeCard.getSum());
		
		orderService.setOrderInProgressState(order);
		System.out.println("Order cost: " + order.getOrderPrice());
		System.out.println("sum on card after InprogressStatus: " + accumulativeCard.getSum());
		
		orderService.setOrderInDoneState(order);
		System.out.println("Order cost: " + order.getOrderPrice());
		System.out.println("sum on card after  done status: " + accumulativeCard.getSum());
		
	}
}

