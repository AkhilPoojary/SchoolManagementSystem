package com.school.sba.requestdto;

import com.school.sba.entity.School;
import com.school.sba.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserRequest {

	
	@NotNull(message = "user name should not be null")
	@NotBlank(message = "user name should not be empty")
	private String userName;
	

	private String password;
	
	@NotEmpty(message = "first name should not be empty")
	private String firstName;
	
	@NotEmpty(message = "first name should not be empty")
	private String lastName;
	
	
	private long contactNo;
	
	@NotEmpty(message = "email should not be empty")
	private String email;
	
	private UserRole userRole;
	
	private School school;
}
