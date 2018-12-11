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
	

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	private String getUserInfoPage() {
		return "userinfo";
	}

	@RequestMapping(value = "/main", method = {RequestMethod.GET,RequestMethod.POST})
	private String getmainPage() {
		return "/manage/main";
	}
	
	@RequestMapping(value = "/nav", method = RequestMethod.GET)
	private String getnavPage() {
		return "/manage/nav";
	}
	
	@RequestMapping(value = "/mainuser", method = RequestMethod.GET)
	private String getmainuserPage() {
		return "/manage/mainuser";
	}
	
	@RequestMapping(value = "/navuser", method = RequestMethod.GET)
	private String getnavuserPage() {
		return "/manage/navuser";
	}
	
	@RequestMapping(value = "/mainnews", method = RequestMethod.GET)
	private String getmainnewsPage() {
		return "/manage/mainnews";
	}
	
	@RequestMapping(value = "/navnews", method = RequestMethod.GET)
	private String getnavnewsPage() {
		return "/manage/navnews";
	}
	
	@RequestMapping(value = "/mainadmin", method = RequestMethod.GET)
	private String getmainadminPage() {
		return "/manage/mainadmin";
	}
	
	@RequestMapping(value = "/navadmin", method = RequestMethod.GET)
	private String getnavadminPage() {
		return "/manage/navadmin";
	}
	
	@RequestMapping(value = "/maincourse", method = RequestMethod.GET)
	private String getmaincoursePage() {
		return "/manage/maincourse";
	}
	
	@RequestMapping(value = "/navcourse", method = RequestMethod.GET)
	private String getnavcoursePage() {
		return "/manage/navcourse";
	}
	
	@RequestMapping(value = "/mainfiles", method = RequestMethod.GET)
	private String getmainfilesPage() {
		return "/manage/mainfiles";
	}
	
	@RequestMapping(value = "/navfiles", method = RequestMethod.GET)
	private String getnavfilesPage() {
		return "/manage/navfiles";
	}
	
	@RequestMapping(value = "/maincompany", method = RequestMethod.GET)
	private String getmaincompanyPage() {
		return "/manage/maincompany";
	}
	
	@RequestMapping(value = "/navcompany", method = RequestMethod.GET)
	private String getnavcompanyPage() {
		return "/manage/navcompany";
	}

}
