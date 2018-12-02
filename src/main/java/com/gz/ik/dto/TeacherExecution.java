package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Teacher;
import com.gz.ik.enums.TeacherStateEnum;

public class TeacherExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private Teacher teacher;

	private List<Teacher> teacherList;
	
	

	public TeacherExecution() {
		super();
	}

	public TeacherExecution(TeacherStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public TeacherExecution(TeacherStateEnum stateEnum, Teacher teacher) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.teacher = teacher;
	}
	



	public TeacherExecution(TeacherStateEnum stateEnum, List<Teacher> teacherList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.teacherList = teacherList;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	
	
	
	

}
