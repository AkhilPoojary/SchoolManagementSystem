package com.school.sba.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sba.entity.ClassHour;
import com.school.sba.entity.User;



public interface ClassHourRepository extends JpaRepository<ClassHour, Integer>  {



public	boolean existsByBeginsAtAndRoomNo(LocalDateTime beginsAt, int roomNo);

public ClassHour findByUser(User user2);




}
