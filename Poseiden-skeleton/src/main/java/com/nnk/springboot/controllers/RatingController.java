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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		model.addAttribute("ratingList", ratingService.findAllRating());
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ratingService.addRating(rating);
			model.addAttribute("ratingList", ratingService.findAllRating());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getRatingById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));

		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			ratingService.addRating(rating);
			model.addAttribute("ratingList", ratingService.findAllRating());
			return "redirect:/rating/list";
		}
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingService.deleteRating(id);
		model.addAttribute("ratingList", ratingService.findAllRating());
		return "redirect:/rating/list";
	}
}
