package com.school.sba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sba.entity.School;


public interface SchoolRepository extends JpaRepository<School, Integer> {

	

public List<School> finAllByIsDelete(boolean b);

}

