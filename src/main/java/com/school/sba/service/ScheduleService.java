package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.Schedule;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.utility.ResponseStrcture;

public interface ScheduleService {

ResponseEntity<ResponseStrcture<ScheduleResponse>> addSchedule(int schoolId, ScheduleRequest schedule);

ResponseEntity<ResponseStrcture<ScheduleResponse>> findSchedule(int schoolId);

ResponseEntity<ResponseStrcture<ScheduleResponse>> updateupdateSchedule(int scheduleId, ScheduleRequest schedule);

}
