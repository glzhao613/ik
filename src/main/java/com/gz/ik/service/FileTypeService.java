package com.gz.ik.service;

import com.gz.ik.dto.FileTypeExecution;
import com.gz.ik.entity.FileType;

public interface FileTypeService {
	
	FileTypeExecution queryFileTypeList(FileType fileType)throws RuntimeException;
	
	FileTypeExecution getFileTypeList(FileType fileType,int pageIndex, int pageSize)throws RuntimeException;

	FileTypeExecution delFileType(FileType fileType)throws RuntimeException;
	
	FileTypeExecution addFileType(FileType fileType)throws RuntimeException;
	
	FileTypeExecution updateFileType(FileType fileType)throws RuntimeException;
	
}
