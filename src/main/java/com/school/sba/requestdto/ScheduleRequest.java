package com.school.sba.requestdto;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ScheduleRequest {


	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private int classHourLengthInMin;
	private LocalTime breakTime;
	private int breakLeangthInMin;
	private LocalTime lunchTime;
	private int lunchLengthInMin;
}
