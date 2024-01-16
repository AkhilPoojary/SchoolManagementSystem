package com.school.sba.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.school.sba.exception.TeacherNotFoundById;

@RestControllerAdvice
public class ApplicationHandler {
	public ResponseEntity<ResponseStrcture<String>> schoolNotFoundById(TeacherNotFoundById exception)
	{
		ResponseStrcture<String> response=new ResponseStrcture<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("school object with the given id is not prasent!!!");
		return new ResponseEntity<ResponseStrcture<String>>(response,HttpStatus.NOT_FOUND);
	}

}
