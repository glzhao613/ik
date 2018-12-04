package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.NewsType;
import com.gz.ik.enums.NewsTypeStateEnum;

public class NewsTypeExecution {
	//结果状态
	private int state;	
	//状态标识
	private String stateInfo;		
	private int count;
	private NewsType newsType;
	private List<NewsType> newsTypeList;
	
	//操作失败的构造器
	public  NewsTypeExecution(NewsTypeStateEnum stateEnum){
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//操作成功的构造器
	public  NewsTypeExecution(NewsTypeStateEnum stateEnum,NewsType newsType){
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.newsType = newsType;
	}
	
	//操作成功的构造器
	public  NewsTypeExecution(NewsTypeStateEnum stateEnum,List<NewsType> newsTypeList){
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.newsTypeList = newsTypeList;
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

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public List<NewsType> getNewsTypeList() {
		return newsTypeList;
	}

	public void setNewsTypeList(List<NewsType> newsTypeList) {
		this.newsTypeList = newsTypeList;
	}
}
