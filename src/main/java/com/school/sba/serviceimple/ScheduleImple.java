package com.school.sba.serviceimple;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.repository.ScheduleRepositiory;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.utility.ResponseStrcture;

@Service
public class ScheduleImple implements ScheduleService {


	@Autowired
	private ScheduleRepositiory schedulerepo;

	@Autowired
	private SchoolRepository schoolrepo;

	@Override
	public ResponseEntity<ResponseStrcture<ScheduleResponse>> addSchedule(int schoolId, ScheduleRequest schedulerequest) 
	{
		return schoolrepo.findById(schoolId).map(school->{
			if(school.getSchedule()==null)	
			{
				Schedule schedule = mapToSchedule(schedulerequest);
				schedule  = schedulerepo.save(schedule);
				school.setSchedule(schedule);
				ResponseStrcture<ScheduleResponse> resp=new ResponseStrcture<ScheduleResponse>();
				resp.setStatus(HttpStatus.CREATED.value());
				resp.setMessage("schedule inserted");
				resp.setData(mapToResponse(schedule));
				return new ResponseEntity<ResponseStrcture<ScheduleResponse>>(resp,HttpStatus.CREATED);
			}
			else
				throw new RuntimeException();
		}).orElseThrow(()->new RuntimeException());
	}

	@Override
	public ResponseEntity<ResponseStrcture<ScheduleResponse>> findSchedule(int schoolId) {
		return schedulerepo.findById(schoolId).
				map(s->{

					ResponseStrcture<ScheduleResponse> resp=new ResponseStrcture<ScheduleResponse>();
					resp.setStatus(HttpStatus.FOUND.value());
					resp.setMessage("schedule found sucessfully");
					resp.setData(mapToResponse(s));
					return new ResponseEntity<ResponseStrcture<ScheduleResponse>>(resp,HttpStatus.FOUND);

				}).orElseThrow(()-> new RuntimeException());
	}




	private ScheduleResponse mapToResponse(Schedule schedule1) {
		return ScheduleResponse.builder()
				.opensAt(schedule1.getOpensAt())
				.closesAt(schedule1.getClosesAt())
				.classHourLengthInMin((int)(Duration.ofMinutes(schedule1.getBreakLeangthInMin().toMinutes()).toMinutes()))
				.breakLeangthInMin((int)(Duration.ofMinutes(schedule1.getBreakLeangthInMin().toMinutes()).toMinutes()))
				.breakTime(schedule1.getBreakTime())
				.lunchLengthInMin((int)(Duration.ofMinutes(schedule1.getLunchLengthInMin().toMinutes()).toMinutes()))
				.lunchTime(schedule1.getLunchTime())
				.build();
	}


	private Schedule mapToSchedule(ScheduleRequest schedule) {
		return Schedule.builder()

				.opensAt(schedule.getOpensAt())
				.closesAt(schedule.getClosesAt())
				.classHourLengthInMin(Duration.ofMinutes(schedule.getClassHourLengthInMin()))
				.classHoursPerDay(schedule.getClassHoursPerDay())
				.breakTime(schedule.getBreakTime())
				.breakLeangthInMin(Duration.ofMinutes(schedule.getBreakLeangthInMin()))
				.lunchLengthInMin(Duration.ofMinutes(schedule.getLunchLengthInMin()))
				.lunchTime(schedule.getLunchTime())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStrcture<ScheduleResponse>> updateupdateSchedule(int scheduleId,ScheduleRequest schedule) 
	{
		return schedulerepo.findById(scheduleId)
				.map(sched -> {
					Schedule schedule1 = mapToSchedule(schedule);
					schedule1.setScheduleId(scheduleId);
					sched = schedulerepo.save(schedule1);

	
			ResponseStrcture<ScheduleResponse> response=new ResponseStrcture<ScheduleResponse>();
			response.setStatus(HttpStatus.FOUND.value());
			response.setMessage("The School Object Upadted");
			response.setData(mapToResponse(sched));


			return new ResponseEntity<ResponseStrcture<ScheduleResponse>>(response,HttpStatus.OK);
		}).orElseThrow(()->new RuntimeException());
		
	}








}
