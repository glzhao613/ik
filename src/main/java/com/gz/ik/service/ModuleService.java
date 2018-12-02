package com.gz.ik.service;

import java.util.Map;

import com.gz.ik.dto.ModuleAddExecution;
import com.gz.ik.dto.ModuleDeleteExecution;
import com.gz.ik.dto.ModuleQuerExecution;
import com.gz.ik.dto.ModuleUpdateExecution;
import com.gz.ik.entity.Module;

public interface ModuleService {
	ModuleAddExecution AddCheck(Module module) throws RuntimeException;
	ModuleQuerExecution QuerCheck(Module module) throws RuntimeException;
	ModuleQuerExecution QuerListCheck(Map<String, Object> pageMap) throws RuntimeException;
	ModuleUpdateExecution UpdateCheck(Module module) throws RuntimeException;
	ModuleDeleteExecution DeleteCheck(Module module) throws RuntimeException;
}
