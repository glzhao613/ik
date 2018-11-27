package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.News;
import com.gz.ik.enums.NewsDeleteStateEnum;

public class NewsDeleteExecution {
    //结果状态
	private int state;	
	//状态标识
	private String stateInfo;		
	private int count;
	private News news;
	private List<News> newsList;
	
	public NewsDeleteExecution() {
		super();
	}
	
	//资讯删除失败的构造器
	public NewsDeleteExecution(NewsDeleteStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//资讯删除成功的构造器
	public NewsDeleteExecution(NewsDeleteStateEnum stateEnum,News news) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.news = news;
	}
	
	//资讯删除成功的构造器
	public NewsDeleteExecution(NewsDeleteStateEnum stateEnum,List<News> newsList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.newsList = newsList;
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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
}
