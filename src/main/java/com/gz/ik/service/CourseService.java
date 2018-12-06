package com.gz.ik.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.CourseDeleteExecution;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseInsertExecution;
import com.gz.ik.entity.Comments;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.Files;
import com.gz.ik.entity.UC;


public interface CourseService {
	
	CourseExecution getCourseList() throws RuntimeException;
	
	CourseExecution getCourse(Course course) throws RuntimeException;
	
	CourseInsertExecution insertCourse(Course course,CommonsMultipartFile img) throws RuntimeException;
	
	CourseDeleteExecution deleteCourse(Course course,Comments comments,UC uc,Files files) throws RuntimeException;
	
	CourseExecution updateCourse(Course course,CommonsMultipartFile img) throws RuntimeException;
	
	CourseExecution showCourseList(Course course,int pageIndex, int pageSize) throws RuntimeException;
	
	CourseExecution showCourse(Course course,int pageIndex, int pageSize) throws RuntimeException;
	
	CourseExecution querCourseList(Course course) throws RuntimeException;
	
	CourseExecution frontCourseList(Course course) throws RuntimeException;

}
