package com.fusillade.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;

public class PizzaMapper implements RowMapper<Pizza>{

	@Override
	public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pizza pizza = new Pizza();
		pizza.setId(rs.getInt("id"));
		pizza.setName(rs.getString("name"));
		pizza.setPrice(rs.getDouble("price"));
		pizza.setType(PizzaType.valueOf(rs.getString("type")));
		return pizza;
	}

}
