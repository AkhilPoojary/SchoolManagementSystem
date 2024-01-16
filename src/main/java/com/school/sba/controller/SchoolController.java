package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.service.SchoolService;
import com.school.sba.utility.ResponseStrcture;

@RestController
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schseri;
	
	@PostMapping
	public ResponseEntity<ResponseStrcture<School>> addSchoolContr(@RequestBody School school)
	{
		return schseri.addSchool(school);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStrcture<List<School>>> findAllSchool()
	{
		return schseri.findSchool();
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStrcture<School>> updateSchoolController(@RequestParam int id,@RequestBody School school)
	{
		return schseri.updateSchool(id, school);
	}
	
	
	@PutMapping("/delete")
	public ResponseEntity<ResponseStrcture<School>> updateSchoolController(@RequestParam int id)
	{
		return schseri.deleteSchool(id);
	}
	

	
	

}
