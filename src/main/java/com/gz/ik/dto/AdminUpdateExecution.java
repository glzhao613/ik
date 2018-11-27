package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Admin;
import com.gz.ik.enums.AdminUpdateStateEnum;

public class AdminUpdateExecution {
	
	//结果状态
	private int state;
	
	//状态标识
	private String stateInfo;
	
	private int count;
	
	private Admin admin;
	
	private List<Admin> adminlist;
	
	public AdminUpdateExecution() {
		super();
	}
	
	//管理员更新操作失败的构造器
	public AdminUpdateExecution(AdminUpdateStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//管理员更新操作成功的构造器
	public AdminUpdateExecution(AdminUpdateStateEnum stateEnum,Admin admin) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.admin = admin;
	}
	
	//管理员更新操作成功的构造器
	public AdminUpdateExecution(AdminUpdateStateEnum stateEnum,List<Admin> adminlist) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.adminlist = adminlist;
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getAdminlist() {
		return adminlist;
	}

	public void setAdminlist(List<Admin> adminlist) {
		this.adminlist = adminlist;
	}
	
	
}
