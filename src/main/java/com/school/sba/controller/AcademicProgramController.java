package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.entity.School;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.service.AcademicProgramService;
import com.school.sba.utility.ResponseStrcture;

@RestController
public class AcademicProgramController {
	
	@Autowired
	private AcademicProgramService academicService;
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/schools/{schoolId}/academicprograms")
	public ResponseEntity<ResponseStrcture<AcademicProgramResponse>> addAcademicPrograms(@RequestBody AcademicProgramRequest acdemicProgram,@PathVariable int schoolId)
	{
		return academicService.addAcademicProgram(acdemicProgram,schoolId);
	}
	
	@GetMapping(" /schools/{schoolId}/academicprograms")
	public ResponseEntity<ResponseStrcture<List<AcademicProgramResponse>>> findAllAcademic(@PathVariable int schoolId)
	{
		return academicService.findAll(schoolId);
	}
	
	@DeleteMapping("/academicprograms/{programid}")
	public ResponseEntity<ResponseStrcture<AcademicProgramResponse>> Delete(@PathVariable int programid)
	{
		return academicService.isDelete(programid);
	}

}
