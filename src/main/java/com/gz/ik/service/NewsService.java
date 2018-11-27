package com.gz.ik.service;

import java.util.Map;

import com.gz.ik.dto.NewsAddExecution;
import com.gz.ik.dto.NewsDeleteExecution;
import com.gz.ik.dto.NewsQuerExecution;
import com.gz.ik.dto.NewsUpdateExecution;
import com.gz.ik.entity.News;

public interface NewsService {
	NewsAddExecution AddCheck(News news) throws RuntimeException;
	NewsDeleteExecution DeleteCheck(News news) throws RuntimeException;
	NewsUpdateExecution UpdateCheck(News news) throws RuntimeException;
	NewsQuerExecution QuerCheck(Map<String,Object> pageMap) throws RuntimeException;
	NewsQuerExecution QuerOneCheck(News news) throws RuntimeException;
	NewsQuerExecution QuerAllCheck() throws RuntimeException;
} 
