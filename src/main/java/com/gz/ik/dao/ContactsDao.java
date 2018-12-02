package com.gz.ik.dao;

import java.util.List;

import com.gz.ik.entity.Contacts;

public interface ContactsDao {
	
	List<Contacts> quercontactlist(Contacts contacts);
	/*根据联系人ID进行查询*/
	Contacts quercontacts(int contactid);
	
	/*添加联系人*/
	int insertcontacts(Contacts contacts);
	
	/*根据Id删除联系人*/
	boolean deletecontacts(int contactid);
	
}
