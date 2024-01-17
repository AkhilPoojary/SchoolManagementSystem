package com.school.sba.responsedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SchoolResponse {
	private int schoolId;
	private String schoolName;
	private long contactNo;
	private String email;
	private String address;
}
