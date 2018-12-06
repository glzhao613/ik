package com.gz.ik.web.news;

import java.util.Date;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
		int newsID = HttpServletRequestUtil.getInt(request, "newsid");
		
		news.setNewsTitle(newstitle);
		news.setNewsId(newsID);
		
		NewsQuerExecution nqe = newsService.querOneCheck(news);
		if(nqe.getState() == NewsQuerStateEnum.QUERY_SUCCESS.getState()){
			newsMap.put("success", 1);
			// 若查询成功，则加入Map中
			newsMap.put("querOneNews", nqe.getNews());
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
		int pageCount = 5;
		
		
		newsPagingMap.put("pageCount", pageCount);
		int start = (currentPage - 1) * pageCount;
		pageMap.put("start", start);
		pageMap.put("pageCount", pageCount);
		NewsQuerExecution nqe = newsService.querCheck(pageMap);
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
		NewsQuerExecution nqe1 = newsService.querAllCheck();
		if(nqe1.getState()==NewsQuerStateEnum.QUERY_SUCCESS.getState()){
			List<News> newslist1 = nqe1.getNewsList();
			//查询所有记录成功，把总记录条数加入Map中
			newsPagingMap.put("totalNews", newslist1.size());
		}
		return newsPagingMap;
	}
	
	@RequestMapping(value="/quernewstype",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> addNewsType(HttpServletRequest request){
		Map<String, Object> addNewsTypeMap = new HashMap<String, Object>();
		List<NewsType> newsTypeList = newsService.queryNewsType();
		addNewsTypeMap.put("newsTypeList", newsTypeList);
		return addNewsTypeMap;
	}
	
	@RequestMapping(value="/addnews",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> addNews(HttpServletRequest request){
		Map<String, Object> addNewsMap = new HashMap<String, Object>();
		News news = new News();
		int newstype = HttpServletRequestUtil.getInt(request, "newsType");
		String newsTitle = HttpServletRequestUtil.getString(request, "newsTitle");
		String newsArticle = HttpServletRequestUtil.getString(request, "newsArticle");
		
		NewsType newsType = new NewsType();
		newsType.setNewsTypeId(newstype);
		news.setNewsType(newsType);
		
		news.setNewsTitle(newsTitle);
		news.setNewsArticle(newsArticle);
		
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("newsImg");
		}
		
		news.setNewsDate(new Date());
		
/*		Admin admin = (Admin) request.getSession().getAttribute("loginadmin");
		Admin t_admin = new Admin();
		Integer adminid = null;
		if(admin != null){
			adminid	=	admin.getAdminId();
		}
		t_admin.setAdminId(adminid);
		news.setAdmin(t_admin);*/
		
		NewsAddExecution nae = newsService.addCheck(news,img);
		if(nae.getState()==NewsAddStateEnum.ADD_SUCCESS.getState()){
			addNewsMap.put("success", true);
			addNewsMap.put("news", nae.getNews());
		}else{
			addNewsMap.put("success", false);
			addNewsMap.put("errMsg", nae.getStateInfo());
		}
		
		return addNewsMap;	
	}
	
	@RequestMapping(value="/deletenews",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> deleteNews(HttpServletRequest request){
		Map<String, Object> deleteNewsMap = new HashMap<String, Object>();
		News news = new News();
		
		int newsID = HttpServletRequestUtil.getInt(request, "newsid");
		news.setNewsId(newsID);
		
		NewsDeleteExecution nde = newsService.deleteCheck(news);
		if(nde.getState()==NewsDeleteStateEnum.DELETE_SUCCESS.getState()){
			deleteNewsMap.put("success", true);
			deleteNewsMap.put("news", nde.getStateInfo());
		}else{
			deleteNewsMap.put("success", false);
			deleteNewsMap.put("errMsg", nde.getStateInfo());
		}
		return deleteNewsMap;
	}
	
	@RequestMapping(value="/updatenews",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> updateNews(HttpServletRequest request){
		Map<String, Object> updateNewsMap = new HashMap<String, Object>();
		News news = new News();
		
		int newsType = HttpServletRequestUtil.getInt(request, "newstype");
		String newsTitle = HttpServletRequestUtil.getString(request, "newstitle");
		String newsArticle = HttpServletRequestUtil.getString(request, "newsarticle");
		
		
		NewsType newsTYpe = new NewsType();
		newsTYpe.setNewsTypeId(newsType);
		
		news.setNewsType(newsTYpe);
		
		news.setNewsTitle(newsTitle);
		news.setNewsArticle(newsArticle);
		
		// 图片获取
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile img = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			img = (CommonsMultipartFile) multipartRequest.getFile("newsimg");
		}
		
		news.setNewsDate(new Date());
		
		int newsID = (int) request.getSession().getAttribute("newsid");
		news.setNewsId(newsID);
		
		News updatenews = (News) request.getSession().getAttribute("updatenews");
		
		updateNewsMap.put("updatenews", updatenews);
		
/*		Admin admin = (Admin) request.getSession().getAttribute("loginadmin");
		Admin t_admin = new Admin();
		Integer adminid = null;
		if(admin != null){
			adminid	=	admin.getAdminId();
		}
		t_admin.setAdminId(adminid);
		news.setAdmin(t_admin);*/
		
		NewsUpdateExecution nue = newsService.updateCheck(news, img);
		if(nue.getState()==NewsUpdateStateEnum.UPDATE_SUCCESS.getState()){
			updateNewsMap.put("success", true);
			updateNewsMap.put("updatenews", nue.getNews());
		}else{
			updateNewsMap.put("success", false);
			updateNewsMap.put("errMsg",nue.getStateInfo());
		}
		
		return updateNewsMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByNewsId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int newsId = HttpServletRequestUtil.getInt(request, "newsid");
		News t_news = new News();
		t_news.setNewsId(newsId);
		if (newsId != -1) {
			request.getSession().setAttribute("newsid", newsId);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
	
	@RequestMapping(value = "/setupdateid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> setUpdateId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int newsId = (int) request.getSession().getAttribute("newsid");
		News t_news = new News();
		t_news.setNewsId(newsId);
		if (newsId != -1) {
			News news = newsService.queryNewsByID(t_news);
			modelMap.put("updatenews", news);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
	
	@RequestMapping(value = "/getnewsbydate", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getNewsByDate(HttpServletRequest request) {
		Map<String, Object> newsMap = new HashMap<String, Object>();
		
		List<News> newsList = newsService.querNewsByDate();
		
		newsMap.put("newslist", newsList);
		
		return newsMap;
	}
}
