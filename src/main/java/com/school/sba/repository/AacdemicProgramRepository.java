package com.school.sba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.school.sba.entity.AcademicProgram;

public interface AacdemicProgramRepository extends JpaRepository<AcademicProgram, Integer>{

public List<AcademicProgram> findAllByIsDelete(boolean b);



}
