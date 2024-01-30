package com.school.sba.requestdto;

import java.time.DayOfWeek;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SchoolRequest {
	
	private String schoolName;
	private long contactNo;
	private String email;
	private String address;
	private DayOfWeek day;
}
