package com.school.sba.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Component
@Entity
@Getter
@Setter
@Builder
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int schoolId;
private String schoolName;
private long contactNo;
private String email;
private String address;
@OneToOne(mappedBy = "shol")
private Schedule schd;

}
