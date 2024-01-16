package com.school.sba.entity;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Component
@Entity
@Getter
@Setter
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private LocalTime classHourLength;
	private LocalTime breakTime;
	private LocalTime breakLeangth;
	private LocalTime lunchTime;
	private LocalTime lunchLength;
	@OneToOne
	private School shol;
}
