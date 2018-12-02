package com.gz.ik.entity;


public class Course {
	
	private Integer courseId;
	private String courseName;
	private String courseDes;
	private Float coursePrice;
	private String courseImg;
	private Integer courseHour;
	
	private Teacher courseTeacher;
	private CourseType courseType;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDes() {
		return courseDes;
	}
	public void setCourseDes(String courseDes) {
		this.courseDes = courseDes;
	}
	public Float getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Float coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}
	public Integer getCourseHour() {
		return courseHour;
	}
	public void setCourseHour(Integer courseHour) {
		this.courseHour = courseHour;
	}
	public Teacher getCourseTeacher() {
		return courseTeacher;
	}
	public void setCourseTeacher(Teacher courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	
	
	
	
	

}
