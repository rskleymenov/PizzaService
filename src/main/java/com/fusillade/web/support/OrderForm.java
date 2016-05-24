package com.fusillade.web.support;

import java.util.Map;

public class OrderForm {
	
	private Map<Integer, String> pizzaMap;
	
	private String deliveryAddress;

	public Map<Integer, String> getPizzaMap() {
		return pizzaMap;
	}

	public void setPizzaMap(Map<Integer, String> pizzaMap) {
		this.pizzaMap = pizzaMap;
	}
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public String toString() {
		return "OrderForm [pizzaMap=" + pizzaMap + ", deliveryAddress=" + deliveryAddress + "]";
	}
	
	
}
