package com.gz.ik.dao;

import java.util.List;

import com.gz.ik.entity.Course;

public interface CourseDao {
	
	List<Course> queryCourse(Course course);

}
