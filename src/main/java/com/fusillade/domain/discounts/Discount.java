package com.fusillade.domain.discounts;

import com.fusillade.domain.entity.impl.Order;

public interface Discount {
	Double calculateDiscount(Order order);
}
