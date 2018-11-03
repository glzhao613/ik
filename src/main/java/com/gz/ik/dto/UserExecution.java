package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.User;
import com.gz.ik.enums.UserStateEnum;

public class UserExecution {
	
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private User user;

	private List<User> userList;
	
	

	public UserExecution() {
		super();
	}

	public UserExecution(UserStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public UserExecution(UserStateEnum stateEnum, User user) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}
	



	public UserExecution(UserStateEnum stateEnum, List<User> userList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.userList = userList;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	

}
