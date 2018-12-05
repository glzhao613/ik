package com.gz.ik.web.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/course")
public class CourseController {

	@RequestMapping(value = "/quer", method = RequestMethod.GET)
	private String getQuerPage() {
		return "quercourse";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	private String getInsertPage() {
		return "addcourse";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	private String getDeletePage() {
		return "deletecourse";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	private String getUpdatePage() {
		return "updatecourse";
	}
	
	@RequestMapping(value = "/showcourse", method = RequestMethod.GET)
	private String getShowCoursePage() {
		return "showcourse";
	}
	
	@RequestMapping(value = "/cours", method = RequestMethod.GET)
	private String getCourseFront() {
		return "front_html/courses";
	}
	@RequestMapping(value = "/dcours", method = RequestMethod.GET)
	private String getDCourseFront() {
		return "front_html/detail";
	}
}
