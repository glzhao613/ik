package com.gz.ik.entity;

public class Admin {
	
	private Integer adminId;
	private String adminAccount;
	private String adminPwd;
	//1:超级管理员，0：管理员
	private Integer adminType;
	private String adminName;
	private Module adminModule;
	
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public Integer getAdminType() {
		return adminType;
	}
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Module getAdminModule() {
		return adminModule;
	}
	public void setAdminModule(Module adminModule) {
		this.adminModule = adminModule;
	}
	
	
	 
	
	

}
