package com.gz.ik.service;

import com.gz.ik.dto.CourseExecution;

public interface CourseService {
	
	CourseExecution getCourseList() throws RuntimeException;

}
