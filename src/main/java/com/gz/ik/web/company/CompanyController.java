package com.gz.ik.web.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@RequestMapping(value = "/companyman", method = RequestMethod.GET)
	private String getcompany(){
		return "companymanagement";
	}
	
	@RequestMapping(value = "/companyadd", method = RequestMethod.GET)
	private String getaddcompany(){
		return "companyadd";
	}
	
	@RequestMapping(value = "/companyupdate", method = RequestMethod.GET)
	private String getupdatecompany(){
		return "companyupdate";
	}
	
	@RequestMapping(value = "/center", method = RequestMethod.GET)
	private String getCentercompany(){
		return "front_html/center";
	}
}
