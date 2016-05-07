package com.fusillade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.repository.PizzaRepository;
import com.fusillade.service.PizzaService;

@Service
public class SimplePizzaService implements PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public Pizza findById(int id) {
		return pizzaRepository.findById(id);
	}

	@Override
	public Pizza save(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	@Override
	public void delete(int id) {
		pizzaRepository.delete(id);
	}

	@Override
	public List<Pizza> getAll() {
		return pizzaRepository.getAll();
	}

}
