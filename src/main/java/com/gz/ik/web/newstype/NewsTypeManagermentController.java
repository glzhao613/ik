package com.gz.ik.web.newstype;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.NewsTypeExecution;
import com.gz.ik.entity.NewsType;
import com.gz.ik.enums.NewsTypeStateEnum;
import com.gz.ik.service.NewsTypeService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/newstypeadm")
public class NewsTypeManagermentController {
	
	@Autowired
	private NewsTypeService newstypeService;
	
	@RequestMapping(value="/newstypeman/{currentPage}",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> querNews(@PathVariable Integer currentPage){
		Map<String, Object> newsTypePagingMap = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		if(currentPage == null){
			currentPage = 1;
		}
		newsTypePagingMap.put("currentPage", currentPage);
		int pageCount = 5;
		
		newsTypePagingMap.put("pageCount", pageCount);
		int start = (currentPage - 1) * pageCount;
		pageMap.put("start", start);
		pageMap.put("pageCount", pageCount);
		NewsTypeExecution nte = newstypeService.querPagingcheck(pageMap);
		if(nte.getState() == NewsTypeStateEnum.SUCCESS.getState()){
			newsTypePagingMap.put("success", true);
			newsTypePagingMap.put("newstypelist", nte.getNewsTypeList());		
		}else{
			newsTypePagingMap.put("success", false);
			newsTypePagingMap.put("errMsg", nte.getStateInfo());
		}
		NewsTypeExecution nte1 = newstypeService.querAllcheck();
		if(nte.getState() == NewsTypeStateEnum.SUCCESS.getState()){
			newsTypePagingMap.put("newstypetotal", nte1.getNewsTypeList().size());
		}
		return newsTypePagingMap;
	}
	
	@RequestMapping(value="/newstypeadd",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> addNewsType(HttpServletRequest request){
		Map<String, Object> newsTypeMap = new HashMap<String, Object>();
		NewsType newsType = new NewsType();
		
		String newsTypeName = HttpServletRequestUtil.getString(request, "newstypename");
		
		newsType.setNewsTypeName(newsTypeName);
		
		NewsTypeExecution nte = newstypeService.insertcheck(newsType);
		if(nte.getState() == NewsTypeStateEnum.SUCCESS.getState()){
			newsTypeMap.put("success", true);
			newsTypeMap.put("newstype", nte.getNewsType());		
		}else{
			newsTypeMap.put("success", false);
			newsTypeMap.put("errMsg", nte.getStateInfo());
		}
		return newsTypeMap;
	}
	
	@RequestMapping(value="/newstypeupdate",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> updateNewsType(HttpServletRequest request){
		Map<String, Object> newsTypeMap = new HashMap<String, Object>();
		NewsType newsType = new NewsType();
		
		String newsTypeName = HttpServletRequestUtil.getString(request, "newstypename");
		int newsTypeId = (int) request.getSession().getAttribute("newstypeid");
		
		newsType.setNewsTypeId(newsTypeId);
		newsType.setNewsTypeName(newsTypeName);
		System.out.println(newsTypeId);
		System.out.println(newsTypeName);
		NewsTypeExecution nte = newstypeService.updatecheck(newsType);
		if(nte.getState() == NewsTypeStateEnum.SUCCESS.getState()){
			newsTypeMap.put("success", true);
			newsTypeMap.put("newstypelist", nte.getNewsType());		
		}else{
			newsTypeMap.put("success", false);
			newsTypeMap.put("errMsg", nte.getStateInfo());
		}
		return newsTypeMap;
	}
	
	@RequestMapping(value = "/setid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByNewsTypeId(HttpServletRequest request) {
		Map<String, Object> newsTypeMap = new HashMap<String, Object>();
		int newsTypeId = HttpServletRequestUtil.getInt(request, "newstypeid");
		
		NewsType newsType = new NewsType();
		newsType.setNewsTypeId(newsTypeId);
		if (newsTypeId != -1) {
			NewsTypeExecution nte = newstypeService.quercheck(newsType);
			NewsType t_newsType = nte.getNewsType();
			
			request.getSession().setAttribute("newstypeid", t_newsType.getNewsTypeId());
			request.getSession().setAttribute("updatenewstype", t_newsType);
			newsTypeMap.put("success", true);
		} else {
			newsTypeMap.put("success", false);
		}
		return newsTypeMap;
	}
	
	@RequestMapping(value = "/setupdateid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> setUpdateId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int newsTypeId = (int) request.getSession().getAttribute("newstypeid");
		NewsType newsType = new NewsType();
		newsType.setNewsTypeId(newsTypeId);
		if (newsTypeId != -1) {
			NewsType t_newsType = newstypeService.quercheck(newsType).getNewsType();
			modelMap.put("updatenewstype", t_newsType);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}
	
	@RequestMapping(value = "/newstypedelete", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> deleteNewsType(HttpServletRequest request) {
		Map<String, Object> newsTypeMap = new HashMap<String, Object>();
		NewsType newsType = new NewsType();
		
		int newsTypeId = HttpServletRequestUtil.getInt(request, "newstypeid");
		
		newsType.setNewsTypeId(newsTypeId);
		NewsTypeExecution nte = newstypeService.deletecheck(newsType);
		if(nte.getState() == NewsTypeStateEnum.SUCCESS.getState()){
			newsTypeMap.put("success", true);	
		}else{
			newsTypeMap.put("success", false);
			newsTypeMap.put("errMsg", nte.getStateInfo());
		}
		return newsTypeMap;
	}
}
