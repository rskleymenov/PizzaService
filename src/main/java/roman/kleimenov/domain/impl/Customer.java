package roman.kleimenov.domain.impl;

import roman.kleimenov.domain.AccumulativeCard;

public class Customer {
	private int id;
	private String name;
	private Address deliveryAddress;
	private AccumulativeCard accumulativeCard;

	public Customer() {

	}

	public Customer(String name, Address deliveryAddress, AccumulativeCard accumulativeCard) {
		super();
		this.name = name;
		this.deliveryAddress = deliveryAddress;
		this.accumulativeCard = accumulativeCard;
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

	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accumulativeCard == null) ? 0 : accumulativeCard.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (accumulativeCard == null) {
			if (other.accumulativeCard != null)
				return false;
		} else if (!accumulativeCard.equals(other.accumulativeCard))
			return false;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", deliveryAddress=" + deliveryAddress + ", accumulativeCard="
				+ accumulativeCard + "]";
	}

}
