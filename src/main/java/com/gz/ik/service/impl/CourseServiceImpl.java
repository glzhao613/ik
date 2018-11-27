package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gz.ik.dao.CourseDao;
import com.gz.ik.dto.CourseExecution;
import com.gz.ik.entity.Course;
import com.gz.ik.enums.CourseStateEnum;
import com.gz.ik.service.CourseService;

public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Override
	public CourseExecution getCourseList() throws RuntimeException {
		List<Course> courseList=courseDao.queryCourse(new Course());
		if(courseList==null||courseList.size()<=0) {
			return new CourseExecution(CourseStateEnum.QUERY_NULL);
		}else {
			return new CourseExecution(CourseStateEnum.QUERY_SECCESS,courseList);		
		}
	}


}
