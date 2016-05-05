package com.fusillade.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;

public class JDBCPizzaRepository implements PizzaRepository {

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Pizza pizza) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO pizzadb.pizzastore "
				+ "(NAME, PRICE, TYPE) VALUES (?, ?, CAST(? as pizzadb.pizzatype))";

		jdbcTemplateObject.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, pizza.getName());
				ps.setDouble(2, pizza.getPrice());
				ps.setString(3, pizza.getType().toString());
				return ps;
			}

		}, keyHolder);

		pizza.setId(keyHolder.getKey().intValue());
	}

	@Override
	public Pizza findById(int id) {
		String sql = "SELECT * FROM pizzadb.pizzastore WHERE id = ?";
		Pizza pizza = jdbcTemplateObject.queryForObject(sql, new Object[] { id }, new PizzaMapper());
		return pizza;
	}

	@Override
	public void update(Pizza pizza) {
		String SQL = "update pizzadb.pizzastore set name = ?, price = ?, type = CAST(? as pizzadb.pizzatype) where id = ?";
		jdbcTemplateObject.update(SQL, pizza.getName(), pizza.getPrice(), pizza.getType().toString(), pizza.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from pizzadb.pizzastore where id = ?";
		jdbcTemplateObject.update(sql, id);
	}

	@Override
	public List<Pizza> getAll() {
		String SQL = "select * from pizzadb.pizzastore";
		List<Pizza> students = jdbcTemplateObject.query(SQL, new PizzaMapper());
		return students;
	}

}
