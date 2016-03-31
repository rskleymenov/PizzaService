package roman.kleimenov.domain;

public interface AccumulativeCard {
	void addMoney(Double money);
	Double calculateCardDiscount(Double totalCostOfOrder);
}
