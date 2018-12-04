package com.gz.ik.service;



import com.gz.ik.dto.CourseExecution;
import com.gz.ik.dto.CourseTypeExecution;
import com.gz.ik.entity.CourseType;


public interface CourseTypeService {
	
	CourseTypeExecution getCourseTypeList() throws RuntimeException;
	
	CourseTypeExecution getCourseType(CourseType courseType) throws RuntimeException;
	
	CourseTypeExecution insertCourseType(CourseType courseType) throws RuntimeException;
	
	CourseTypeExecution updateCourseType(CourseType courseType) throws RuntimeException;
	
	CourseTypeExecution deleteCourseType(CourseType courseType) throws RuntimeException;
	
	CourseTypeExecution showCourseTypeList(int pageIndex, int pageSize) throws RuntimeException;

}
