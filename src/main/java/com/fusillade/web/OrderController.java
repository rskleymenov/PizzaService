package com.fusillade.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fusillade.domain.entity.Address;
import com.fusillade.domain.entity.Customer;
import com.fusillade.domain.entity.Order;
import com.fusillade.domain.entity.Pizza;
import com.fusillade.service.AddressService;
import com.fusillade.service.CustomerService;
import com.fusillade.service.OrderService;
import com.fusillade.service.PizzaService;
import com.fusillade.web.support.OrderForm;

@Controller
@SessionAttributes("customer")
public class OrderController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/submitorder", method = RequestMethod.POST)
	public String getDateForOrder(@ModelAttribute("orderForm") OrderForm orderForm,
			@ModelAttribute("customer") Customer customer, BindingResult result, Model model) {

		System.out.println(result.hasErrors());
		Map<Pizza, Integer> pizzas = createMapOfPizzas(orderForm.getPizzaMap());
		Address address = addressService.findById(Integer.valueOf(orderForm.getDeliveryAddress()));
		orderService.placeNewOrder(customer, address, pizzas);
		customer = customerService.findById(customer.getId());
		model.addAttribute("customer", customer);
		return "customerpage";
	}

	private Map<Pizza, Integer> createMapOfPizzas(Map<Integer, String> map) {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		for (Entry<Integer, String> entry : map.entrySet()) {
			String value = entry.getValue();
			if (value != null && !value.isEmpty() && !"0".equals(value)) {
				Integer numberOfPizzas = Integer.valueOf(value);
				Pizza pizza = pizzaService.findById(entry.getKey());
				pizzas.put(pizza, numberOfPizzas);
			}
		}
		return pizzas;
	}
}
