package com.gz.ik.web.news;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="/newspaging",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> querNews(HttpServletRequest request){
		Map<String, Object> newsPagingMap = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int currentPage = 1;
		int pageCount = 1;
		int start = (currentPage - 1) * pageCount;
		pageMap.put("start", start);
		pageMap.put("pageCount", pageCount);
		NewsQuerExecution nqe = newsService.QuerCheck(pageMap);
		if(nqe.getState()==NewsQuerStateEnum.QUERY_SUCCESS.getState()){
			newsPagingMap.put("success", 1);
			// 若查询成功，则加入
			newsPagingMap.put("newsList", nqe.getNewsList());
			
		}
		else{
			newsPagingMap.put("success", -1);
			newsPagingMap.put("errMsg", nqe.getStateInfo());
		}
		return newsPagingMap;
	}
}
