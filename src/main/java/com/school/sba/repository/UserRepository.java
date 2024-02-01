package com.school.sba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Integer>{

public boolean existsByUserRole(UserRole userRole);

public Optional<User> findByUserName(String username);

public List<User> findAllByUserRole(UserRole userRole);

public List<User> findAllByIsDelete(boolean b);

public List<User> findBySchool(School school2);


}
