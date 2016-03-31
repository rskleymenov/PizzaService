package roman.kleimenov.service;

import java.util.ArrayList;
import java.util.List;

import roman.kleimenov.domain.Discount;
import roman.kleimenov.domain.Order;
import roman.kleimenov.domain.impl.Customer;
import roman.kleimenov.domain.impl.Pizza;
import roman.kleimenov.repository.orderrepo.OrderRepository;
import roman.kleimenov.repository.orderrepo.impl.InMemOrderRepository;
import roman.kleimenov.repository.pizzarepo.PizzaRepository;
import roman.kleimenov.repository.pizzarepo.impl.InMemPizzaRepository;

public class SimpleOrderService implements OrderService {

	OrderRepository orderRepository = new InMemOrderRepository();
	PizzaRepository pizzaRepository = new InMemPizzaRepository();



	@Override
	public Order placeNewOrder(Customer customer, Discount discount, Integer... pizzasID) {

		checkNumberOfPizzas(pizzasID);

		List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
		Order newOrder = createOrder(customer, pizzas, discount);

		orderRepository.saveOrder(newOrder); // set Order Id and save Order to
												// in-memory list
		return newOrder;
	}

	private void checkNumberOfPizzas(Integer... pizzasID) {
		if (!(pizzasID.length >= 1 && pizzasID.length <= 10))
			throw new IllegalArgumentException();
	}

	private Order createOrder(Customer customer, List<Pizza> pizzas, Discount discount) {
		discount.setListOfPizzas(pizzas);
		Order newOrder = new Order(customer, pizzas, discount);
		return newOrder;
	}

	private List<Pizza> pizzasByArrOfId(Integer... pizzasID) {
		List<Pizza> pizzas = new ArrayList<>();

		for (Integer id : pizzasID) {
			pizzas.add(pizzaRepository.getPizzaByID(id)); // get Pizza from
															// predifined
															// in-memory
		}
		return pizzas;
	}

	public Double getOrderPrice(Order order) {
		return order.getOrderPrice();
	}

	public boolean changeOrder(Order order, Integer... pizzasID) {
		return order.changeCurrentOrder(pizzasByArrOfId(pizzasID));
	}

	public boolean setOrderInProgressState(Order order) {
		return order.setInProgressOrderStatus();
	}

	public boolean setOrderInCanceledState(Order order) {
		return order.setCanceledOrderStatus();
	}

	public boolean setOrderInDoneState(Order order) {
		return order.setDoneOrderStatus();
	}
}
