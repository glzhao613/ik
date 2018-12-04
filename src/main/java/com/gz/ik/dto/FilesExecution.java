package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Files;
import com.gz.ik.enums.FilesStateEnum;

public class FilesExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;
	
	private Files entity;
	
	private List<Files> entityList;
	
	

	public FilesExecution() {
		super();
	}

	public FilesExecution(FilesStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public FilesExecution(FilesStateEnum stateEnum, Files entity) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.entity = entity;
	}
	



	public FilesExecution(FilesStateEnum stateEnum, List<Files> entityList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.entityList = entityList;
	}



	
	public Files getEntity() {
		return entity;
	}

	public void setEntity(Files entity) {
		this.entity = entity;
	}

	public List<Files> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Files> entityList) {
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
