package com.school.sba.serviceimple;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public  ResponseEntity<ResponseStrcture<SchoolResponse>>addSchool(SchoolRequest schoolrequest) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userepo.findByUserName(username).map(u->{

			if(u.getUserRole().equals(UserRole.ADMIN))
			{
				if(u.getSchool()==null)
				{
					School school = mapToSchool(schoolrequest);
					shoolrepo.save(school); 
					userepo.findAll().forEach(user->{
						user.setSchool(school);
						userepo.save(user);
					});
					

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
	public ResponseEntity<ResponseStrcture<SchoolResponse>> findSchool(int schoolId) {
		School school = shoolrepo.findById(schoolId).orElseThrow(()->new RuntimeException());
		
		ResponseStrcture<SchoolResponse> response=new ResponseStrcture<SchoolResponse>();
		response.setStatus(HttpStatus.FOUND.value());
		response.setMessage("All details related school found");
	    response.setData(mapToResponse(school));

		return new ResponseEntity<ResponseStrcture<SchoolResponse>>(response,HttpStatus.FOUND);

	}

	

	@Override
	public ResponseEntity<ResponseStrcture<SchoolResponse>> deleteSchool(int schoolId) {
		
		School existingSchool = shoolrepo.findById(schoolId)
				.orElseThrow(() -> new RuntimeException());
		

	
		
			shoolrepo.delete(existingSchool);
			ResponseStrcture<SchoolResponse> response=new ResponseStrcture<SchoolResponse>();
			response.setStatus(HttpStatus.FOUND.value());
			response.setMessage("Teacher Object is Found");
			response.setData(mapToResponse(existingSchool));

			return new ResponseEntity<ResponseStrcture<SchoolResponse>>(response,HttpStatus.FOUND);
		}
		

	


	

	private School mapToSchool(SchoolRequest schoolrequest) {
		return School.builder()
				.schoolName(schoolrequest.getSchoolName())
				.email(schoolrequest.getEmail())
				.address(schoolrequest.getAddress())
				.contactNo(schoolrequest.getContactNo())
				.build();

	}

	private SchoolResponse mapToResponse(School school) {
		return SchoolResponse.builder()
				.schoolId(school.getSchoolId())
				.schoolName(school.getSchoolName())
				.email(school.getEmail())
				.address(school.getAddress())
				.contactNo(school.getContactNo())
				.build();

	}



	@Override
	public ResponseEntity<ResponseStrcture<SchoolResponse>> updateSchool(int schoolId, SchoolRequest updaeSchool) {
		School existingSchool = shoolrepo.findById(schoolId)
				.map(u -> {
					School school = mapToSchool(updaeSchool);
					school.setSchoolId(schoolId);
					return shoolrepo.save(school);
				})
				.orElseThrow(() -> new RuntimeException());

		ResponseStrcture<SchoolResponse> response=new ResponseStrcture<SchoolResponse>();
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("School data updated successfully in database");
		response.setData(mapToResponse(existingSchool));

		return new ResponseEntity<ResponseStrcture<SchoolResponse>>(response, HttpStatus.OK);
	}



}


