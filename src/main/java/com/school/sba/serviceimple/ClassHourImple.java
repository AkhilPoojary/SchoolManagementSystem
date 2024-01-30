package com.school.sba.serviceimple;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.ClassHour;
import com.school.sba.entity.Schedule;
import com.school.sba.entity.Subject;
import com.school.sba.entity.User;
import com.school.sba.enums.ClassStatus;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.AacdemicProgramRepository;
import com.school.sba.repository.ClassHourRepository;
import com.school.sba.repository.SubjectRepo;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.ClassHourRequest;
import com.school.sba.responsedto.ClassHourResponse;
import com.school.sba.service.ClassHourService;
import com.school.sba.utility.ResponseStrcture;


@Service
public class ClassHourImple implements ClassHourService {

	@Autowired
	private ClassHourRepository classhourrepo;

	@Autowired
	private AacdemicProgramRepository acdemicrepo;

	@Autowired
	private ResponseStrcture<String> response;
	
	@Autowired
	private ResponseStrcture<List<ClassHourResponse>> listresp;


	@Autowired
	private UserRepository usrerepo;


	@Autowired
	private SubjectRepo subjectrepo;

	@Autowired
	private ResponseStrcture<ClassHourResponse> resp;
	
	public List<ClassHourResponse> mapToResponse(List<ClassHour> listofclasshour)
	{
 		List<ClassHourResponse> responselist=new ArrayList<ClassHourResponse>();
 		listofclasshour.forEach(classhour->{
 			responselist.add(ClassHourResponse.builder()
 			.beginsAt(classhour.getBeginsAt().toLocalTime())
			.endAt(classhour.getEndAt().toLocalTime())
			.classHourId(classhour.getClassHourId())
			.roomNo(classhour.getRoomNo())
			.classStatus(classhour.getClassStatus())
			.day(classhour.getDay())
			.date(classhour.getDate())
			.build());
 		});
 		
 		return responselist;
			
	}


	private boolean isBreakTime(LocalDateTime currentTime, Schedule schedule) {
		LocalTime breakTimeStart = schedule.getBreakTime();
		LocalTime breakTimeEnd = breakTimeStart.plusMinutes(schedule.getBreakLeangthInMin().toMinutes());

		return (currentTime.toLocalTime().isAfter(breakTimeStart) && currentTime.toLocalTime().isBefore(breakTimeEnd));

	}

	private boolean isLunchTime(LocalDateTime currentTime, Schedule schedule) {
		LocalTime LunchTimeStart = schedule.getLunchTime();
		LocalTime LunchTimeEnd = LunchTimeStart.plusMinutes(schedule.getLunchLengthInMin().toMinutes());

		return (currentTime.toLocalTime().isAfter(LunchTimeStart) && currentTime.toLocalTime().isBefore(LunchTimeEnd));

	}

