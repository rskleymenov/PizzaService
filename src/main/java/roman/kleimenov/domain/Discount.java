package roman.kleimenov.domain;

import java.util.List;

import roman.kleimenov.domain.impl.Pizza;

public interface Discount {
	Double calculateDiscount();
	void setListOfPizzas(List<Pizza> listOfPizzas);
}
