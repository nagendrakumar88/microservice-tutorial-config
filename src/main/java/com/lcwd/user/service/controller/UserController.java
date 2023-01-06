package com.lcwd.user.service.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.service.UserServiceImpl;

@RestController
@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User>createUser(@RequestBody User user){
		
		User createUser = this.userService.createUser(user);
		
		return new ResponseEntity<User>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User>updateUser(@RequestBody User user, @PathVariable String userId){
		
		User updateUser = this.userService.updateUser(userId, user);
		
		return new ResponseEntity<User>(updateUser,HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User>getUser(@PathVariable String userId){
		
		User user = this.userService.getUser(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>>getAllUSer(){
		List<User> allUsers = this.userService.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUSer/{userId}")
	public ResponseEntity<User>deleteUser(@PathVariable String userId){
		this.userService.deleteUser(userId);
		
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}
