package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.FileType;
import com.gz.ik.enums.FileTypeStateEnum;

public class FileTypeExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;
	
	private FileType entity;
	
	private List<FileType> entityList;
	
	

	public FileTypeExecution() {
		super();
	}

	public FileTypeExecution(FileTypeStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public FileTypeExecution(FileTypeStateEnum stateEnum, FileType entity) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.entity = entity;
	}
	



	public FileTypeExecution(FileTypeStateEnum stateEnum, List<FileType> entityList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.entityList = entityList;
	}



	public FileType getEntity() {
		return entity;
	}

	public void setEntity(FileType entity) {
		this.entity = entity;
	}

	public List<FileType> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<FileType> entityList) {
		this.entityList = entityList;
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

	

}
