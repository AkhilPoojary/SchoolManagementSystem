package com.school.sba.serviceimple;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.School;
import com.school.sba.entity.Subject;
import com.school.sba.repository.AacdemicProgramRepository;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.service.AcademicProgramService;
import com.school.sba.utility.ResponseStrcture;





@Service
public class AacdemicProgramImple implements AcademicProgramService
{

	@Autowired
	private SchoolRepository scholrepo;

	@Autowired
	private AacdemicProgramRepository academicrepo;


	@Autowired
	private ResponseStrcture<AcademicProgramResponse> structure;


	@Autowired
	private ResponseStrcture<List<AcademicProgramResponse>> listStructure;

	@Override
	public ResponseEntity<ResponseStrcture<AcademicProgramResponse>> addAcademicProgram(AcademicProgramRequest acdemicProgram, int schoolId)
	{
		return scholrepo.findById(schoolId).map(school->{
			AcademicProgram academyobj = academicrepo.save(mapToAcademicProgram(acdemicProgram));

			school.getListOfAcademicPrograms().add(academyobj);
			school = scholrepo.save(school);
			academyobj.setSchool(school);
		    academicrepo.save(academyobj);


			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Academic program created successfully");
			structure.setData(mapToAcademicProgramResponse(academyobj));

			return new ResponseEntity<ResponseStrcture<AcademicProgramResponse>>(structure, HttpStatus.CREATED);
		}).orElseThrow(()->new RuntimeException());
	}
	

	@Override
	public ResponseEntity<ResponseStrcture<List<AcademicProgramResponse>>> findAll(int schoolId) {
		return scholrepo.findById(schoolId).map(school->{

			List<AcademicProgram> listAcdemic = academicrepo.findAll();
			List<AcademicProgramResponse> listofacdemicstream = listAcdemic.stream()
					.map(this::mapToAcademicProgramResponse)
					.collect(Collectors.toList());

			if(listAcdemic.isEmpty())
			{
				listStructure.setStatus(HttpStatus.NO_CONTENT.value());
				listStructure.setMessage("no programs has been found");
				listStructure.setData(listofacdemicstream);

				return new  ResponseEntity<ResponseStrcture<List<AcademicProgramResponse>>>(listStructure,HttpStatus.NO_CONTENT);
			}
			else {
				listStructure.setStatus(HttpStatus.FOUND.value());
				listStructure.setMessage("found list of academic programs");
				listStructure.setData(listofacdemicstream);

				return new  ResponseEntity<ResponseStrcture<List<AcademicProgramResponse>>>(listStructure,HttpStatus.FOUND);
			}
		}).orElseThrow(()->new RuntimeException());
	}
	
	
	public AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram) {

		List<String> subjects = new ArrayList<String>();
		
		List<Subject> listOfSubject = academicProgram.getListOfSubject();

		if(listOfSubject != null) {
			listOfSubject.forEach(sub -> {
				subjects.add(sub.getSubjectName());
			});
		}

		return AcademicProgramResponse.builder()
				.programid(academicProgram.getProgramid())
				.programType(academicProgram.getProgramType())
				.programName(academicProgram.getProgramName())
				.beginTime(academicProgram.getBeginTime())
				.endTime(academicProgram.getEndTime())
				.listOfSubjects(subjects)
				.build();
	}
	
	private AcademicProgram mapToAcademicProgram(AcademicProgramRequest acdemic)
	{
		return AcademicProgram.builder()
				
				.programType(acdemic.getProgramType())
				.programName(acdemic.getProgramName())
				.beginTime(acdemic.getBeginTime())
				.endTime(acdemic.getEndTime())
				.build();
	}


}
