package com.gz.ik.dao;

import java.util.List;

import com.gz.ik.entity.CourseType;

public interface CourseTypeDao {
	
	List<CourseType> queryCourseTypelist(CourseType courseType);
	
	CourseType queryCourseType(String courseTypeName);
	
	int insertCourseType(CourseType courseType);
	
	int updateCourseType(CourseType courseType);
	
	boolean deleteCourseType(int courseTypeId);
	boolean deleteCourseTypeC(int courseTypeId);

}
