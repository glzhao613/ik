package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.ContactsDao;
import com.gz.ik.dto.ContactsDeleteExecution;
import com.gz.ik.dto.ContactsInsertExecution;
import com.gz.ik.dto.ContactsQuerExecution;
import com.gz.ik.dto.TeacherExecution;
import com.gz.ik.entity.Contacts;
import com.gz.ik.entity.Teacher;
import com.gz.ik.enums.ContactsDeleteStateEnum;
import com.gz.ik.enums.ContactsInsertStateEnum;
import com.gz.ik.enums.ContactsQuerStateEnum;
import com.gz.ik.enums.TeacherStateEnum;
import com.gz.ik.service.ContactsService;
import com.gz.ik.util.PageCalculator;

@Service
public class ContactsServiceImpl implements ContactsService {
	
	@Autowired
	private ContactsDao contactsDao;

	@Override
	public ContactsQuerExecution querCheck(Contacts contacts) throws RuntimeException {
		Contacts q_contact=null;
		if(contacts==null) {
			return new ContactsQuerExecution(ContactsQuerStateEnum.NULL_INPUT);
		}
		else {
			q_contact=contactsDao.quercontacts(contacts.getContactId());
			if(q_contact==null) {
				return new ContactsQuerExecution(ContactsQuerStateEnum.ERROR);
			}
			else {
				return new ContactsQuerExecution(ContactsQuerStateEnum.PASS,q_contact);
			}
		}
	}

	@Override
	public ContactsInsertExecution inserCheck(Contacts contacts) throws RuntimeException {
		if(contacts==null) {
			return new ContactsInsertExecution(ContactsInsertStateEnum.NULL_INPUT);
		}
		else {
			Contacts i_contact=contactsDao.quercontactsTel(contacts.getContactTel());
			if(i_contact==null) {
				int count=contactsDao.insertcontacts(contacts);
				if(count>0) {
					return new ContactsInsertExecution(ContactsInsertStateEnum.PASS,contacts);
				}
				else {
					return new ContactsInsertExecution(ContactsInsertStateEnum.ERROR);
				}
			}
			else {
				return new ContactsInsertExecution(ContactsInsertStateEnum.ERROR_ACCOUNT);
			}
		}
	}

	@Override
	public ContactsDeleteExecution deleteCheck(Contacts contacts) throws RuntimeException {
		if(contacts==null) {
			return new ContactsDeleteExecution(ContactsDeleteStateEnum.NULL_INPUT);
		}
		else {
			boolean d_contacts=false;
			d_contacts=contactsDao.deletecontacts(contacts.getContactId());
			if(d_contacts) {
				return new ContactsDeleteExecution(ContactsDeleteStateEnum.PASS);
			}
			else {
				return new ContactsDeleteExecution(ContactsDeleteStateEnum.ERROR);
			}
		}
	}

	@Override
	public ContactsQuerExecution showContactsList(int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Contacts> contactsList = null;
		contactsList = contactsDao.queryContactsList(rowIndex, pageSize);
		int count = contactsDao.queryContactsCount();
		ContactsQuerExecution te = new ContactsQuerExecution();
		if (contactsList != null && contactsList.size() > 0) {
			te.setState(ContactsQuerStateEnum.GET_SECCESS.getState());
			te.setContactslist(contactsList);
			te.setCount(count);
		} else {
			te.setState(ContactsQuerStateEnum.GET_FALSE.getState());
		}
		return te;
	}
	
	

}
