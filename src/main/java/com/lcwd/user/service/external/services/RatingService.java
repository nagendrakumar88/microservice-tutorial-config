package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entity.Ratings;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	//get
	
	
	//post
	
	
	//create
	
	@PostMapping("/ratings/saveRating")
	public Ratings createRating(Ratings rating);
	
	//put
	
	@PutMapping("updateRating/{ratingId}")
	public Ratings updateRating(@PathVariable("ratingId")String ratingId, Ratings rating);
	
	
	//delete
	@DeleteMapping("/deleteRating/{ratingId}")
	public void deleteRating(String ratingId);

}
