package com.gz.ik.service;

import com.gz.ik.dto.AdminExecution;
import com.gz.ik.dto.ContactsDeleteExecution;
import com.gz.ik.dto.ContactsInsertExecution;
import com.gz.ik.dto.ContactsQuerExecution;
import com.gz.ik.entity.Admin;
import com.gz.ik.entity.Contacts;

public interface ContactsService {
	
	ContactsQuerExecution querCheck(Contacts contacts) throws RuntimeException;
	ContactsInsertExecution inserCheck(Contacts contacts) throws RuntimeException;
	ContactsDeleteExecution deleteCheck(Contacts contacts) throws RuntimeException;
	
	ContactsQuerExecution showContactsList(int pageIndex, int pageSize) throws RuntimeException;
	
}
