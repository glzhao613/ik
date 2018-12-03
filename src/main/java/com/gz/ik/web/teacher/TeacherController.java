package com.gz.ik.web.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@RequestMapping(value = "/quer", method = RequestMethod.GET)
	private String getQuerPage() {
		return "querteacher";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	private String getInsertPage() {
		return "addteacher";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	private String getUpdatePage() {
		return "updateteacher";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	private String getDeletePage() {
		return "deleteteacher";
	}
	
	@RequestMapping(value = "/showteacher", method = RequestMethod.GET)
	private String getShowTeacherPage() {
		return "showteacher";
	}
}
