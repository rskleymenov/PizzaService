package roman.kleimenov.domain;

import java.util.List;

import roman.kleimenov.domain.enums.OrderStatus;
import roman.kleimenov.domain.impl.Customer;
import roman.kleimenov.domain.impl.Pizza;

public class Order {
	private int id;
	private static int nextId = 0;
	private Customer customer;
	private List<Pizza> listOfPizzas;
	private OrderStatus orderStatus;
	private Discount discount;
	private Double orderPrice = 0d;

	private Order() {
		super();
		this.id = ++nextId;
		orderStatus = OrderStatus.NEW;
	}

	public Order(Customer customer, List<Pizza> listOfPizzas, Discount discount) {
		this();
		this.customer = customer;
		this.listOfPizzas = listOfPizzas;
		this.discount = discount;
		this.orderPrice = getOrderCost();
	}

	private Double getOrderCost() {
		Double totalCostOfOrder = calculateTotalPriceWithoutDiscount();

		if (listOfPizzas.isEmpty()) {
			return 0d;
		}

		if (discount != null) {
			totalCostOfOrder -= discount.calculateDiscount();
		}

		AccumulativeCard accumulativeCard = customer.getAccumulativeCard();

		if (accumulativeCard != null) {
			totalCostOfOrder -= accumulativeCard.calculateCardDiscount(totalCostOfOrder);
		}
		orderPrice = totalCostOfOrder;
		return totalCostOfOrder;
	}

	public Double getOrderPrice() {
		return this.orderPrice;
	}

	public boolean changeCurrentOrder(List<Pizza> listOfPizzas) {
		if (listOfPizzas != null && this.orderStatus == OrderStatus.NEW) {
			this.listOfPizzas = listOfPizzas;
			this.orderPrice = getOrderCost();
			return true;
		}
		return false;
	}

	private Double calculateTotalPriceWithoutDiscount() {
		Double totalPriceOfOrder = 0d;
		for (Pizza pizza : listOfPizzas) {
			totalPriceOfOrder += pizza.getPrice();
		}
		return totalPriceOfOrder;
	}

	public boolean setInProgressOrderStatus() {
		if (this.orderStatus == OrderStatus.NEW) {
			this.orderStatus = OrderStatus.IN_PROGRESS;
			return true;
		}
		return false;
	}

	public boolean setCanceledOrderStatus() {
		if (this.orderStatus == OrderStatus.NEW || this.orderStatus == OrderStatus.IN_PROGRESS) {
			this.orderStatus = OrderStatus.CANCELED;
			return true;
		}
		return false;
	}

	public boolean setDoneOrderStatus() {
		if (this.orderStatus == OrderStatus.IN_PROGRESS) {
			this.orderStatus = OrderStatus.DONE;
			this.customer.getAccumulativeCard().addMoney(orderPrice);
			return true;
		}
		return false;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + id;
		result = prime * result + ((listOfPizzas == null) ? 0 : listOfPizzas.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
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
		if (orderStatus != other.orderStatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", listOfPizzas=" + listOfPizzas + ", orderStatus="
				+ orderStatus + ", discount=" + discount + "]";
	}

}
