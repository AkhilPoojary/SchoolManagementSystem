package com.school.sba.exception;

public class TeacherNotFoundById extends RuntimeException {
	
	private String message;

	public TeacherNotFoundById(String message) {
		super();
		this.message = message;
	}
	

}
