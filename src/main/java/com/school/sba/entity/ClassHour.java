package com.school.sba.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;


import com.school.sba.enums.ClassStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassHour {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int classHourId;
private LocalDateTime beginsAt;
private LocalDateTime endAt;
private int roomNo;
private ClassStatus classStatus;
private DayOfWeek day;
private LocalDate date;

@ManyToOne
private AcademicProgram academicprogram;

@ManyToOne
private Subject subject;

@ManyToOne
private User user;

}
