package com.school.sba.serviceimple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Subject;
import com.school.sba.repository.AacdemicProgramRepository;
import com.school.sba.repository.SubjectRepo;
import com.school.sba.requestdto.SubjectRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.responsedto.SubjectResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.utility.ResponseStrcture;

@Service
public class SubjectImple implements SubjectService {

	@Autowired
	private AacdemicProgramRepository academicrepo;

	@Autowired
	private SubjectRepo subjrepo;
	
	@Autowired
	private ResponseStrcture<List<SubjectResponse>> listStructure;
	
	@Autowired
	private AacdemicProgramImple acdemicimpl;
	
	@Autowired
	private ResponseStrcture<AcademicProgramResponse> structure;

	@Override
	public ResponseEntity<ResponseStrcture<AcademicProgramResponse>> addSubject( SubjectRequest subjectRequest,int programId) {
		return academicrepo.findById(programId)
				.map(academicProgram -> {
					List<Subject> listOfSubjects = (academicProgram.getListOfSubject() != null) ? academicProgram.getListOfSubject() : new ArrayList<Subject>();


					// to add the new project that are specified by the client
					subjectRequest.getSubjectNames().forEach(name -> {
						boolean isPresent = false;
						for(Subject subject : listOfSubjects) {
							isPresent = (name.equalsIgnoreCase(subject.getSubjectName())) ? true : false;
							if(isPresent) break;
						}
						if(!isPresent) {
							listOfSubjects.add(subjrepo.findBySubjectName(name)
									.orElseGet(()-> subjrepo.save(Subject.builder().subjectName(name).build())));
						}
					});


					//to remove the subject that are not specified by the client
					List<Subject> toBeRemoved = new ArrayList<Subject>();
					listOfSubjects.forEach(subject -> {
						boolean isPresent = false;
						for(String name : subjectRequest.getSubjectNames()) {
							isPresent = (subject.getSubjectName().equalsIgnoreCase(name)) ? true : false;
							if(isPresent) break;
						}
						if(!isPresent) toBeRemoved.add(subject);
					});

					listOfSubjects.removeAll(toBeRemoved);

					academicProgram.setListOfSubject(listOfSubjects);

					academicrepo.save(academicProgram);

					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("subjects have been updated successfully");
					structure.setData(acdemicimpl.mapToAcademicProgramResponse(academicProgram));

					return new ResponseEntity<ResponseStrcture<AcademicProgramResponse>>(structure, HttpStatus.CREATED);

				})
				.orElseThrow(() -> new RuntimeException());

	}

				
				
				
		@Override
				public ResponseEntity<ResponseStrcture<List<SubjectResponse>>> findAllSubjects() {
					List<Subject> listOfSubjects = subjrepo.findAll();

					if(listOfSubjects.isEmpty()) {
						listStructure.setStatus(HttpStatus.NOT_FOUND.value());
						listStructure.setMessage("No subjects found");
						listStructure.setData(mapToResponse(listOfSubjects));

						return new ResponseEntity<ResponseStrcture<List<SubjectResponse>>>(listStructure,HttpStatus.NOT_FOUND);
					}
					else {
						listStructure.setStatus(HttpStatus.FOUND.value());
						listStructure.setMessage("list of subjects found");
						listStructure.setData(mapToResponse(listOfSubjects));

						return new ResponseEntity<ResponseStrcture<List<SubjectResponse>>>(listStructure, HttpStatus.FOUND);
					}

				}


				private List<SubjectResponse> mapToResponse(List<Subject> listOfSubject)
				{
					List<SubjectResponse> listOfSubjectResponse=new ArrayList<SubjectResponse>();
					listOfSubject.forEach(subject->{
						SubjectResponse sr=new SubjectResponse();
						sr.setSubjectId(subject.getSubjectId());
						sr.setSubjectNames(subject.getSubjectName());
						listOfSubjectResponse.add(sr);
					});
					return listOfSubjectResponse;
				}


	}
