package com.fusillade.domain.discounts.impl;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.entity.Customer;

@Entity
@Table(name = "ACCUMULATIVE_CARDS")
public class AccumulativeCardDiscount implements AccumulativeCard {
	private static final Double CARD_DISCOUNT = 0.1d;
	private static final Double MAX_CARD_DISCOUNT = 0.3d;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCUMULATIVE_SEQ")
	@SequenceGenerator(name = "ACCUMULATIVE_SEQ", sequenceName = "ACCUMULATIVE_SEQ", allocationSize = 1)
	private int id;
	private Double sum = 0d;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	public AccumulativeCardDiscount() {
		super();
	}

	public AccumulativeCardDiscount(Double sum) {
		super();
		this.sum = sum;
	}

	public AccumulativeCardDiscount(Double sum, Customer customer) {
		super();
		this.sum = sum;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addMoney(Double money) {
		this.sum += money;
	}

	@Override
	public Double calculateCardDiscount(Double totalCostOfOrder) {
		Double discount = 0d;
		Double thisCardDiscount = this.sum * CARD_DISCOUNT;
		Double maxCardDiscount = totalCostOfOrder * MAX_CARD_DISCOUNT;
		if (thisCardDiscount <= maxCardDiscount) {
			discount = thisCardDiscount;
		} else {
			discount = maxCardDiscount;
		}
		return discount;
	}

	@Override
	public String toString() {
		return "AccumulativeCardDiscount [id=" + id + ", sum=" + sum + ", customer=" + customer.getSurname() + "]";
	}

}
