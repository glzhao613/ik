package com.gz.ik.web.contacts;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.ContactsDeleteExecution;
import com.gz.ik.dto.ContactsInsertExecution;
import com.gz.ik.dto.ContactsQuerExecution;
import com.gz.ik.entity.Contacts;
import com.gz.ik.enums.ContactsDeleteStateEnum;
import com.gz.ik.enums.ContactsInsertStateEnum;
import com.gz.ik.enums.ContactsQuerStateEnum;
import com.gz.ik.service.ContactsService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/contactsad")
public class ContactsManagementController {
	
	@Autowired
	private ContactsService contactService;
	

	@RequestMapping(value = "/contactsinsert", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> contactInsert(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Contacts contact = new Contacts();
		String contactTel = HttpServletRequestUtil.getString(request, "contacttel");
		String contactName = HttpServletRequestUtil.getString(request, "contactname");
		String contactEmail = HttpServletRequestUtil.getString(request, "contactemail");
		String contactArticle = HttpServletRequestUtil.getString(request, "contactarticle");

		contact.setContactTel(contactTel);
		contact.setContactName(contactName);
		contact.setContactEmail(contactEmail);
		contact.setContactArticle(contactArticle);
		
		ContactsInsertExecution ad=contactService.inserCheck(contact);
		
		if(ad.getState() == ContactsInsertStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			// 若插入成功，则加入session中
			request.getSession().setAttribute("insertcontacts", ad.getContacts());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}
	
	
	@RequestMapping(value = "/contactsquer", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> contactQuer(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Contacts contact = new Contacts();
		int contactId=HttpServletRequestUtil.getInt(request, "contactid");

		contact.setContactId(contactId);
		
		ContactsQuerExecution ad=contactService.querCheck(contact);
		
		if(ad.getState() == ContactsQuerStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
			// 若查询成功，则加入session中
			request.getSession().setAttribute("quercontacts", ad.getContacts());
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/contactsdelete", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> contactDelete(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Contacts contact = new Contacts();
		int contactId=HttpServletRequestUtil.getInt(request, "contactid");

		contact.setContactId(contactId);
		
		ContactsDeleteExecution ad=contactService.deleteCheck(contact);
		
		if(ad.getState() == ContactsDeleteStateEnum.PASS.getState()) {
			modelMap.put("success", 1);
		}
		else {
			modelMap.put("success", -1);
			modelMap.put("errMsg", ad.getStateInfo());
		}
		return modelMap;
	}



	
}
