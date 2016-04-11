package com.fusillade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.State;
import com.fusillade.domain.entity.states.CancelledOrderState;
import com.fusillade.domain.entity.states.DoneOrderState;
import com.fusillade.domain.entity.states.InProgressOrderState;
import com.fusillade.repository.OrderRepository;
import com.fusillade.repository.PizzaRepository;
import com.fusillade.service.AccumulativeCardService;
import com.fusillade.service.DiscountService;
import com.fusillade.service.OrderService;

@Service
public class SimpleOrderService implements OrderService {
	OrderRepository orderRepository;
	PizzaRepository pizzaRepository;
	AccumulativeCardService cardService;
	DiscountService discountService;

	public SimpleOrderService(){
		
	}
	
	@Autowired
	public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository,
			AccumulativeCardService simpleAccumulativeCardService, DiscountService discountService) {
		super();
		this.orderRepository = orderRepository;
		this.pizzaRepository = pizzaRepository;
		this.cardService = simpleAccumulativeCardService;
		this.discountService = discountService;
	}
	
	private void checkNumberOfPizzas(Integer... pizzasID) {
		if (!(pizzasID.length >= 1 && pizzasID.length <= 10))
			throw new IllegalArgumentException();
	}

	@Lookup
	protected Order createOrder() {
		return null;
	}

	@Override
	public List<Pizza> pizzasByArrOfId(Integer... pizzasID) {
		List<Pizza> pizzas = new ArrayList<>();

		for (Integer id : pizzasID) {
			pizzas.add(pizzaRepository.getPizzaByID(id));
		}
		return pizzas;
	}

	@Override
	public Order placeNewOrder(Customer customer, Integer... pizzasID) {
		checkNumberOfPizzas(pizzasID);
		List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
		Order newOrder = createOrder();
		newOrder.setCustomer(customer);
		newOrder.setListOfPizzas(pizzas);
		orderRepository.saveOrder(newOrder);
		return newOrder;
	}

	@Override
	public void applyDiscountsToOrder(Order newOrder) {
		Double discountOfOrder = discountService.calculateDiscount(newOrder);
		newOrder.setDiscount(discountOfOrder);
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
	public boolean changeOrder(Order order, Integer... pizzasID) {
		return order.changeCurrentOrder(pizzasByArrOfId(pizzasID));
	}

	@Override
	public boolean setOrderInProgressState(Order order) {
		State state = new InProgressOrderState();
		return state.changeState(order);
	}

	@Override
	public boolean setOrderInCanceledState(Order order) {
		State state = new CancelledOrderState();
		return state.changeState(order);
	}

	@Override
	public boolean setOrderInDoneState(Order order) {
		AccumulativeCard accumulativeCard = cardService.getCardByCustomer(order.getCustomer());
		State state = new DoneOrderState();
		boolean accepted = state.changeState(order);
		if (accepted) {
			accumulativeCard.addMoney(order.getPriceWithDiscounts());
			return true;
		}
		return false;
	}

	@Override
	public void addAccumulativeCardToCustomer(Customer customer, AccumulativeCard accumulativeCard) {
		cardService.addAccumulativeCardToCustomer(customer, accumulativeCard);
	}

	@Override
	public AccumulativeCard getCardByCustomer(Customer customer) {
		return cardService.getCardByCustomer(customer);
	}

}
