package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.utility.ResponseStrcture;

@RestController
public class UserController {

	@Autowired
	private UserService usercontrol;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStrcture<UserResponse>> addUser(@RequestBody UserRequest user)
	{
		return usercontrol.addUser(user);
	}
	@GetMapping("users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> findOneUser(@PathVariable int userId)
	{
		return usercontrol.findUser(userId);
	}
	@DeleteMapping("users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> deleteUser(@PathVariable int userId)
	{
		return usercontrol.deleteTheUser(userId);
	}
	
	
	
	
}
