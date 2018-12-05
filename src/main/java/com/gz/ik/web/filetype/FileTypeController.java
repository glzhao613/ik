package com.gz.ik.web.filetype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/filetype")
public class FileTypeController {
	
	@RequestMapping(value = "/showfiletype", method = RequestMethod.GET)
	private String getFileTypeListPage() {
		return "showfiletype";
	}
	
	@RequestMapping(value = "/addfiletype", method = RequestMethod.GET)
	private String getAddFileTypePage() {
		return "addfiletype";
	}
	
	@RequestMapping(value = "/updatefiletype", method = RequestMethod.GET)
	private String getUpdateFileTypePage() {
		return "updatefiletype";
	}
}
