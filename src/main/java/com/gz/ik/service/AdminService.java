package com.gz.ik.service;

import java.util.Map;

import com.gz.ik.dto.AdminDeleteExecution;
import com.gz.ik.dto.AdminExecution;
import com.gz.ik.dto.AdminRegisterExecution;
import com.gz.ik.dto.AdminUpdateExecution;
import com.gz.ik.entity.Admin;

public interface AdminService {
	AdminExecution loginCheck(Admin admin) throws RuntimeException;
	AdminRegisterExecution registerCheck(Admin admin) throws RuntimeException;
	AdminUpdateExecution updateCheck(Admin admin) throws RuntimeException;
	AdminDeleteExecution deleteCheck(Admin admin) throws RuntimeException;
	AdminExecution querCheck(Map<String, Object> pageMap) throws RuntimeException;
}
