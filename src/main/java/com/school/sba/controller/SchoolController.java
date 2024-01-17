package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.utility.ResponseStrcture;

@RestController
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolservice;
	
	@PostMapping("/users/{userId}/schools")
	public ResponseEntity<ResponseStrcture<SchoolResponse>> addSchoolContr(@RequestBody SchoolRequest school,@PathVariable int userId)
	{
		return schoolservice.addSchool(school,userId);
	}
	
	@GetMapping("/schools")
	public ResponseEntity<ResponseStrcture<List<School>>> findAllSchool()
	{
		return schoolservice.findSchool();
	}
	
	@PutMapping("/schools/{schoolId}")
	public ResponseEntity<ResponseStrcture<School>> updateSchoolController(@RequestParam int id,@RequestBody School school)
	{
		return schoolservice.updateSchool(id, school);
	}
	
	
	@DeleteMapping("/schools/{schoolId}")
	public ResponseEntity<ResponseStrcture<School>> updateSchoolController(@RequestParam int id)
	{
		return schoolservice.deleteSchool(id);
	}
	
	
	

	
	

}
