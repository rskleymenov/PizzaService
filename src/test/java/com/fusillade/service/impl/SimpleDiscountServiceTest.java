package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fusillade.domain.discounts.AccumulativeCard;
import com.fusillade.domain.discounts.Discount;
import com.fusillade.domain.discounts.impl.AccumulativeCardDiscount;
import com.fusillade.domain.discounts.impl.MaxPricePizzaDiscount;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.service.AccumulativeCardService;
import com.fusillade.service.DiscountService;
import com.fusillade.service.OrderService;
import com.fusillade.service.impl.SimpleAccumulativeCardService;
import com.fusillade.service.impl.SimpleDiscountService;

public class SimpleDiscountServiceTest {
	ConfigurableApplicationContext applicationContext;
	OrderService orderService;
	
	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("AppContext.xml");
		orderService = (OrderService) applicationContext.getBean("simpleOrderService");
	}
	
	@After
	public void close() {
		applicationContext.close();
	}
	
	@Test
	public void calculateDiscountShouldReturn43dot5() {
		AccumulativeCard accumulativeCard = new AccumulativeCardDiscount(1000d);
		Customer customer = mock(Customer.class);
		Order order = orderService.placeNewOrder(customer, 1, 2, 3, 4, 5);
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		DiscountService discountService = new SimpleDiscountService(accumulativeCardService);
		accumulativeCardService.addAccumulativeCardToCustomer(customer, accumulativeCard);
		discountService.addDiscounts(new MaxPricePizzaDiscount());
		
		assertEquals(Double.valueOf(43.5), discountService.calculateDiscount(order));
	}

	@Test
	public void addDiscountShouldReturnTrue() {
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		DiscountService discountService = new SimpleDiscountService(accumulativeCardService);
		
		assertTrue(discountService.addDiscounts(new MaxPricePizzaDiscount()));
	}

	@Test
	public void addDiscountShouldReturnFalseDueToEmplyInputValue() {
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		DiscountService discountService = new SimpleDiscountService(accumulativeCardService);
		assertFalse(discountService.addDiscounts());
	}

	@Test
	public void addDiscountShouldReturnFalseDueToNullInputValue() {
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		DiscountService discountService = new SimpleDiscountService(accumulativeCardService);
		
		assertFalse(discountService.addDiscounts(null));
	}

	@Test
	public void deleteDiscountShouldReturnTrue() {
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		DiscountService discountService = new SimpleDiscountService(accumulativeCardService);
		Discount discount = new MaxPricePizzaDiscount();
		discountService.addDiscounts(discount);
		
		assertTrue(discountService.deleteDiscount(discount));
	}

	@Test
	public void deleteDiscountShouldReturnFalse() {
		AccumulativeCardService accumulativeCardService = new SimpleAccumulativeCardService();
		DiscountService discountService = new SimpleDiscountService(accumulativeCardService);
		discountService.addDiscounts(new MaxPricePizzaDiscount());
		
		assertFalse(discountService.deleteDiscount(new Discount() {
			public Double calculateDiscount(Order order) {
				return 5d;
			}
		}));
	}
}
