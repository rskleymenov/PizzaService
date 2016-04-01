package roman.kleimenov.domain.impl;

import roman.kleimenov.domain.AccumulativeCard;

public class AccumulativeCardTaskImplementation implements AccumulativeCard {
	private static final Double CARD_DISCOUNT = 0.1d;
	private static final Double MAX_CARD_DISCOUNT = 0.3d;
	private Double sum = 0d;

	public AccumulativeCardTaskImplementation() {
		super();
	}

	public AccumulativeCardTaskImplementation(Double sum) {
		super();
		this.sum = sum;
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
