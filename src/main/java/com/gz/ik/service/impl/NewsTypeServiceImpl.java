package com.gz.ik.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.ik.dao.NewsTypeDao;
import com.gz.ik.dto.NewsTypeExecution;
import com.gz.ik.entity.News;
import com.gz.ik.entity.NewsType;
import com.gz.ik.enums.NewsTypeStateEnum;
import com.gz.ik.service.NewsTypeService;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {
	
	@Autowired
	private NewsTypeDao newsTypeDao;
	
	@Override
	public NewsTypeExecution quercheck(NewsType newsType) throws RuntimeException {
		NewsType t_newsType;
		if(newsType == null){
			return new NewsTypeExecution(NewsTypeStateEnum.NULL_INPUT);
		}else{
			t_newsType = newsTypeDao.querNewsType(newsType);
			if(t_newsType != null){
				return new NewsTypeExecution(NewsTypeStateEnum.SUCCESS,t_newsType);  
			}else{
				return new NewsTypeExecution(NewsTypeStateEnum.FAILED);
			}
		}
	}

	@Override
	public NewsTypeExecution updatecheck(NewsType newsType) throws RuntimeException {
		NewsType t_newsType;
		if(newsType.getNewsTypeName() == null){
			return new NewsTypeExecution(NewsTypeStateEnum.NULL_INPUT);
		}else{
			t_newsType = newsTypeDao.querNewsType(newsType);
			if(t_newsType != null){
				return new NewsTypeExecution(NewsTypeStateEnum.ID_ERROR);
			}else{
				int count = newsTypeDao.updateNewsType(newsType);
				if(count > 0){
					return new NewsTypeExecution(NewsTypeStateEnum.SUCCESS);
				}else{
					return new NewsTypeExecution(NewsTypeStateEnum.FAILED);
				}
			}
		}
	}

	@Override
	public NewsTypeExecution insertcheck(NewsType newsType) throws RuntimeException {
		NewsType t_newsType;
		if(newsType.getNewsTypeName() == null){
			return new NewsTypeExecution(NewsTypeStateEnum.NULL_INPUT);
		}else{
			t_newsType = newsTypeDao.querNewsType(newsType);
			if(t_newsType != null){
				return new NewsTypeExecution(NewsTypeStateEnum.ID_ERROR);
			}else{
				int count = newsTypeDao.insertNewsType(newsType);
				if(count > 0){
					return new NewsTypeExecution(NewsTypeStateEnum.SUCCESS);
				}else{
					return new NewsTypeExecution(NewsTypeStateEnum.FAILED);
				}
			}
		}
	}
	
	@Transactional
	@Override
	public NewsTypeExecution deletecheck(NewsType newsType) throws RuntimeException {
		
		if(newsType == null){
			return new NewsTypeExecution(NewsTypeStateEnum.NULL_INPUT);
		}else{
			List<News> newslist = null;
			boolean n_index = true;
			newslist = newsTypeDao.querNewsByNewsType(newsType);
			if(newslist.size() != 0){
				 n_index = newsTypeDao.deleteNewsByNewsType(newsType);
			}
			if(n_index){
				boolean nty_index =  newsTypeDao.deleteNewsType(newsType);
				if(nty_index){
					return new NewsTypeExecution(NewsTypeStateEnum.SUCCESS);
				}else{
					return new NewsTypeExecution(NewsTypeStateEnum.FAILED);
				}
			}else{
				return new NewsTypeExecution(NewsTypeStateEnum.FAILED);
			}
		}
	}

	@Override
	public NewsTypeExecution querPagingcheck(Map<String, Object> pageMap) throws RuntimeException {
		return new NewsTypeExecution(NewsTypeStateEnum.SUCCESS,newsTypeDao.querNewsTypePaging(pageMap));
	}

	@Override
	public NewsTypeExecution querAllcheck() throws RuntimeException {
		return new NewsTypeExecution(NewsTypeStateEnum.SUCCESS,newsTypeDao.querAllNewsType());
	}

}
