package com.school.sba.requestdto;

import java.time.LocalDate;
import java.time.LocalTime;

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
	
	
		
	private ProgramType programType;
	private String programName;
	private LocalDate beginTime;
	private LocalDate endTime;
	private boolean isDelete;
	
}
