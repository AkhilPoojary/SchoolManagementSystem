package com.school.sba.requestdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SchoolRequest {
	private int schoolId;
	private String schoolName;
	private long contactNo;
	private String email;
	private String address;
}
