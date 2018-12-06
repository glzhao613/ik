package com.gz.ik.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dao.NewsDao;
import com.gz.ik.dto.NewsAddExecution;
import com.gz.ik.dto.NewsDeleteExecution;
import com.gz.ik.dto.NewsQuerExecution;
import com.gz.ik.dto.NewsUpdateExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.entity.News;
import com.gz.ik.entity.NewsType;
import com.gz.ik.enums.NewsAddStateEnum;
import com.gz.ik.enums.NewsDeleteStateEnum;
import com.gz.ik.enums.NewsQuerStateEnum;
import com.gz.ik.enums.NewsUpdateStateEnum;
import com.gz.ik.service.NewsService;
import com.gz.ik.util.FileUtil;
import com.gz.ik.util.ImageUtil;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;


	@Override
	public NewsAddExecution addCheck(News news,CommonsMultipartFile img) throws RuntimeException {
		News t_news = null;
		if(news.getNewsTitle() == null || news.getNewsArticle() == null){
			return new NewsAddExecution(NewsAddStateEnum.NULL_INPUT);
		}
		else{
			t_news = newsDao.queryOneNews(news.getNewsTitle());
			if(t_news != null){
				return new NewsAddExecution(NewsAddStateEnum.ID_ERROR);
			}
			else{
				int count;
				if(img != null){
					addNewsImg(news,img);
				}
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
	public NewsDeleteExecution deleteCheck(News news) throws RuntimeException {
		if(news == null){
			return new NewsDeleteExecution(NewsDeleteStateEnum.NULL_INPUT);
		}
		else{
			News t_news = newsDao.queryNewsByID(news);
			if(t_news == null){
				return new NewsDeleteExecution(NewsDeleteStateEnum.ERROR_NOTEXIST);
			}
			else{
				if(t_news.getNewsImg() != null){
					FileUtil.deleteFile(t_news.getNewsImg());
				}
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
	public NewsUpdateExecution updateCheck(News news,CommonsMultipartFile img) throws RuntimeException {
		News t_news =null;
		if(news == null){
			return new NewsUpdateExecution(NewsUpdateStateEnum.NULL_INPUT);
		}
		else{
			t_news = newsDao.queryNewsByID(news);
			if(t_news == null){
				return new NewsUpdateExecution(NewsUpdateStateEnum.UPDATE_FAILED);
			}else{
				if(img != null){
					addNewsImg(news,img);
				}
				if(t_news.getNewsImg() != null){
					FileUtil.deleteFile(t_news.getNewsImg());
				}
				int count = newsDao.updateNews(news);
				if(count > 0){
					return new NewsUpdateExecution(NewsUpdateStateEnum.UPDATE_SUCCESS);
				}
				else{
					return new NewsUpdateExecution(NewsUpdateStateEnum.UPDATE_FAILED);
				}
			}
			
		}
	}

	@Override
	public NewsQuerExecution querOneCheck(News news) throws RuntimeException {
		News t_news = null;
		if(news == null){
			return new NewsQuerExecution(NewsQuerStateEnum.NULL_INPUT);
		}
		else{
			if(news.getNewsId() != null){
				t_news = newsDao.queryNewsByID(news);
			}
			else{
				t_news = newsDao.queryOneNews(news.getNewsTitle());
			}
			if(t_news != null){
				return new NewsQuerExecution(NewsQuerStateEnum.QUERY_SUCCESS,t_news);
			}
			else{
				return new NewsQuerExecution(NewsQuerStateEnum.QUERY_FAILED);
			}
		}
	}

	@Override
	public NewsQuerExecution querCheck(Map<String,Object> pageMap) throws RuntimeException {	
		return new NewsQuerExecution(NewsQuerStateEnum.QUERY_SUCCESS,newsDao.queryNews(pageMap));
	}

	@Override
	public NewsQuerExecution querAllCheck() throws RuntimeException {
		return new NewsQuerExecution(NewsQuerStateEnum.QUERY_SUCCESS,newsDao.queryAllNews());
	}

	@Override
	public List<NewsType> queryNewsType() throws RuntimeException {
		return newsDao.queryNewsType();
	}
	
	private void addNewsImg(News news, CommonsMultipartFile img) {
		String dest = FileUtil.getNewsImgPath();
		String imgAddr = ImageUtil.generateThumbnail(img, dest);
		news.setNewsImg(imgAddr);
	}

	@Override
	public News queryNewsByID(News news) throws RuntimeException {
		return newsDao.queryNewsByID(news);
	}

	@Override
	public List<News> querNewsByDate() throws RuntimeException {
		return newsDao.querNewsByDate();
	}
}
