package com.school.sba.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.AacdemicProgramRepository;
import com.school.sba.repository.SubjectRepo;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.utility.ResponseStrcture;
@Service
public class UserServiceImple implements UserService{

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private AacdemicProgramRepository academicProgramRepository;

	@Autowired
	private ResponseStrcture<UserResponse> resp;

	@Autowired
	private SubjectRepo subjerepo;

	@Autowired
	private PasswordEncoder encoder;

	private School school;

	//	@Override
	//	public ResponseEntity<ResponseStrcture<UserResponse>> addUser(UserRequest user) {
	//
	//		User userobj = mapToUser(user);
	//		if(userobj.getUserRole().equals(UserRole.ADMIN))
	//		{
	//			boolean existsByUser = userrepo.existsByUserRole(user.getUserRole());
	//			System.out.println("validating if admin is prasent or not");
	//			if(existsByUser==false)
	//			{
	//				System.out.println("admin not prasent");
	//				userrepo.save(mapToUser(user));
	//				resp.setMessage("user object entered sucessfully");
	//				resp.setStatus(HttpStatus.CREATED.value());
	//				resp.setData(mapToUserResponse(userobj));
	//			}
	//			else
	//				System.out.println("exist");
	//			throw new RuntimeException();
	//		}
	//		else {
	//			
	//		
	//			User user11 = userrepo.save(mapToUser(user));
	//
	//			resp.setMessage("user object entered sucessfully");
	//			resp.setStatus(HttpStatus.CREATED.value());
	//			resp.setData(mapToUserResponse(user11));
	//
	//			return new ResponseEntity<ResponseStrcture<UserResponse>> (resp,HttpStatus.CREATED);
	//		}
	//	}

	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> findUser(int userId) 
	{
		User userobj = userrepo.findById(userId).orElseThrow(()->new RuntimeException());

		resp.setStatus(HttpStatus.FOUND.value());
		resp.setMessage("user found by id");
		resp.setData(mapToUserResponse(userobj));

		return new ResponseEntity<ResponseStrcture<UserResponse>>(resp,HttpStatus.FOUND);
	}


	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> deleteTheUser(int userId) {
		User userObj = userrepo.findById(userId).orElseThrow(()->new RuntimeException());

		if(userObj.isDelete())
		{
			throw new RuntimeException();
		}
		userObj.setDelete(true);
		User delobje = userrepo.save(userObj);

		resp.setStatus(HttpStatus.OK.value());
		resp.setMessage("user left the institution");
		resp.setData(mapToUserResponse(delobje));

		return new ResponseEntity<ResponseStrcture<UserResponse>>(resp,HttpStatus.OK);
	}


	private User mapToUser(UserRequest req)
	{
		return User.builder()
				.userName(req.getUserName())
				.password(encoder.encode(req.getPassword()))
				.firstName(req.getFirstName())
				.lastName(req.getLastName())
				.email(req.getEmail())
				.contactNo(req.getContactNo())
				.userRole(req.getUserRole())
				.build();
	}
	private UserResponse mapToUserResponse(User user)
	{
		return UserResponse.builder()
				.userName(user.getUserName())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.contactNo(user.getContactNo())
				.userRole(user.getUserRole())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> updateUser(int userId, UserRequest userRequest) {
		User user = userrepo.findById(userId)
				.map( u -> {
					User mappedUser = mapToUser(userRequest);
					mappedUser.setUserId(userId);
					return userrepo.save(mappedUser);
				})
				.orElseThrow(() -> new RuntimeException());

		resp.setStatus(HttpStatus.OK.value());
		resp.setMessage("user updated successfully");
		resp.setData(mapToUserResponse(user));

		return new  ResponseEntity<ResponseStrcture<UserResponse>>(resp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> assignToAcademicProgram(int programId, int userId) {
		return userrepo.findById(userId)
				.map(user -> {
					if(user.getUserRole().equals(UserRole.ADMIN)) {
						throw new RuntimeException();
					}
					else {
						return academicProgramRepository.findById(programId)
								.map(academicProgram -> {
									academicProgram.getListOfUsers().add(user);
									user.getListOfAcademicPrograms().add(academicProgram);

									userrepo.save(user);
									academicProgramRepository.save(academicProgram);

									resp.setStatus(HttpStatus.OK.value());
									resp.setMessage("user updated successfully");
									resp.setData(mapToUserResponse(user));

									return new  ResponseEntity<ResponseStrcture<UserResponse>>(resp, HttpStatus.OK);

								})
								.orElseThrow(() -> new RuntimeException());
					}
				})
				.orElseThrow(() -> new RuntimeException());
	}

	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> assignSubjectToTeacher(int subjectId, int userId) {
		return userrepo.findById(userId)
				.map(user -> {
					if(user.getUserRole().equals(UserRole.TEACHER)) {
						return subjerepo.findById(subjectId)
								.map(subject -> {
									user.setSubject(subject);
									userrepo.save(user);

									resp.setStatus(HttpStatus.OK.value());
									resp.setMessage("subject assigned to teacher successfully");
									resp.setData(mapToUserResponse(user));

									return new ResponseEntity<ResponseStrcture<UserResponse>>(resp, HttpStatus.OK);
								})
								.orElseThrow(() -> new RuntimeException() );
					}
					else {
						throw  new RuntimeException();
					}
				})
				.orElseThrow(() ->  new RuntimeException());
	}


	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> addAdmin(UserRequest user) {
		User user1 = mapToUser(user);
		if(user1.getUserRole().equals(UserRole.ADMIN))
		{
			userrepo.save(user1);

			resp.setData(mapToUserResponse(user1));
			resp.setMessage("admin created successfully");
			resp.setStatus(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStrcture<UserResponse>>(resp,HttpStatus.CREATED);
		}
		else
			throw new RuntimeException();
	}


	@Override
	public ResponseEntity<ResponseStrcture<UserResponse>> addOtherUser(UserRequest user) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		if(user.getUserRole().equals(UserRole.ADMIN))
		{
			throw new RuntimeException();
		}
		else
		{
			return	userrepo.findByUserName(username).map(admin->{


				school = admin.getSchool();
				User user1 = userrepo.save(mapToUser(user));
				user1.setSchool(school);
				User user2 = userrepo.save(user1);

				resp.setData(mapToUserResponse(user2));
				resp.setMessage("admin created successfully");
				resp.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStrcture<UserResponse>>(resp,HttpStatus.CREATED);
			}).orElseThrow(()->new RuntimeException());
		}

	}
}




