package com.gz.ik.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gz.ik.BaseTest;
import com.gz.ik.entity.Admin;

public class AdminDaoTest extends BaseTest {
	@Autowired
	private AdminDao adminDao;
	@Test
	public void textqueryAdmin() {
	/*	Admin admin=new Admin();
		admin.setAdminId(1);
		int effNum=adminDao.insertadmin(admin);
		assertEquals(1, effNum);
		*/
	}
}
