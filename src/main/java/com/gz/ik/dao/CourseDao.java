package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Course;

public interface CourseDao {
	
	List<Course> queryCourse(Course course);
	
	Course queryCourseId(int courseId);
	
	Course queryCourseName(String courseName);
	
	List<Course> frontCourseList(@Param("course") Course course,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	List<Course> queryCourseList(@Param("course") Course course,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	List<Course> getCourseList(@Param("course") Course course,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryCourseCount();
	
	int insertCourse(Course course);
	
	boolean deleteCourse(int courseId);
	
	int updateCourse(Course course);

}
