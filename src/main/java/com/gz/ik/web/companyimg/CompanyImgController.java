package com.gz.ik.web.companyimg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/companyimg")
public class CompanyImgController {
	@RequestMapping(value = "/companyimgman", method = RequestMethod.GET)
	private String getcompanyimg(){
		return "companyimgmanagement";
	}
	
	@RequestMapping(value = "/companyimgadd", method = RequestMethod.GET)
	private String getaddcompanyimg(){
		return "companyimgadd";
	}
	
	@RequestMapping(value = "/companyimgupdate", method = RequestMethod.GET)
	private String getupdatecompanyimg(){
		return "companyimgupdate";
	}
}
