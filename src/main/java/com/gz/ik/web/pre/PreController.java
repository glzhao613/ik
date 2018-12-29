package com.gz.ik.web.pre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pre")
public class PreController {
	@RequestMapping(value = "/coursedetail", method = RequestMethod.GET)
	private String getcoursedmanagement(){
		return "/pre_html/c_detail";
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	private String getcoursemanagement(){
		return "/pre_html/course";
	}
	
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	private String getnewsmanagement(){
		return "/pre_html/news";
	}
	
	@RequestMapping(value = "/news_d", method = RequestMethod.GET)
	private String getnewsdmanagement(){
		return "/pre_html/news_detail";
	}
	
	@RequestMapping(value = "/center", method = RequestMethod.GET)
	private String getcentermanagement(){
		return "/pre_html/center";
	}
	
	@RequestMapping(value = "/indexpage", method = RequestMethod.GET)
	private String getindexpagemanagement(){
		return "/pre_html/indexpage";
	}
	
	@RequestMapping(value = "/td", method = RequestMethod.GET)
	private String gettdmanagement(){
		return "/pre_html/t_detail";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	private String getcontactmanagement(){
		return "/pre_html/contact";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	private String getfilemanagement(){
		return "/pre_html/file";
	}
	
		
}
