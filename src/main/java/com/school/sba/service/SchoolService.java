package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.utility.ResponseStrcture;

public interface SchoolService {
	
	public ResponseEntity<ResponseStrcture<School>> addSchool(School school);
	
	public ResponseEntity<ResponseStrcture<List<School>>> findSchool();
	
	public ResponseEntity<ResponseStrcture<School>> updateSchool(int id,School updaeSchool);
	
	public ResponseEntity<ResponseStrcture<School>> deleteSchool(int id);
	
	
	
	

								

}
