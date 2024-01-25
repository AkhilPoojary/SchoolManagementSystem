package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.SubjectRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.responsedto.SubjectResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.utility.ResponseStrcture;

@RestController
public class SubjectController {
	
	@Autowired
	private SubjectService subservice;
	
@PostMapping(" /academicprograms/{programId}/subjects")
public ResponseEntity<ResponseStrcture<AcademicProgramResponse>> addSubject(@RequestBody SubjectRequest subjectRequest,@PathVariable int programId)
{
	return subservice.addSubject(subjectRequest,programId);
}

@GetMapping("/subjects")
public ResponseEntity<ResponseStrcture<List<SubjectResponse>>> findAllSubjects()
{
	return subservice.findAllSubjects();
}

}
