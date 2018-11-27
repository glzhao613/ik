package com.gz.ik.dao;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.gz.ik.BaseTest;
import com.gz.ik.entity.News;

public class NewsDaoTest extends BaseTest {
	@Autowired
	private NewsDao newsDao;
	
/*	@Test
	public void testqueryNews(){
		Map<String,Object> pageMap = new HashMap<String,Object>();
		int currentPage = 1;
		int pageCount = 2;
		int start = (currentPage - 1) * pageCount;
		pageMap.put("start", start);
		pageMap.put("pageCount", pageCount);
		List<News> effNum = newsDao.queryNews(pageMap);
		assertEquals(2,effNum.size());
	}*/
/*    @Test
	public void testqueryAllNews(){
		List<News> effNum=newsDao.queryAllNews();
		assertEquals(2, effNum.size());
	}*/
	
}
