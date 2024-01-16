package com.school.sba.utility;

import java.util.List;

import com.school.sba.entity.School;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseStrcture<T> {
private int status ;
private String message;
private T data;
}
