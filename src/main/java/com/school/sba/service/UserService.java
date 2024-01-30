package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.enums.UserRole;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.utility.ResponseStrcture;

public interface UserService {

	ResponseEntity<ResponseStrcture<UserResponse>> addAdmin(UserRequest user);
	
	ResponseEntity<ResponseStrcture<UserResponse>> addOtherUser(UserRequest user);

	ResponseEntity<ResponseStrcture<UserResponse>> findUser(int userId);

	ResponseEntity<ResponseStrcture<UserResponse>> deleteTheUser(int userId);

	ResponseEntity<ResponseStrcture<UserResponse>> updateUser(int userId, UserRequest userRequest);

	ResponseEntity<ResponseStrcture<UserResponse>> assignToAcademicProgram(int programId, int userId);

	ResponseEntity<ResponseStrcture<UserResponse>> assignSubjectToTeacher(int subjectId, int userId);

	ResponseEntity<ResponseStrcture<List<UserResponse>>> getUserRoleAcademic(String role, int programId);







}
