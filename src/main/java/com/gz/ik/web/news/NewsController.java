package com.gz.ik.web.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/news")
public class NewsController {
	@RequestMapping(value = "/newsinfo", method = RequestMethod.GET)
	private String getnews(){
		return "news";
	}
	@RequestMapping(value = "/newspaging",method =  RequestMethod.GET)
	private String getnewspaging(){
		return "newspaging";
	}
}
