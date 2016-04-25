package com.fusillade.domain.discounts;

import com.fusillade.domain.entity.Order;

public interface Discount {
	Double calculateDiscount(Order order);
}
