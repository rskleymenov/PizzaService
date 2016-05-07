package com.fusillade.domain.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fusillade.domain.discounts.impl.DiscountCard;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
	@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
	private int id;
	private String name;
	private String surname;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CUSTOMER_ADDRESS", joinColumns = { @JoinColumn(name = "CUSTOMER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ADDRESS_ID") })
	private List<Address> addresses;
	@OneToOne(mappedBy = "customer", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE })
	private DiscountCard accumulativeCard;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Order> orders;

	public Customer() {
		super();
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

	public Customer(String name, String surname, List<Address> addresses, DiscountCard accumulativeCard,
			List<Order> orders) {
		super();
		this.name = name;
		this.surname = surname;
		this.addresses = addresses;
		this.accumulativeCard = accumulativeCard;
		this.orders = orders;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public DiscountCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(DiscountCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + ", addresses=" + addresses
				+ ", AccumulativeCardDiscount=" + accumulativeCard + ", orders=" + orders + "]";
	}

}
