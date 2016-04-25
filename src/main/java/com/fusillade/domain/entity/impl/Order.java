package com.fusillade.domain.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fusillade.domain.entity.OrderStateConverter;
import com.fusillade.domain.entity.State;
import com.fusillade.domain.entity.states.NewOrderState;

/*@Domain
@Scope("prototype")*/
@Entity
@Table(name = "PIZZASERVICE.ORDER")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Transient
	private List<Pizza> listOfPizzas;
	@Convert(converter = OrderStateConverter.class)
	private State state;
	private Double price = 0d;
	private Double discount = 0d;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;
	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	public Order() {
		super();
		this.state = new NewOrderState();
	}

	public Order(Double price, Double discount, Customer customer, Address address) {
		this();
		this.price = price;
		this.discount = discount;
		this.customer = customer;
		this.address = address;
	}

	public boolean changeCurrentOrder(List<Pizza> listOfPizzas) {
		if (this.state.getClass() == NewOrderState.class) {
			this.listOfPizzas = listOfPizzas;
			return true;
		}
		return false;
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

	public List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public void setListOfPizzas(List<Pizza> listOfPizzas) {
		this.listOfPizzas = listOfPizzas;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + ", price=" + price + ", discount=" + discount + ", address="
				+ address + "]";
	}
	
	

}
