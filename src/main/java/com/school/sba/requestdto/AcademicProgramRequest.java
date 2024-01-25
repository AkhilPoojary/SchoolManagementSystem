package com.school.sba.requestdto;

import java.time.LocalTime;

import com.school.sba.entity.School;
import com.school.sba.enums.ProgramType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicProgramRequest {
	
	
	private int programid;	
	private ProgramType programType;
	private String programName;
	private LocalTime beginTime;
	private LocalTime endTime;
	
}
