package com.school.sba.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.utility.ResponseStrcture;
@Service
public class UserServiceImple implements UserService{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ResponseStrcture<UserResponse> resp;
	
	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> addUser(UserRequest user) {
		
		User userobj = mapToUser(user);
		if(userobj.getUserRole().equals(UserRole.ADMIN))
		{
			boolean existsByUser = userrepo.existsByUserRole(user.getUserRole());
			System.out.println("validating if admin is prasent or not");
			if(existsByUser==false)
			{
				System.out.println("admin not prasent");
				userrepo.save(mapToUser(user));
				resp.setMessage("user object entered sucessfully");
				resp.setStatus(HttpStatus.CREATED.value());
				resp.setData(mapToUserResponse(userobj));
			}
			else
				System.out.println("exist");
			throw new RuntimeException();
		}
		else {
			User user11 = userrepo.save(mapToUser(user));
			
		resp.setMessage("user object entered sucessfully");
		resp.setStatus(HttpStatus.CREATED.value());
		resp.setData(mapToUserResponse(user11));
		
		return new ResponseEntity<ResponseStrcture<UserResponse>> (resp,HttpStatus.CREATED);
	}
	}
	
	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> findUser(int userId) 
	{
	User userobj = userrepo.findById(userId).orElseThrow(()->new RuntimeException());
	
	resp.setStatus(HttpStatus.FOUND.value());
	resp.setMessage("user found by id");
	resp.setData(mapToUserResponse(userobj));
	
	return new ResponseEntity<ResponseStrcture<UserResponse>>(resp,HttpStatus.FOUND);
	}

	
	private User mapToUser(UserRequest req)
	{
		return User.builder()
				.userName(req.getUserName())
				.password(req.getPassword())
				.firstName(req.getFirstName())
				.lastName(req.getLastName())
				.email(req.getEmail())
				.contactNo(req.getContactNo())
				.userRole(req.getUserRole())
				.build();
	}
	private UserResponse mapToUserResponse(User user)
	{
		return UserResponse.builder()
				.userName(user.getUserName())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.contactNo(user.getContactNo())
				.userRole(user.getUserRole())
				.build();
	}


}
