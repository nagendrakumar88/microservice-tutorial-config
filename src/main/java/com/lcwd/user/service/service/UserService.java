package com.lcwd.user.service.service;

import java.util.List;

import com.lcwd.user.service.entity.User;

public interface UserService {
	
	public User createUser(User user);
	
	User updateUser(String userId,User user);
	
	User getUser(String userId);
	
	List<User>getAllUsers();
	
	void deleteUser(String userId);

}
