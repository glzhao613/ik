package com.gz.ik.service;

import java.util.Map;

import com.gz.ik.dto.NewsTypeExecution;
import com.gz.ik.entity.NewsType;

public interface NewsTypeService {
	NewsTypeExecution quercheck(NewsType newsType) throws RuntimeException;
	
	NewsTypeExecution updatecheck(NewsType newsType) throws RuntimeException;
	
	NewsTypeExecution insertcheck(NewsType newsType) throws RuntimeException;
	
	NewsTypeExecution deletecheck(NewsType newsType) throws RuntimeException;
	
	NewsTypeExecution querPagingcheck(Map<String,Object> pageMap) throws RuntimeException;
	
	NewsTypeExecution querAllcheck() throws RuntimeException;
	
}
