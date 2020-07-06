package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> findAllRating() {
		return ratingRepository.findAll();
	}

	public Rating addRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	public Optional<Rating> getRatingById(int id) {
		return ratingRepository.findById(id);
	}

	public void deleteRating(int id) {
		ratingRepository.deleteById(id);
	}

}
