package com.gz.ik.service;

import com.gz.ik.dto.FilesExecution;
import com.gz.ik.entity.Files;

public interface FilesService {
	
	FilesExecution queryFilesList(Files files)throws RuntimeException;
	
	FilesExecution getFilesList(Files files,int pageIndex, int pageSize)throws RuntimeException;

	FilesExecution delFiles(Files files)throws RuntimeException;
	
	FilesExecution addFiles(Files files)throws RuntimeException;
	
	FilesExecution updateFiles(Files files)throws RuntimeException;
	
}
