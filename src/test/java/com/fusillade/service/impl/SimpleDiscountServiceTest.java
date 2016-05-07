package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
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

import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.domain.entity.Customer;
import com.fusillade.service.DiscountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryInMemDBContext.xml" })
public class SimpleDiscountServiceTest {

	@Autowired
	private DiscountService discountService;

	private JdbcTemplate jdbcTemplate;
	private Integer customerId;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Before
	public void init() {
		final String insertCustomerSQL = "insert into customers(id, name, surname) values (CUSTOMER_SEQ.NEXTVAL, 'Roman', 'Kl')";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		customerId = keyHolder.getKey().intValue();
	}

	@Test
	public void findByIdTest() {
		final String sql = "INSERT INTO accumulative_cards(id, sum, customer_id) values (ACCUMULATIVE_SEQ.NEXTVAL, 10.55, "
				+ customerId + ")";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		Integer cardId = keyHolder.getKey().intValue();

		DiscountCard actualCard = discountService.findById(cardId);

		assertEquals(Double.valueOf(10.55d), actualCard.getSum());
		assertEquals(customerId, Integer.valueOf(actualCard.getCustomer().getId()));
		assertEquals("Roman", actualCard.getCustomer().getName());
	}

	@Test
	public void saveShouldSaveEntity() {
		final String sql = "SELECT * FROM accumulative_cards WHERE id = ?";
		DiscountCard expectedCard = new DiscountCard(10.55d, new Customer("R", "K"));
		expectedCard = discountService.save(expectedCard);
		DiscountCard actualCard = jdbcTemplate.queryForObject(sql, new Object[] { expectedCard.getId() },
				new BeanPropertyRowMapper<DiscountCard>(DiscountCard.class));

		assertEquals(expectedCard.getId(), actualCard.getId());
		assertEquals(expectedCard.getSum(), actualCard.getSum());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteShouldDeleteAndRiseExceptionDuetoNoRowInTable() {
		final String sql = "INSERT INTO accumulative_cards(id, sum, customer_id) values (ACCUMULATIVE_SEQ.NEXTVAL, 10.55, "
				+ customerId + ")";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		Integer cardId = keyHolder.getKey().intValue();

		discountService.delete(cardId);

		final String selectSQL = "SELECT * FROM accumulative_cards WHERE id = ?";

		jdbcTemplate.queryForObject(selectSQL, new Object[] { cardId },
				new BeanPropertyRowMapper<DiscountCard>(DiscountCard.class));
	}

	@Test
	public void getAllTest() {
		final String sql = "INSERT INTO accumulative_cards(id, sum, customer_id) values (ACCUMULATIVE_SEQ.NEXTVAL, 10.55, "
				+ customerId + ")";
		final String clearSQL = "DELETE FROM ACCUMULATIVE_CARDS WHERE ID <> 0";

		jdbcTemplate.update(clearSQL);
		jdbcTemplate.update(sql);

		List<DiscountCard> actual = discountService.getAll();
		assertEquals(1, actual.size());
		assertEquals(Double.valueOf(10.55d), actual.get(0).getSum());
	}

}
