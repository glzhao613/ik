package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.CompanyImg;
import com.gz.ik.enums.CompanyImgStateEnum;

public class CompanyImgExecution {
	 //结果状态
	private int state;	
	//状态标识
	private String stateInfo;		
	private int count;
	private CompanyImg companyimg;
	private List<CompanyImg> companyimgList;
	
	//操作失败的构造器
	public CompanyImgExecution(CompanyImgStateEnum stateEnum){
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//操作成功的构造器
	public CompanyImgExecution(CompanyImgStateEnum stateEnum,CompanyImg companyimg){
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.companyimg = companyimg;
	}
	
	//操作成功的构造器
	public CompanyImgExecution(CompanyImgStateEnum stateEnum,List<CompanyImg> companyimgList){
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.companyimgList = companyimgList;
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

	public CompanyImg getCompanyimg() {
		return companyimg;
	}

	public void setCompanyimg(CompanyImg companyimg) {
		this.companyimg = companyimg;
	}

	public List<CompanyImg> getCompanyimgList() {
		return companyimgList;
	}

	public void setCompanyimgList(List<CompanyImg> companyimgList) {
		this.companyimgList = companyimgList;
	}
	
	
}
