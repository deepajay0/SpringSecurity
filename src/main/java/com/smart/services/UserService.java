package com.smart.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.models.User;

@Service
public class UserService {
	List<User> list = new ArrayList<>();

	public UserService() {
		list.add(new User("abc","abc","abc@gmail.com"));
		list.add(new User("xyz","xyz","xyz@gmail.com"));
	}
	
	//get all users
	public List<User> getAllUser(){
		return list;
	}
	
	//get single user
	public User getUserById(String username) {
		return list.stream().filter(e->e.getUsername().equals(username)).findAny().orElse(null);
	}
	
	//add new user
	public User addUser(User user) {
		list.add(user);
		return user;
	}
	
}
