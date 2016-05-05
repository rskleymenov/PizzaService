package com.fusillade.domain.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fusillade.domain.states.State;
import com.fusillade.domain.states.impl.NewOrderState;
import com.fusillade.domain.states.impl.OrderStateConverter;

@Component
@Scope("prototype")
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
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	@ElementCollection
	@CollectionTable(name = "pizzaservice.order_pizza")
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

	public Map<Pizza, Integer> getPizzasInOrder() {
		return pizzasInOrder;
	}

	public void setPizzasInOrder(Map<Pizza, Integer> pizzasInOrder) {
		this.pizzasInOrder = pizzasInOrder;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + ", price=" + price + ", discount=" + discount + ", address="
				+ address.getCity() + "]";
	}
	
	

}
