package com.fusillade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fusillade.domain.entity.Pizza;
import com.fusillade.domain.entity.enums.PizzaType;
import com.fusillade.service.PizzaService;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/pizzas";
	}

	@RequestMapping(value = "/pizzas", method = RequestMethod.GET)
	public String showAllPizzas(Model model) {
		model.addAttribute("pizzas", pizzaService.getAll());
		return "pizzaslist";
	}

	@RequestMapping(value = "/pizzas/{id}/delete", method = RequestMethod.POST)
	public String deletePizza(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		try {
		pizzaService.delete(id);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Can't delete pizza!");
		}
		return "redirect:/pizzas";
	}
	
	@RequestMapping(value = "/pizzas/{id}/update", method = RequestMethod.GET)
	public String showPizzaUpdateForm(@PathVariable("id") int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizzaForm", pizza);
		return "updatepizza";
	}
	
	@RequestMapping(value = "/pizzas", method = RequestMethod.POST)
	public String saveOrUpdatePizza(@ModelAttribute("pizzaForm") Pizza pizza, Model model) {
		pizzaService.save(pizza);
		return "redirect:/pizzas";
	}
	
	@RequestMapping(value = "/pizzas/add", method = RequestMethod.GET)
	public String showPizzaAddForm(Model model) {
		Pizza pizza = new Pizza();
		// DEFAULT SETTINGS FOR TESTING
		pizza.setName("DEFAULT_PIZZA");
		pizza.setPrice(99.99d);
		pizza.setType(PizzaType.Meat);
		// DEFAULT SETTINGS FOR TESTING
		model.addAttribute("pizzaForm", pizza);
		return "updatepizza";
	}

}
