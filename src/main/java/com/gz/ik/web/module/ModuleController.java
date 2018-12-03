package com.gz.ik.web.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module")
public class ModuleController {
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	private String getadd() {
		return "moduleadd";
	}
	
	@RequestMapping(value="/quer",method=RequestMethod.GET)
	private String getquer() {
		return "modulequer";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	private String getupdate() {
		return "moduleupdate";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	private String getdelete() {
		return "moduledelete";
	}
	
	@RequestMapping(value="/showmodule",method=RequestMethod.GET)
	private String getShowModule() {
		return "showmodule";
	}
}
