package com.gz.ik.entity;

import java.util.Date;

public class News {
	private Integer newsId;
	private String newsTitle;
	private String newsArticle;
	private Date newsDate;
	private String newsImg;
	
	private CourseType courseType;
	private Teacher courseTeacher;
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsArticle() {
		return newsArticle;
	}
	public void setNewsArticle(String newsArticle) {
		this.newsArticle = newsArticle;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsImg() {
		return newsImg;
	}
	public void setNewsImg(String newsImg) {
		this.newsImg = newsImg;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public Teacher getCourseTeacher() {
		return courseTeacher;
	}
	public void setCourseTeacher(Teacher courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	
	

}
