package com.gz.ik.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.NewsDao;
import com.gz.ik.dto.NewsAddExecution;
import com.gz.ik.dto.NewsDeleteExecution;
import com.gz.ik.dto.NewsQuerExecution;
import com.gz.ik.dto.NewsUpdateExecution;
import com.gz.ik.entity.News;
import com.gz.ik.enums.NewsAddStateEnum;
import com.gz.ik.enums.NewsDeleteStateEnum;
import com.gz.ik.enums.NewsQuerStateEnum;
import com.gz.ik.enums.NewsUpdateStateEnum;
import com.gz.ik.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;


	@Override
	public NewsAddExecution AddCheck(News news) throws RuntimeException {
		News t_news = null;
		if(news == null){
			return new NewsAddExecution(NewsAddStateEnum.NULL_INPUT);
		}
		else{
			t_news = newsDao.queryOneNews(news.getNewsTitle());
			if(t_news != null){
				return new NewsAddExecution(NewsAddStateEnum.ID_ERROR);
			}
			else{
				int count;
				count = newsDao.insertNews(news);
				if(count > 0){
					return new NewsAddExecution(NewsAddStateEnum.ADD_SUCCESS);
				}
				else{
					return new NewsAddExecution(NewsAddStateEnum.ADD_FAILED);
				}
			}
		}
	}

	@Override
	public NewsDeleteExecution DeleteCheck(News news) throws RuntimeException {
		if(news == null){
			return new NewsDeleteExecution(NewsDeleteStateEnum.NULL_INPUT);
		}
		else{
			News t_news = newsDao.queryOneNews(news.getNewsTitle());
			if(t_news == null){
				return new NewsDeleteExecution(NewsDeleteStateEnum.ERROR_NOTEXIST);
			}
			else{
				boolean index = newsDao.deleteNews(news);
				if(index){
					return new NewsDeleteExecution(NewsDeleteStateEnum.DELETE_SUCCESS);
				}
				else{
					return new NewsDeleteExecution(NewsDeleteStateEnum.DELETE_FAILED);
				}
			}
			
		}
	}

	@Override
	public NewsUpdateExecution UpdateCheck(News news) throws RuntimeException {
		if(news == null){
			return new NewsUpdateExecution(NewsUpdateStateEnum.NULL_INPUT);
		}
		else{
			int count = newsDao.updateNews(news);
			if(count > 0){
				return new NewsUpdateExecution(NewsUpdateStateEnum.UPDATE_SUCCESS);
			}
			else{
				return new NewsUpdateExecution(NewsUpdateStateEnum.UPDATE_FAILED);
			}
		}
	}

	@Override
	public NewsQuerExecution QuerOneCheck(News news) throws RuntimeException {
		News t_news = null;
		if(news == null){
			return new NewsQuerExecution(NewsQuerStateEnum.NULL_INPUT);
		}
		else{
			t_news = newsDao.queryOneNews(news.getNewsTitle());
			if(t_news != null){
				return new NewsQuerExecution(NewsQuerStateEnum.QUERY_SUCCESS,t_news);
			}
			else{
				return new NewsQuerExecution(NewsQuerStateEnum.QUERY_FAILED);
			}
		}
	}

	@Override
	public NewsQuerExecution QuerCheck(Map<String,Object> pageMap) throws RuntimeException {	
		return new NewsQuerExecution(NewsQuerStateEnum.QUERY_SUCCESS,newsDao.queryNews(pageMap));
	}

	@Override
	public NewsQuerExecution QuerAllCheck() throws RuntimeException {
		return new NewsQuerExecution(NewsQuerStateEnum.QUERY_SUCCESS,newsDao.queryAllNews());
	}

}
