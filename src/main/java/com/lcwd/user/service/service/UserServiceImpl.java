package com.lcwd.user.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Ratings;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelService hoteService;


	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Override
	public User createUser(User user) {
	
		String random = UUID.randomUUID().toString();
		user.setUserId(random);
		return this.userRepository.save(user);
	}

		

	@Override
	public User updateUser(String userId, User user) {
		User user1=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("This User Not Present in database"));
		
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		user1.setCity(user.getCity());
		user1.setAbout(user.getAbout());
		
		return this.userRepository.save(user1);
	}

	@Override
	public User getUser(String userId) {
		//get user by id
	 User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("The User Not Present in database..."));
	
	 //fetch rating
	 
	  Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Ratings[].class);
	 
	List<Ratings> ratings = Arrays.asList(ratingsOfUser);
	 //http://localhost:9191/ratings/users/b209f729-4247-4f92-a865-67b3b910ef51
	 //get Rating service
	 
	 logger.info("{} ",ratingsOfUser);
	  
		 List<Ratings> ratingList = ratings.stream().map(rating->{
			 
			 //api call to hotel service to get Hotel
			 //http://localhost:9494/hotels/getHotel/05635945-7ab3-4c42-a4b4-125976b607ff
			 //if we want use restTemplate then use this line
			 //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getHotel/"+rating.getHotelId(), Hotel.class);
			Hotel hotel =hoteService.getHotel(rating.getHotelId());
			// logger.info("Responce Status code ",forEntity.getStatusCode());
			 //set the hotel to rating 
			 
			rating.setHotel(hotel);
			 //return the hotel
			  
			 return rating;
			 
		 }).collect(Collectors.toList());
		
	 user.setRatings(ratingList);
	 return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		return this.userRepository.findAll();
	}

	@Override
	public void deleteUser(String userId) {
	User user=	this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("The User Not Present in database..."));
		this.userRepository.delete(user);
	}

	
}
