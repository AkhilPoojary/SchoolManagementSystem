package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.utility.ResponseStrcture;

@RestController

public class SchoolController {
	
	@Autowired
	private SchoolService schoolservice;
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/users/schools")
	public ResponseEntity<ResponseStrcture<SchoolResponse>> addSchoolContr(@RequestBody SchoolRequest school)
	{
		return schoolservice.addSchool(school);
	}
	
	@GetMapping("/schools/{schoolId}")
	public ResponseEntity<ResponseStrcture<SchoolResponse>> findSchool(@PathVariable int schoolId)
	{
		return schoolservice.findSchool(schoolId);
	}
	
	@PutMapping("/schools/{schoolId}")
	public ResponseEntity<ResponseStrcture<SchoolResponse>> updateSchool(@PathVariable int schoolId,@RequestBody SchoolRequest school)
	{
		return schoolservice.updateSchool(schoolId, school);
	}
	
	@DeleteMapping("/schools/{schoolId}")
	public ResponseEntity<ResponseStrcture<SchoolResponse>> deleteSchool(@PathVariable Integer schoolId){
		return schoolservice.deleteSchool(schoolId);
	}
	
	
	

	
	

}
