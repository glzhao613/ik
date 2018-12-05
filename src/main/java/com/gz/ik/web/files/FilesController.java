package com.gz.ik.web.files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/files")
public class FilesController {
	
	@RequestMapping(value = "/showfiles", method = RequestMethod.GET)
	private String getFilesListPage() {
		return "showfiles";
	}
	
	@RequestMapping(value = "/addfiles", method = RequestMethod.GET)
	private String getAddFilesPage() {
		return "addfiles";
	}
	
	@RequestMapping(value = "/updatefiles", method = RequestMethod.GET)
	private String getUpdateFilesPage() {
		return "updatefiles";
	}
}
