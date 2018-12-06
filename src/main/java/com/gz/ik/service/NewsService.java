package com.gz.ik.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dto.NewsAddExecution;
import com.gz.ik.dto.NewsDeleteExecution;
import com.gz.ik.dto.NewsQuerExecution;
import com.gz.ik.dto.NewsUpdateExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.entity.News;
import com.gz.ik.entity.NewsType;

public interface NewsService {
	NewsAddExecution addCheck(News news,CommonsMultipartFile img) throws RuntimeException;
	
	NewsDeleteExecution deleteCheck(News news) throws RuntimeException;
	
	NewsUpdateExecution updateCheck(News news,CommonsMultipartFile img) throws RuntimeException;
	
	NewsQuerExecution querCheck(Map<String,Object> pageMap) throws RuntimeException;
	
	NewsQuerExecution querOneCheck(News news) throws RuntimeException;
	
	NewsQuerExecution querAllCheck() throws RuntimeException;
	
	List<NewsType> queryNewsType() throws RuntimeException;
	
	News queryNewsByID(News news) throws RuntimeException;
	
	List<News> querNewsByDate() throws RuntimeException;
} 
