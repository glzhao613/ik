package com.gz.ik.dto;

import java.util.List;

import com.gz.ik.entity.Contacts;
import com.gz.ik.enums.ContactsInsertStateEnum;

public class ContactsInsertExecution {
	
	//结果状态
	private int state;
	
	//状态标识
	private String stateInfo;
	
	private int count;
	
	private Contacts contacts;
	
	private List<Contacts> contactslist;
	
	public ContactsInsertExecution() {
		super();
	}
	
	//联系人插入失败操作失败的构造器
	public ContactsInsertExecution(ContactsInsertStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//联系人插入成功的构造器
	public ContactsInsertExecution(ContactsInsertStateEnum stateEnum,Contacts contacts) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.contacts = contacts;
	}
	
	//联系人插入成功的构造器
	public ContactsInsertExecution(ContactsInsertStateEnum stateEnum,List<Contacts> contactslist) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.contactslist = contactslist;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	public List<Contacts> getContactslist() {
		return contactslist;
	}

	public void setContactslist(List<Contacts> contactslist) {
		this.contactslist = contactslist;
	}



	
	
}
