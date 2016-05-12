package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.service.PizzaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryInMemDBContext.xml" })
@Transactional
public class SimplePizzaServiceTest {

	@Autowired
	private PizzaService pizzaService;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void findByIdShouldReturnActualPizza() {
		final String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		Integer id = keyHolder.getKey().intValue();

		Pizza expectedPizza = new Pizza(id, "MEAT", 22.22d, PizzaType.Meat);
		Pizza actualPizza = pizzaService.findById(id);

		assertEquals(expectedPizza, actualPizza);
	}

	@Test
	public void pizzaServiceShouldAddPizzaToDB() {
		final String sql = "SELECT * FROM PIZZAS WHERE id = ?";
		Pizza expectedPizza = new Pizza("MeatBalls", 55.25d, PizzaType.Meat);
		expectedPizza = pizzaService.save(expectedPizza);
		pizzaService.findById(expectedPizza.getId());
		Pizza actualPizza = jdbcTemplate.queryForObject(sql, new Object[] { expectedPizza.getId() },
				new BeanPropertyRowMapper<Pizza>(Pizza.class));

		assertEquals(expectedPizza, actualPizza);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteShouldDeletePizzaAndTestMustRiseExcepionDueToNoPizzaInDB() {
		final String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		Integer id = keyHolder.getKey().intValue();

		pizzaService.delete(id);
		
		pizzaService.getAll();

		final String selectSQL = "SELECT * FROM PIZZAS WHERE id = ?";

		jdbcTemplate.queryForObject(selectSQL, new Object[] { id }, new BeanPropertyRowMapper<Pizza>(Pizza.class));
	}

	@Test
	public void pizzaServiceShouldReturnAllPizzasInDB() {
		final String sql = "INSERT INTO PIZZAS(ID, NAME, PRICE, TYPE) VALUES(PIZZA_SEQ.NEXTVAL, 'MEAT', 22.22, 'Meat')";
		jdbcTemplate.update(sql);
		List<Pizza> actualList = pizzaService.getAll();
		String sql2 = "SELECT * FROM PIZZAS";
		List<Pizza> expectedList = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<Pizza>(Pizza.class));

		assertEquals(expectedList, actualList);
	}
}
