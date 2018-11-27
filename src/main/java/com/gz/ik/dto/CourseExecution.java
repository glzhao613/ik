package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Course;
import com.gz.ik.enums.CourseStateEnum;

public class CourseExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private Course course;

	private List<Course> courseList;
	
	

	public CourseExecution() {
		super();
	}

	public CourseExecution(CourseStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public CourseExecution(CourseStateEnum stateEnum, Course course) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.course = course;
	}
	



	public CourseExecution(CourseStateEnum stateEnum, List<Course> courseList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseList = courseList;
	}



	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	

}
