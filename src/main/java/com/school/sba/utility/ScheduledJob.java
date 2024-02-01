package com.school.sba.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.sba.serviceimple.AacdemicProgramImple;
import com.school.sba.serviceimple.SchoolServiceImple;

@Component
public class ScheduledJob {
	
	@Autowired 
	private SchoolServiceImple schoolService;
	
	@Autowired
	private AacdemicProgramImple acdemicSerive;

	@Scheduled(fixedDelay = 1000l)
	public void Test()
	{
		schoolService.hardDelete();
		acdemicSerive.deleted();
	}
}
