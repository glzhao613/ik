package com.gz.ik.service;

import com.gz.ik.dto.AdminExecution;
import com.gz.ik.entity.Admin;

public interface AdminService {
	AdminExecution loginCheck(Admin admin) throws RuntimeException;
}
