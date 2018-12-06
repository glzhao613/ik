package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Company;
import com.gz.ik.enums.CompanyStateEnum;

public class CompanyExecution {
	    //结果状态
		private int state;	
		//状态标识
		private String stateInfo;		
		private int count;
		private Company company;
		private List<Company> companyList;
		
		//操作失败的构造器
		public CompanyExecution(CompanyStateEnum stateEnum){
			super();
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
		}
		
		//操作成功的构造器
		public CompanyExecution(CompanyStateEnum stateEnum,Company company){
			super();
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.company = company;
		}
		
		//操作成功的构造器
		public CompanyExecution(CompanyStateEnum stateEnum,List<Company> companyList){
			super();
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.companyList = companyList;
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

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		public List<Company> getCompanyList() {
			return companyList;
		}

		public void setCompanyList(List<Company> companyList) {
			this.companyList = companyList;
		}
		
		
}
