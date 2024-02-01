package com.school.sba.entity;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int schoolId;
private String schoolName;
private long contactNo;
private String email;
private String address;
private DayOfWeek day;
private boolean isDelete;


@OneToOne
private Schedule schedule;

@OneToMany(mappedBy = "school")
private List<AcademicProgram> listOfAcademicPrograms;

}
