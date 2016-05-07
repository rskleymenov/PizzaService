package com.fusillade.service.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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

import com.fusillade.domain.entity.Address;
import com.fusillade.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryInMemDBContext.xml" })
public class SimpleAddressServiceTest {
	
	@Autowired
	private AddressService addressService;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void saveAddressTest() {
		final String sql = "SELECT * FROM ADDRESSES WHERE id = ?";
		Address address = new Address("adr", "adr");
		address = addressService.save(address);
		
		Address actualAddress = jdbcTemplate.queryForObject(sql, new Object[] { address.getId() },
				new BeanPropertyRowMapper<Address>(Address.class));

		assertEquals(address.getId(), actualAddress.getId());
		assertEquals(address.getStreet(), actualAddress.getStreet());
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteAddress() {
		final String sql = "INSERT INTO ADDRESSES(ID, CITY, STREET) VALUES(ADDRESS_SEQ.NEXTVAL, 'rr', 'gg')";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);

		int id = keyHolder.getKey().intValue();

		addressService.delete(id);

		final String selectSQL = "SELECT * FROM ADDRESSES WHERE id = ?";

		jdbcTemplate.queryForObject(selectSQL, new Object[] { id }, new BeanPropertyRowMapper<Address>(Address.class));
	}

}
