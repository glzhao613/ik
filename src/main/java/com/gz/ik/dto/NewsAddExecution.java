package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.News;
import com.gz.ik.enums.NewsAddStateEnum;

public class NewsAddExecution {
			//结果状态
			private int state;	
			//状态标识
			private String stateInfo;		
			private int count;
			private News news;
			private List<News> newsList;
			
			public NewsAddExecution() {
				super();
			}
			
			//资讯添加失败的构造器
			public NewsAddExecution(NewsAddStateEnum stateEnum) {
				super();
				this.state = stateEnum.getState();
				this.stateInfo = stateEnum.getStateInfo();
			}
			
			//资讯添加成功的构造器
			public NewsAddExecution(NewsAddStateEnum stateEnum,News news) {
				super();
				this.state = stateEnum.getState();
				this.stateInfo = stateEnum.getStateInfo();
				this.news = news;
			}
			
			//资讯添加成功的构造器
			public NewsAddExecution(NewsAddStateEnum stateEnum,List<News> newsList) {
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
