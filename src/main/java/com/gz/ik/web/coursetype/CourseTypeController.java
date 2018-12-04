package com.gz.ik.web.coursetype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/coursetype")
public class CourseTypeController {

	@RequestMapping(value = "/quer", method = RequestMethod.GET)
	private String getQuerPage() {
		return "quercoursetype";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	private String getInsertPage() {
		return "addcoursetype";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	private String getUpdatePage() {
		return "updatecoursetype";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	private String getDeletePage() {
		return "deletecoursetype";
	}
	
	@RequestMapping(value = "/showcoursetype", method = RequestMethod.GET)
	private String getShowcourseTypePage() {
		return "showcoursetype";
	}
}
