package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fusillade.domain.discounts.impl.MaxPricePizzaDiscount;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.domain.states.impl.CancelledOrderState;
import com.fusillade.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryInMemDBContext.xml" })
public class SimpleOrderServiceTest {

	@Autowired
	private OrderService orderService;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void placeNewOrderMustInsertNewOrder() {
		String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";
		String addrIdSQL = "SELECT ADDRESS_ID FROM ORDERS WHERE id = ?";
		String custIdSQL = "SELECT CUSTOMER_ID FROM ORDERS WHERE id = ?";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		int id = keyHolder.getKey().intValue();

		Customer customer = new Customer("n", "s");
		Address orderAddress = new Address("s", "c");
		Map<Pizza, Integer> pizzas = new HashMap<>();

		Pizza pizza = new Pizza();
		pizza.setId(id);
		pizza.setName("MEAT");
		pizza.setPrice(22.22d);
		pizza.setType(PizzaType.Meat);

		pizzas.put(pizza, 3);

		Order order = orderService.placeNewOrder(customer, orderAddress, pizzas);

		int addrId = jdbcTemplate.queryForObject(addrIdSQL, new Object[] { order.getId() }, Integer.class);
		int custId = jdbcTemplate.queryForObject(custIdSQL, new Object[] { order.getId() }, Integer.class);
		assertEquals(order.getAddress().getId(), addrId);
		assertEquals(order.getCustomer().getId(), custId);
	}
	
	@Test
	public void applyDiscountsToOrderTest() {
		String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";
		String sqlForOrder = "select discount from orders where orders.id = ?";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		int id = keyHolder.getKey().intValue();
		Customer customer = new Customer("n", "s");
		Address orderAddress = new Address("s", "c");
		Map<Pizza, Integer> pizzas = new HashMap<>();

		Pizza pizza = new Pizza();
		pizza.setId(id);
		pizzas.put(pizza, 4);

		Order order = orderService.placeNewOrder(customer, orderAddress, pizzas);
		orderService.addDiscounts(new MaxPricePizzaDiscount());
		order = orderService.applyDiscountsToOrder(order);
		
		Double discount = jdbcTemplate.queryForObject(sqlForOrder, new Object[] {order.getId()}, Double.class);
		assertEquals(Double.valueOf(6.66d), discount, 0.01);
	}
	
	@Test
	public void testChangeOrder() {
		String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";
		String selectUpdatedPizzas = "select pizzas from ORDER_PIZZA where ORDER_ID = ?";
		Integer numberOfPizzasForUpdate = 22;
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		int id = keyHolder.getKey().intValue();
		Customer customer = new Customer("n", "s");
		Address orderAddress = new Address("s", "c");
		Map<Pizza, Integer> pizzas = new HashMap<>();

		Pizza pizza = new Pizza();
		pizza.setId(id);
		pizzas.put(pizza, 4);

		Order order = orderService.placeNewOrder(customer, orderAddress, pizzas);
	
		pizzas = new HashMap<>();
		pizzas.put(pizza, numberOfPizzasForUpdate);
		orderService.changeOrder(order, pizzas);
		Integer numOfPizzas = jdbcTemplate.queryForObject(selectUpdatedPizzas, new Object[] {order.getId()}, Integer.class);
		assertEquals(numberOfPizzasForUpdate, numOfPizzas);
	}
	
	@Test
	public void testSetOrderInCancelledState() {
		String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		int id = keyHolder.getKey().intValue();
		Customer customer = new Customer("n", "s");
		Address orderAddress = new Address("s", "c");
		Map<Pizza, Integer> pizzas = new HashMap<>();

		Pizza pizza = new Pizza();
		pizza.setId(id);
		pizzas.put(pizza, 3);

		Order order = orderService.placeNewOrder(customer, orderAddress, pizzas);
		boolean canceled= orderService.setOrderInCanceledState(order);
		
		assertTrue(canceled);
		assertEquals(new CancelledOrderState(), orderService.findById(order.getId()).getState());
	}

}
