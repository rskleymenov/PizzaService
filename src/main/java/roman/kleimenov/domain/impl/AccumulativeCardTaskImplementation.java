package roman.kleimenov.domain.impl;

import roman.kleimenov.domain.AccumulativeCard;

public class AccumulativeCardTaskImplementation implements AccumulativeCard {
	private static final Double CARD_DISCOUNT = 0.1d;
	private static final Double MAX_CARD_DISCOUNT = 0.3d;
	private int id;
	private Double sum = 0d;

	public AccumulativeCardTaskImplementation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccumulativeCardTaskImplementation(int id, Double sum) {
		super();
		this.id = id;
		this.sum = sum;
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

}
