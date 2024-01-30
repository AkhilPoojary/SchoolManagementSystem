package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.enums.UserRole;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.utility.ResponseStrcture;

@RestController
public class UserController {

	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStrcture<UserResponse>> addAdmin(@RequestBody UserRequest user)
	{
		return userservice.addAdmin(user);
	}
	
	@PostMapping("/users")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStrcture<UserResponse>> addOtherUser(@RequestBody UserRequest user)
	{
		return userservice.addOtherUser(user);
	}
	
	@GetMapping("users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> findOneUser(@PathVariable int userId)
	{
		return userservice.findUser(userId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> deleteUser(@PathVariable int userId)
	{
		return userservice.deleteTheUser(userId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> updateUser(@PathVariable("userId") int userId,
			@RequestBody UserRequest userRequest){
		return userservice.updateUser(userId, userRequest);
	}
	
	
	@PreAuthorize("hasAuthority('TEACHER') OR hasAuthority('STUDENT')")
	@PutMapping("/academic-programs/{programId}/users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> assignToAcademicProgram(@PathVariable("programId") int programId,
			@PathVariable("userId") int userId){
		return userservice.assignToAcademicProgram(programId, userId);
	}
	
	@PutMapping("/subjects/{subjectId}/users/{userId}")
	public ResponseEntity<ResponseStrcture<UserResponse>> assignSubjectToTeacher(@PathVariable("subjectId") int subjectId,
			@PathVariable("userId") int userId){
		return userservice.assignSubjectToTeacher(subjectId,userId);
	}
	
	@GetMapping("/academic-programs/{programId}/user-roles/{role}/users")
	public ResponseEntity<ResponseStrcture<List<UserResponse>>> getUserRoleAcademic(@PathVariable String role,@PathVariable int programId)
	{
		return userservice.getUserRoleAcademic(role,programId);
	}
	
}
