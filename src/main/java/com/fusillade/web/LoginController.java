package com.fusillade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fusillade.domain.entity.Customer;
import com.fusillade.service.PizzaService;
import com.fusillade.web.support.OrderForm;

@Controller
@SessionAttributes(value = {"customer", "pizzas"})
public class LoginController {
	
	@Autowired
	private PizzaService pizzaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getCustomerPage(@RequestParam("customerId") Customer customer, Model model) {
		model.addAttribute("customer", customer);
		model.addAttribute("pizzas", pizzaService.getAll());
		OrderForm orderForm = new OrderForm();
		model.addAttribute("orderForm", orderForm);
		return "customerpage";
	}
	
	
}
