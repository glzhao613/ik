package com.gz.ik.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String getLoginPage() {
		return "userlogin";
	}
	
	@RequestMapping(value = "/updatapwd", method = RequestMethod.GET)
	private String getUpdataPwdPage() {
		return "updatauserpwd";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	private String getAddUserPage() {
		return "adduser";
	}
	
	@RequestMapping(value = "/updatainfo", method = RequestMethod.GET)
	private String getUpdataInfoPage() {
		return "updatauserinfo";
	}
	
	@RequestMapping(value = "/usercourse", method = RequestMethod.GET)
	private String getUserCoursePage() {
		return "usercourse";
	}
	
	@RequestMapping(value = "/showuser", method = RequestMethod.GET)
	private String getShowUserPage() {
		return "showuser";
	}
	
	@RequestMapping(value = "/addusercourse", method = RequestMethod.GET)
	private String getAddUserCoursePage() {
		return "addusercourse";
	}

}
