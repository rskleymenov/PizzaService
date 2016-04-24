package com.fusillade.domain.entity.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.fusillade.domain.entity.Domain;
import com.fusillade.domain.entity.State;
import com.fusillade.domain.entity.states.NewOrderState;

@Domain
@Scope("prototype")
public class Order {
	private int id;
	private static int nextId = 0;
	private Customer customer;
	private List<Pizza> listOfPizzas;
	private State state;
	private Double discount = 0d;

	private Order() {
		super();
		this.id = ++nextId;
		this.state = new NewOrderState();
	}

	public Order(Customer customer, List<Pizza> listOfPizzas) {
		this();
		this.customer = customer;
		this.listOfPizzas = listOfPizzas;
	}

	public boolean changeCurrentOrder(List<Pizza> listOfPizzas) {
		if (this.state.getClass() == NewOrderState.class) {
			this.listOfPizzas = listOfPizzas;
			return true;
		}
		return false;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Double getTotalPrice() {
		Double totalPriceOfOrder = 0d;
		for (Pizza pizza : listOfPizzas) {
			totalPriceOfOrder += pizza.getPrice();
		}
		return totalPriceOfOrder;
	}

	public Double getPriceWithDiscounts() {
		return this.getTotalPrice() - discount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public void setListOfPizzas(List<Pizza> listOfPizzas) {
		this.listOfPizzas = listOfPizzas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + id;
		result = prime * result + ((listOfPizzas == null) ? 0 : listOfPizzas.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (id != other.id)
			return false;
		if (listOfPizzas == null) {
			if (other.listOfPizzas != null)
				return false;
		} else if (!listOfPizzas.equals(other.listOfPizzas))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	



}
