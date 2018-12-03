package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.CourseType;
import com.gz.ik.enums.CourseTypeStateEnum;

public class CourseTypeExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private CourseType courseType;

	private List<CourseType> courseTypeList;
	
	

	public CourseTypeExecution() {
		super();
	}

	public CourseTypeExecution(CourseTypeStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public CourseTypeExecution(CourseTypeStateEnum stateEnum, CourseType courseType) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseType = courseType;
	}
	



	public CourseTypeExecution(CourseTypeStateEnum stateEnum, List<CourseType> courseTypeList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseTypeList = courseTypeList;
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

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public List<CourseType> getCourseTypeList() {
		return courseTypeList;
	}

	public void setCourseTypeList(List<CourseType> courseTypeList) {
		this.courseTypeList = courseTypeList;
	}

	
	
	

}
