package com.fusillade.domain.entity;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fusillade.domain.states.State;
import com.fusillade.domain.states.impl.NewOrderState;
import com.fusillade.domain.states.impl.OrderStateConverter;

@Component
@Scope("prototype")
@Entity
@Table(name = "ORDERS")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
	@SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
	private int id;
	@Convert(converter = OrderStateConverter.class)
	private State state;
	private Double price = 0d;
	private Double discount = 0d;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ORDER_PIZZA")
	@MapKeyJoinColumn(name = "PIZZA_ID")
	@Column(name = "pizzas")
	private Map<Pizza, Integer> pizzasInOrder;

	public Order() {
		super();
		this.state = new NewOrderState();
	}

	public Order(Double price, Double discount, Customer customer) {
		this();
		this.price = price;
		this.discount = discount;
		this.customer = customer;
	}

	public boolean changeCurrentOrder(Map<Pizza, Integer> pizzasInOrder) {
		if (this.state.getClass() == NewOrderState.class) {
			this.pizzasInOrder = pizzasInOrder;
			return true;
		}
		return false;
	}

	public Double getTotalPrice() {
		Double totalPriceOfOrder = 0d;
		Iterator<Entry<Pizza, Integer>> it = pizzasInOrder.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Pizza, Integer> pair = (Map.Entry<Pizza, Integer>) it.next();
			totalPriceOfOrder += pair.getKey().getPrice() * pair.getValue();
		}
		this.price = totalPriceOfOrder;
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

	public Map<Pizza, Integer> getListOfPizzas() {
		return pizzasInOrder;
	}

	public void setListOfPizzas(Map<Pizza, Integer> pizzasInOrder) {
		this.pizzasInOrder = pizzasInOrder;
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

	public Map<Pizza, Integer> getPizzasInOrder() {
		return pizzasInOrder;
	}

	public void setPizzasInOrder(Map<Pizza, Integer> pizzasInOrder) {
		this.pizzasInOrder = pizzasInOrder;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + ", price=" + price + ", discount=" + discount + ", address="
				+ address.getId() + "]";
	}

}
