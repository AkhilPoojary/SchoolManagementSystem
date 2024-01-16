package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.utility.ResponseStrcture;

public interface UserService {

	ResponseEntity<ResponseStrcture<UserResponse>> addUser(UserRequest user);

	ResponseEntity<ResponseStrcture<UserResponse>> findUser(int userId);

}
