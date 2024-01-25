package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.utility.ResponseStrcture;

public interface AcademicProgramService {

	ResponseEntity<ResponseStrcture<AcademicProgramResponse>> addAcademicProgram(AcademicProgramRequest acdemicProgram,int schoolId);

	ResponseEntity<ResponseStrcture<List<AcademicProgramResponse>>> findAll(int schoolId);



}
