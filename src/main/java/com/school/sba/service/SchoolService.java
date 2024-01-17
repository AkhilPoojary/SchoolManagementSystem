package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.utility.ResponseStrcture;

public interface SchoolService {
	
	public ResponseEntity<ResponseStrcture<SchoolResponse>> addSchool(SchoolRequest school,int userId);
	
	public ResponseEntity<ResponseStrcture<List<School>>> findSchool();
	
	public ResponseEntity<ResponseStrcture<School>> updateSchool(int id,School updaeSchool);
	
	public ResponseEntity<ResponseStrcture<School>> deleteSchool(int id);
	
	
	
	

								

}
