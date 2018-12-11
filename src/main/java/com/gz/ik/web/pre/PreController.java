package com.gz.ik.web.pre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pre")
public class PreController {
	@RequestMapping(value = "/coursedetail", method = RequestMethod.GET)
	private String getcoursedmanagement(){
		return "coursedetail";
	}
	
}
