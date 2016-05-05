package com.fusillade.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fusillade.domain.entity.enums.PizzaType;

@Entity
@Table(name = "PIZZAS")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIZZA_SEQ")
	@SequenceGenerator(name = "PIZZA_SEQ", sequenceName = "PIZZA_SEQ", allocationSize = 1)
	private int id;
	private String name;
	private Double price;
	@Enumerated(EnumType.STRING)
	private PizzaType type;

	public Pizza() {

	}

	public Pizza(String name, Double price, PizzaType type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Pizza(int id, String name, Double price, PizzaType type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}
}
