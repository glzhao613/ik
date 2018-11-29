package com.gz.ik.web.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.NewsQuerExecution;
import com.gz.ik.entity.News;
import com.gz.ik.enums.NewsQuerStateEnum;
import com.gz.ik.service.NewsService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/newsadm")
public class NewsManagermentController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="/newsinfo",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> querOneNews(HttpServletRequest request){
		Map<String, Object> newsMap = new HashMap<String, Object>();
		News news = new News();
		String newstitle = HttpServletRequestUtil.getString(request, "newstitle");
		news.setNewsTitle(newstitle);
		NewsQuerExecution nqe = newsService.QuerOneCheck(news);
		if(nqe.getState() == NewsQuerStateEnum.QUERY_SUCCESS.getState()){
			newsMap.put("success", 1);
			// 若查询成功，则加入session中
			request.getSession().setAttribute("querOneNews", nqe.getNews());
		}
		else{
			newsMap.put("success", -1);
			newsMap.put("errMsg", nqe.getStateInfo());
		}
		return newsMap;
	}
	
	@RequestMapping(value="/newspaging/{currentPage}",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> querNews(@PathVariable Integer currentPage){
		Map<String, Object> newsPagingMap = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		if(currentPage == null){
			currentPage = 1;
		}
		newsPagingMap.put("currentPage", currentPage);
		int pageCount = 1;
		newsPagingMap.put("pageCount", pageCount);
		int start = (currentPage - 1) * pageCount;
		pageMap.put("start", start);
		pageMap.put("pageCount", pageCount);
		NewsQuerExecution nqe = newsService.QuerCheck(pageMap);
		if(nqe.getState()==NewsQuerStateEnum.QUERY_SUCCESS.getState()){
			newsPagingMap.put("success", true);
			List<News> newslist = nqe.getNewsList();
			// 若查询成功，则加入Map中
			newsPagingMap.put("newsList",newslist );
			
		}
		else{
			newsPagingMap.put("success", false);
			newsPagingMap.put("errMsg", nqe.getStateInfo());
		}
		NewsQuerExecution nqe1 = newsService.QuerAllCheck();
		if(nqe1.getState()==NewsQuerStateEnum.QUERY_SUCCESS.getState()){
			List<News> newslist1 = nqe1.getNewsList();
			//查询所有记录成功，把总记录条数加入Map中
			newsPagingMap.put("totalNews", newslist1.size());
		}
		return newsPagingMap;
	}
}
