package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@Controller
public class TradeController {
	// TODO: Inject Trade service

	@Autowired
	private TradeService tradeService;

	@RequestMapping("/trade/list")
	public String home(Model model) {
		model.addAttribute("allTrade", tradeService.findAllTrade());
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			tradeService.addTrade(trade);
			model.addAttribute("allTrade", tradeService.findAllTrade());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeService.getTradeById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));

		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			tradeService.addTrade(trade);
			model.addAttribute("allTrade", tradeService.findAllTrade());
			return "redirect:/trade/list";
		}
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		tradeService.deletetrade(id);
		model.addAttribute("allTrade", tradeService.findAllTrade());
		return "redirect:/trade/list";
	}
}
