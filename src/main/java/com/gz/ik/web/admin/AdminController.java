package com.gz.ik.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String getLoginPage() {
		return "adminlogin";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	private String getregister() {
		return "adminregister";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	private String getupdate() {
		return "adminupdate";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	private String getdelete() {
		return "admindelete";
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	private String getAdminPage() {
		return "adminPage";
	}
	

}
