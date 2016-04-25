package com.fusillade.domain.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;

@Entity
@Table(name = "PIZZASERVICE.CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "CUSTOMER_ADDRESS", joinColumns = { @JoinColumn(name = "CUSTOMER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ADDRESS_ID") })
	private List<Address> addresses;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, targetEntity = AccumulativeCardDiscount.class)
	private List<AccumulativeCard> accumulativeCards;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public Customer(String name, String surname, List<Address> addresses) {
		super();
		this.name = name;
		this.surname = surname;
		this.addresses = addresses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<AccumulativeCard> getAccumulativeCards() {
		return accumulativeCards;
	}

	public void setAccumulativeCards(List<AccumulativeCard> accumulativeCards) {
		this.accumulativeCards = accumulativeCards;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
