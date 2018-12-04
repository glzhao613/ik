package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Course;
import com.gz.ik.entity.CourseType;

public interface CourseTypeDao {
	
	List<CourseType> queryCourseTypelist(CourseType courseType);
	
	CourseType queryCourseType(String courseTypeName);
	
	List<CourseType> queryCourseTypeList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryCourseTypeCount();
	
	int insertCourseType(CourseType courseType);
	
	int updateCourseType(CourseType courseType);
	
	boolean deleteCourseType(int courseTypeId);
	boolean deleteCourseTypeC(int courseTypeId);

}
