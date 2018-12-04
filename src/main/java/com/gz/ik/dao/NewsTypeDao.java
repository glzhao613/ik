package com.gz.ik.dao;

import java.util.List;
import java.util.Map;

import com.gz.ik.entity.News;
import com.gz.ik.entity.NewsType;

public interface NewsTypeDao {
	//查询资讯类型
	NewsType querNewsType(NewsType newsType);
	
	//根据指定参数分页查询
	List<NewsType> querNewsTypePaging(Map<String,Object> pageMap);
	
	//查询所有资讯类型
	List<NewsType> querAllNewsType();
	
	//更新资讯类型
	int updateNewsType(NewsType newsType);
	
	//插入一条资讯类型
	int insertNewsType(NewsType newsType);
	
	//删除一条资讯类型
	//根据资讯类型删除资讯
	boolean deleteNewsType(NewsType newsType);
	boolean deleteNewsByNewsType(NewsType newsType);
	List<News> querNewsByNewsType(NewsType newsType);
}
