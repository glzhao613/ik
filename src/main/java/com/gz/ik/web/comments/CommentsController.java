package com.gz.ik.web.comments;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	
	@RequestMapping(value = "/showcomments", method = RequestMethod.GET)
	private String getLoginPage() {
		return "showcomments";
	}
	
	

}
