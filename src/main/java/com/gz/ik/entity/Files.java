package com.gz.ik.entity;

public class Files {
	
	private Integer fileId;
	private String fileName;
	private String filePath;
	
	private Course fileCourse;
	private FileType fileType;
	
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Course getFileCourse() {
		return fileCourse;
	}
	public void setFileCourse(Course fileCourse) {
		this.fileCourse = fileCourse;
	}
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	
	

}
