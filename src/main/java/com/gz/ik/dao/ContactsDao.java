package com.gz.ik.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gz.ik.entity.Contacts;

public interface ContactsDao {
	
	/*根据联系人ID进行查询*/
	Contacts quercontacts(int contactid);
	
	Contacts quercontactsTel(String contactTel);
	
	List<Contacts> queryContactsList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	int queryContactsCount();
	
	/*添加联系人*/
	int insertcontacts(Contacts contacts);
	
	/*根据Id删除联系人*/
	boolean deletecontacts(int contactid);
	
}
