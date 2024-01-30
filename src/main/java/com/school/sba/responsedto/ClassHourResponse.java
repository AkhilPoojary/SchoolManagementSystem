package com.school.sba.responsedto;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import com.school.sba.enums.ClassStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ClassHourResponse {
	private int classHourId;
	private LocalTime beginsAt;
	private LocalTime endAt;
	private int roomNo;
	private ClassStatus classStatus;
	private DayOfWeek day;
	private LocalDate date;

}
