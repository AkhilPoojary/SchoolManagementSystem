package com.school.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sba.entity.Schedule;

public interface ScheduleRepositiory extends JpaRepository<Schedule, Integer> {

}
