package com.gz.ik.web.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/news")
public class NewsController {
	@RequestMapping(value = "/newsquer", method = RequestMethod.GET)
	private String getquerynews(){
		return "newsquer";
	}
	@RequestMapping(value = "/newspaging",method =  RequestMethod.GET)
	private String getnewspaging(){
		return "newsshow";
	}
	@RequestMapping(value = "/newsadd",method =  RequestMethod.GET)
	private String getaddnews(){
		return "newsadd";
	}
	
	@RequestMapping(value = "/newsdel",method =  RequestMethod.GET)
	private String getdeletenews(){
		return "newsdelete";
	}
	
	@RequestMapping(value = "/newsupdate",method =  RequestMethod.GET)
	private String getupdatenews(){
		return "newsupdate";
	}
	
}
