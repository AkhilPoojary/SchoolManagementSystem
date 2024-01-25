package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.entity.Schedule;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.utility.ResponseStrcture;
@RestController
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleservice;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/schools/{schoolId}/schedules")
	public ResponseEntity<ResponseStrcture<ScheduleResponse>> addSchedule(@PathVariable int schoolId,@RequestBody ScheduleRequest schedule)
	{
		return scheduleservice.addSchedule(schoolId,schedule);
	}
	@GetMapping("/schools/{schoolId}/schedules")
	public ResponseEntity<ResponseStrcture<ScheduleResponse>> findSchedule(@PathVariable int schoolId)
	{
		return scheduleservice.findSchedule(schoolId);
	}
	
	// validation should be performed on the requests
	@PutMapping("/schedules/{scheduleId}")
	public ResponseEntity<ResponseStrcture<ScheduleResponse>> updateSchedule(@PathVariable int scheduleId,@RequestBody ScheduleRequest updateSchedule )
	{
		return scheduleservice.updateupdateSchedule(scheduleId,updateSchedule);
	}
	
}
