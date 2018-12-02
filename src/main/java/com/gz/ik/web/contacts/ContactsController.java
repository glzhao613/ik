package com.gz.ik.web.contacts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
	
	@RequestMapping(value = "/quer", method = RequestMethod.GET)
	private String getQuerPage() {
		return "contactsquer";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	private String getInsertPage() {
		return "contactsinsert";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	private String getDeletePage() {
		return "contactsdelete";
	}
}
