package com.school.sba.requestdto;

import com.school.sba.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder

public class UserRequest {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private long contactNo;
	private String email;
	private UserRole userRole;
}
