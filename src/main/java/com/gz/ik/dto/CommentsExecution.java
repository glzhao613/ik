package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Comments;
import com.gz.ik.enums.CommentsStateEnum;

public class CommentsExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;
	
	private Comments entity;
	
	private List<Comments> entityList;
	
	

	public CommentsExecution() {
		super();
	}

	public CommentsExecution(CommentsStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public CommentsExecution(CommentsStateEnum stateEnum, Comments entity) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.entity = entity;
	}
	



	public CommentsExecution(CommentsStateEnum stateEnum, List<Comments> entityList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.entityList = entityList;
	}



	public Comments getEntity() {
		return entity;
	}

	public void setEntity(Comments entity) {
		this.entity = entity;
	}

	public List<Comments> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Comments> entityList) {
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
