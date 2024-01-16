package com.school.sba.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.service.SchoolService;
import com.school.sba.utility.ResponseStrcture;

@Service
public class SchoolServiceImple implements SchoolService {
	
	@Autowired
	private SchoolRepository schrepo;

	@Override
	public ResponseEntity<ResponseStrcture<School>> addSchool(School school) {
		School sch=schrepo.save(school);
		
		ResponseStrcture<School> response=new ResponseStrcture<School>();
		
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("School Object created");
		response.setData(sch);
		
		return new ResponseEntity<ResponseStrcture<School>>(response,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStrcture<List<School>>> findSchool() {
		
		List<School> list = schrepo.findAll();
		
		ResponseStrcture<List<School>> response=new ResponseStrcture<List<School>>();
		response.setStatus(HttpStatus.FOUND.value());
		response.setMessage("All details related school found");
		response.setData(list);
		
		return new  ResponseEntity<ResponseStrcture<List<School>>>(response,HttpStatus.FOUND);
		
	}

	@Override
	public ResponseEntity<ResponseStrcture<School>> updateSchool(int id, School updateSchool) {
		Optional<School> findbyid = schrepo.findById(id);
		
		if(findbyid.isPresent())
			
		{
			School existschool = findbyid.get();
			
			updateSchool.setSchoolId(existschool.getSchoolId());
			School sch = schrepo.save(updateSchool);
			
		ResponseStrcture<School> response=new ResponseStrcture<School>();
				response.setStatus(HttpStatus.FOUND.value());
		response.setMessage("The School Object Upadted");
		response.setData(sch);
		
		return new ResponseEntity<ResponseStrcture<School>>(response,HttpStatus.OK);
		}
		else 
			return null;
		
	}

	@Override
	public ResponseEntity<ResponseStrcture<School>> deleteSchool(int id) {
Optional<School> findbyid = schrepo.findById(id);
		
		if(findbyid.isPresent())
			
		{
			School existschool = findbyid.get();
			schrepo.delete(existschool);
			 ResponseStrcture<School> response=new ResponseStrcture<School>();
				response.setStatus(HttpStatus.FOUND.value());
				response.setMessage("Teacher Object is Found");
				response.setData(existschool);
				
			return new  ResponseEntity<ResponseStrcture<School>>(response,HttpStatus.OK); 
	}
		return null;
		
		
	}

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


