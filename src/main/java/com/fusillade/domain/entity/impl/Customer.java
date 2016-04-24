package com.fusillade.domain.entity.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;

	@Transient
	private Address deliveryAddress;

	public Customer() {

	}

	public Customer(String name, Address deliveryAddress) {
		super();
		this.name = name;
		this.deliveryAddress = deliveryAddress;
	}

	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public Customer(String name, String surname, Address deliveryAddress) {
		super();
		this.name = name;
		this.surname = surname;
		this.deliveryAddress = deliveryAddress;
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

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
