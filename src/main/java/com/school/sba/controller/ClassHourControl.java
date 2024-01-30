package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.repository.ClassHourRepository;
import com.school.sba.requestdto.ClassHourRequest;
import com.school.sba.responsedto.ClassHourResponse;
import com.school.sba.service.ClassHourService;
import com.school.sba.utility.ResponseStrcture;

@RestController
public class ClassHourControl {
	
	@Autowired
	private ClassHourService classHourService;
	
	@PostMapping("/academic-program/{programId}/class-hours")
	private ResponseEntity<ResponseStrcture<String>> autogenerate(@PathVariable int programId)
	{
		return classHourService.generateClassHourForAcademics(programId);
	}
	
	@PutMapping("/class-hours")
	private ResponseEntity<ResponseStrcture<List<ClassHourResponse>>> assigningPeriods(List<ClassHourRequest> classHourRequest )
	{
		return classHourService.updateClassHour(classHourRequest);
	}

}