	@Override
	public	ResponseEntity<ResponseStrcture<String>> generateClassHourForAcademics(int programId) {

		return acdemicrepo.findById(programId)
				.map(academicProgram -> {

					Schedule schedule = academicProgram.getSchool().getSchedule();

					if(schedule != null) {

						int classHoursPerDay = schedule.getClassHoursPerDay();
						int classHourLengthInMinutes = (int)schedule.getClassHourLengthInMin().toMinutes();

						LocalDateTime currentTime = LocalDateTime.now().with(schedule.getOpensAt());
						System.out.println(currentTime);

						LocalTime breakTimeStart = schedule.getBreakTime();
						System.out.println(breakTimeStart);
						LocalTime breakTimeEnd = breakTimeStart.plusMinutes(schedule.getBreakLeangthInMin().toMinutes());
						System.out.println(breakTimeEnd);

						LocalTime lunchTimeStart = schedule.getLunchTime();
						System.out.println(lunchTimeStart);

						LocalTime lunchTimeEnd = lunchTimeStart.plusMinutes(schedule.getLunchLengthInMin().toMinutes());
						System.out.println(lunchTimeEnd);

						for(int day = 1; day <= 6; day++) {

							for(int hour = 0; hour<classHoursPerDay+2;hour++) {

								ClassHour classHour = new ClassHour();

								if(!currentTime.toLocalTime().equals(lunchTimeStart) && !isLunchTime(currentTime, schedule)) {

									if(!currentTime.toLocalTime().equals(breakTimeStart) && !isBreakTime(currentTime, schedule))
									{
										LocalDateTime beginsAt = currentTime;
										LocalDateTime endsAt = beginsAt.plusMinutes(classHourLengthInMinutes);
										System.out.println("inside if start time "+ beginsAt);
										System.out.println("inside if ends time "+ endsAt);

										classHour.setBeginsAt(beginsAt);
										classHour.setEndAt(endsAt);
										classHour.setClassStatus(ClassStatus.NOT_SCHEDULED);

										currentTime = endsAt;
									}
									else
									{
										System.out.println("inside else");
										classHour.setBeginsAt(currentTime);
										classHour.setEndAt(LocalDateTime.now().with(breakTimeEnd));

										classHour.setClassStatus(ClassStatus.BREAK_TIME);
										currentTime = currentTime.plusMinutes(schedule.getBreakLeangthInMin().toMinutes());
									}

								}
								else {
									classHour.setBeginsAt(currentTime);
									classHour.setEndAt(LocalDateTime.now().with(lunchTimeEnd));
									classHour.setClassStatus(ClassStatus.LUNCH_TIME);
									currentTime = currentTime.plusMinutes(schedule.getLunchLengthInMin().toMinutes());
								}
								classHour.setAcademicprogram(academicProgram);
								classhourrepo.save(classHour);
							}
							currentTime = currentTime.plusDays(1).with(schedule.getOpensAt());
						}
					}
					else {
						throw new RuntimeException();
					}
					response.setStatus(HttpStatus.CREATED.value());
					response.setMessage("ClassHour generated successfully for the academic progarm");
					response.setData("Class Hour generated for the current week successfully");

					return new  ResponseEntity<ResponseStrcture<String>> (response, HttpStatus.CREATED);
				})
				.orElseThrow(() -> new RuntimeException());
	}

	@Override
	public ResponseEntity<ResponseStrcture<List<ClassHourResponse>>> updateClassHour(List<ClassHourRequest> classHourRequest) 
	{
		List<ClassHour> listOfClassHours = new ArrayList<>();
		for (ClassHourRequest classH : classHourRequest) {
			
			User user = usrerepo.findById(classH.getTeacherId())
					.orElseThrow(() -> new RuntimeException());
			
			Subject subject = subjectrepo.findById(classH.getSubjectId())
					.orElseThrow(() -> new RuntimeException());

			ClassHour classHour = classhourrepo.findById(classH.getClassHourId())
					.orElseThrow(() -> new RuntimeException());

			if (user.getUserRole().equals(UserRole.TEACHER) && user.getSubject().equals(subject) && user.getListOfAcademicPrograms().contains(classHour.getAcademicprogram())) {

				if (classhourrepo.existsByBeginsAtAndRoomNo(classHour.getBeginsAt(), classHour.getRoomNo()) == false) {

					LocalDateTime currentTime = LocalDateTime.now();
					if (currentTime.isAfter(classHour.getBeginsAt()) && currentTime.isBefore(classHour.getEndAt())) {
						classHour.setUser(user);
						classHour.setClassStatus(ClassStatus.ONGOING);
						classHour.setRoomNo(classH.getRoomNum());
						classHour.setSubject(subject);
						listOfClassHours.add(classHour);
					}

					else if (currentTime.isAfter(classHour.getEndAt())) {
						classHour.setUser(user);
						classHour.setClassStatus(ClassStatus.FINISHED);
						classHour.setRoomNo(classH.getRoomNum());
						classHour.setSubject(subject);
						listOfClassHours.add(classHour);
					}

					else {

						classHour.setUser(user);
						classHour.setClassStatus(ClassStatus.UPCOMING);
						classHour.setRoomNo(classH.getRoomNum());
						classHour.setSubject(subject);
						listOfClassHours.add(classHour);
					}
				} else
					throw new RuntimeException();

			} else
				throw new RuntimeException();
		}

		classhourrepo.saveAll(listOfClassHours);

		listresp.setStatus(HttpStatus.FOUND.value());
		listresp.setMessage("list of classHours found");
		listresp.setData(mapToResponse(listOfClassHours));

		return new ResponseEntity<ResponseStrcture<List<ClassHourResponse>>>(listresp, HttpStatus.FOUND);

	}
}

