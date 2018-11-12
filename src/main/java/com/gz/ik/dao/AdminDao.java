package com.gz.ik.dao;

import java.util.List;

import com.gz.ik.entity.Admin;


/*通过账号判断是否有这个管理员*/
public interface AdminDao {
	Admin queryadmin (String account);
}
