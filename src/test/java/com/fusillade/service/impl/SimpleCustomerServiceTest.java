package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

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

import com.fusillade.domain.discounts.impl.DiscountCard;
import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryInMemDBContext.xml" })
public class SimpleCustomerServiceTest {
	@Autowired
	private CustomerService customerService;

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void findById() {
		final String sql = "INSERT INTO customers(id, name, surname) values (CUSTOMER_SEQ.NEXTVAL, 'Roman', 'Serg')";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		Integer cId = keyHolder.getKey().intValue();

		Customer actualCustomer = customerService.findById(cId);
		assertNotNull(actualCustomer.getId());
		assertEquals("Roman", actualCustomer.getName());
		assertEquals("Serg", actualCustomer.getSurname());
	}
	
	@Test
	public void saveCustomerTest() {
		final String sqlCust = "SELECT * FROM CUSTOMERS WHERE id = ?";
		final String sqlADDR_CUST = "SELECT ADDRESS_ID FROM customer_address where CUSTOMER_ID = ?";
		Customer expectedCustomer = new Customer("n", "s");
		Address address = new Address("a", "b");
		expectedCustomer.setAddresses(Arrays.asList(address));
		expectedCustomer = customerService.save(expectedCustomer);
		
		Customer actualCustomer = jdbcTemplate.queryForObject(sqlCust, new Object[] { expectedCustomer.getId() },
				new BeanPropertyRowMapper<Customer>(Customer.class));
		
		int addr_id = jdbcTemplate.queryForObject(sqlADDR_CUST, new Object[] { expectedCustomer.getId() },
				Integer.class);
		
		assertEquals(expectedCustomer.getName(), actualCustomer.getName());
		assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname());
		assertEquals(expectedCustomer.getAddresses().get(0).getId(), addr_id);
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteCustomerTest() {
		final String insertCustomerSQL = "INSERT INTO customers(id, name, surname) values (CUSTOMER_SEQ.NEXTVAL, 'Roman', 'Serg')";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);
		
		int custId = keyHolder.getKey().intValue();
		
		customerService.delete(custId);
		
		final String selectSQL = "SELECT * FROM accumulative_cards WHERE id = ?";

		jdbcTemplate.queryForObject(selectSQL, new Object[] { custId },
				new BeanPropertyRowMapper<DiscountCard>(DiscountCard.class));
	}

}
