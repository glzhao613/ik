package com.gz.ik.entity;

import java.util.Date;

public class Comments {
	
	private Integer commentId;
	private String commentArticle;
	private Date commentDate;
	
	private User commentUser;
	private Course commentCourse;
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getCommentArticle() {
		return commentArticle;
	}
	public void setCommentArticle(String commentArticle) {
		this.commentArticle = commentArticle;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public User getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}
	public Course getCommentCourse() {
		return commentCourse;
	}
	public void setCommentCourse(Course commentCourse) {
		this.commentCourse = commentCourse;
	}
	
	

}
