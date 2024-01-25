package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.SubjectRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.responsedto.SubjectResponse;
import com.school.sba.utility.ResponseStrcture;

public interface SubjectService {

	ResponseEntity<ResponseStrcture<AcademicProgramResponse>> addSubject(SubjectRequest subjectRequest, int programId);



	ResponseEntity<ResponseStrcture<List<SubjectResponse>>> findAllSubjects();

}
