package com.school.sba.serviceimple;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.utility.ResponseStrcture;

@Service
public class SchoolServiceImple implements SchoolService {
	
	@Autowired
	private SchoolRepository shoolrepo;
	
	@Autowired
   private UserRepository userepo;

	@Override
	public  ResponseEntity<ResponseStrcture<SchoolResponse>>addSchool(SchoolRequest schoolrequest,int usreId) {
		
		return userepo.findById(usreId).map(u->{
			
			if(u.getUserRole().equals(UserRole.ADMIN))
			{
				if(u.getSchool()==null)
				{
					School school = mapToSchool(schoolrequest);
					school=shoolrepo.save(school); 
					userepo.save(u);
					
					ResponseStrcture<SchoolResponse> response=new ResponseStrcture<SchoolResponse>();
					
					response.setStatus(HttpStatus.CREATED.value());
					response.setMessage("School Object created");
					response.setData(mapToResponse(school));
					return new ResponseEntity<ResponseStrcture<SchoolResponse>>(response,HttpStatus.CREATED);
				}else
					throw new RuntimeException();
			}else
				throw new RuntimeException();
		}).orElseThrow(()->new RuntimeException());
	}

	

	@Override
	public ResponseEntity<ResponseStrcture<List<School>>> findSchool() {
		
		List<School> list = shoolrepo.findAll();
		
		ResponseStrcture<List<School>> response=new ResponseStrcture<List<School>>();
		response.setStatus(HttpStatus.FOUND.value());
		response.setMessage("All details related school found");
		response.setData(list);
		
		return new  ResponseEntity<ResponseStrcture<List<School>>>(response,HttpStatus.FOUND);
		
	}

	@Override
	public ResponseEntity<ResponseStrcture<School>> updateSchool(int id, School updateSchool) {
		Optional<School> findbyid = shoolrepo.findById(id);
		
		if(findbyid.isPresent())
			
		{
			School existschool = findbyid.get();
			
			updateSchool.setSchoolId(existschool.getSchoolId());
			School sch = shoolrepo.save(updateSchool);
			
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
Optional<School> findbyid = shoolrepo.findById(id);
		
		if(findbyid.isPresent())
			
		{
			School existschool = findbyid.get();
			shoolrepo.delete(existschool);
			 ResponseStrcture<School> response=new ResponseStrcture<School>();
				response.setStatus(HttpStatus.FOUND.value());
				response.setMessage("Teacher Object is Found");
				response.setData(existschool);
				
			return new  ResponseEntity<ResponseStrcture<School>>(response,HttpStatus.OK); 
	}
		return null;
		
		
	}

	

	private School mapToSchool(SchoolRequest schoolrequest) {
		return School.builder()
				.schoolId(schoolrequest.getSchoolId())
				.schoolName(schoolrequest.getSchoolName())
				.email(schoolrequest.getEmail())
				.address(schoolrequest.getAddress())
				.contactNo(schoolrequest.getContactNo())
				.build();
		
	}

	private SchoolResponse mapToResponse(School school) {
	return SchoolResponse.builder()
			.schoolName(school.getSchoolName())
			.email(school.getEmail())
			.address(school.getAddress())
			.contactNo(school.getContactNo())
			.build();
			
	}
		
		
		
	}


