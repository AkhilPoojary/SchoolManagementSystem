package com.school.sba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Thushr {
	
	@Id
	private int id;
	private String name;

}
