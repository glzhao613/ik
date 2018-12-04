package com.gz.ik.web.newstype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/newstype")
public class NewsTypeController {
	
	@RequestMapping(value = "/newstypeman", method = RequestMethod.GET)
	private String getnewstypemanagement(){
		return "newstypemanagement";
	}
	
	@RequestMapping(value = "/newstypeadd", method = RequestMethod.GET)
	private String getaddnewstype(){
		return "newstypeadd";
	}
	
	@RequestMapping(value = "/newstypeupdate", method = RequestMethod.GET)
	private String getupdatenewstype(){
		return "newstypeupdate";
	}
}
