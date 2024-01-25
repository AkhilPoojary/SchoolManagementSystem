package com.school.sba.responsedto;

import java.time.LocalTime;
import java.util.List;

import com.school.sba.enums.ProgramType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicProgramResponse {
	
	private int programid;	
	private ProgramType programType;
	private String programName;
	private LocalTime beginTime;
	private LocalTime endTime;
	
	private List<String> listOfSubjects;
}
