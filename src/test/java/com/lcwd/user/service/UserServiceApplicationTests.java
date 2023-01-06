package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.user.service.entity.Ratings;
import com.lcwd.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {
	
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}
	
	/*
	 * @Test public void createRating() {
	 * 
	 * Ratings rating =
	 * Ratings.builder().rating(10).userId("").feedback("This is rating test").
	 * userId("").build();
	 * 
	 * Ratings saveRating = ratingService.createRating(rating);
	 * 
	 * System.out.println("new rating created..."); }
	 */

}
