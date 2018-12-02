package com.gz.ik.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.CourseDeleteExecution;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseInsertExecution;
import com.gz.ik.entity.Course;


public interface CourseService {
	
	CourseExecution getCourseList() throws RuntimeException;
	
	CourseExecution getCourse(Course course) throws RuntimeException;
	
	CourseInsertExecution insertCourse(Course course,CommonsMultipartFile img) throws RuntimeException;
	
	CourseDeleteExecution deleteCourse(Course course) throws RuntimeException;
	
	CourseExecution updateCourse(Course course,CommonsMultipartFile img) throws RuntimeException;

}
