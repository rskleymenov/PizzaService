package com.fusillade.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.states.State;
import com.fusillade.domain.states.impl.CancelledOrderState;
import com.fusillade.domain.states.impl.DoneOrderState;
import com.fusillade.domain.states.impl.InProgressOrderState;
import com.fusillade.repository.OrderRepository;
import com.fusillade.service.DiscountService;
import com.fusillade.service.OrderService;
import com.fusillade.service.PizzaService;

@Service
@Transactional
public class SimpleOrderService implements OrderService {
	OrderRepository orderRepository;
	PizzaService pizzaService;
	DiscountService discountService;

	public SimpleOrderService() {

	}

	@Autowired
	public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, DiscountService discountService) {
		super();
		this.orderRepository = orderRepository;
		this.pizzaService = pizzaService;
		this.discountService = discountService;
	}

	@Transactional(readOnly = true)
	private void checkNumberOfPizzas(Map<Pizza, Integer> pizzas) {
		Integer numOfPizzas = 0;
		Iterator<Entry<Pizza, Integer>> it = pizzas.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> pair = (Map.Entry<Pizza, Integer>) it.next();
			numOfPizzas += pair.getValue();
		}

		if (!(numOfPizzas >= 1 && numOfPizzas <= 10))
			throw new IllegalArgumentException();
	}

	@Lookup
	protected Order createOrder() {
		return null;
	}

	@Override
	public Order placeNewOrder(Customer customer, Address orderAddress, Map<Pizza, Integer> pizzas) {
		checkNumberOfPizzas(pizzas);
		Order newOrder = createOrder();
		newOrder.setCustomer(customer);
		newOrder.setListOfPizzas(pizzas);
		newOrder.setAddress(orderAddress);
		return orderRepository.save(newOrder);
	}

	@Override
	public Order applyDiscountsToOrder(Order newOrder) {
		Double discountOfOrder = discountService.calculateDiscount(newOrder);
		newOrder.setDiscount(discountOfOrder);
		return orderRepository.save(newOrder);
	}

	@Override
	public boolean addDiscounts(Discount... discounts) {
		return discountService.addDiscounts(discounts);
	}

	@Override
	public boolean deleteDiscount(Discount discount) {
		return discountService.deleteDiscount(discount);
	}

	@Override
	public boolean changeOrder(Order order, Map<Pizza, Integer> pizzas) {
		if (order.changeCurrentOrder(pizzas)) {
			order = orderRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public boolean setOrderInProgressState(Order order) {
		State state = new InProgressOrderState();
		if (state.changeState(order)) {
			order = orderRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public boolean setOrderInCanceledState(Order order) {
		State state = new CancelledOrderState();
		if (state.changeState(order)) {
			order = orderRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public boolean setOrderInDoneState(Order order) {
		DiscountCard accumulativeCard = order.getCustomer().getAccumulativeCard();
		State state = new DoneOrderState();
		boolean accepted = state.changeState(order);
		if (accepted) {
			accumulativeCard.addMoney(order.getPriceWithDiscounts());
			discountService.save(accumulativeCard);
			order = orderRepository.save(order);
			return true;
		}
		return false;
	}
	
	@Override
	public Order findById(int id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> getAll() {
		return orderRepository.getAll();
	}

}
