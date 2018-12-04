package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.ik.dao.FileTypeDao;
import com.gz.ik.dao.FilesDao;
import com.gz.ik.dto.FileTypeExecution;
import com.gz.ik.dto.FilesExecution;
import com.gz.ik.entity.FileType;
import com.gz.ik.entity.Files;
import com.gz.ik.enums.FileTypeStateEnum;
import com.gz.ik.enums.FilesStateEnum;
import com.gz.ik.service.FileTypeService;
import com.gz.ik.util.PageCalculator;

@Service
public class FileTypeServiceImpl implements FileTypeService {

	@Autowired
	private FileTypeDao fileTypeDao;

	@Autowired
	private FilesDao filesDao;
	
	@Override
	public FileTypeExecution queryFileTypeList(FileType fileType) throws RuntimeException {
		List<FileType> list = null;
		list=fileTypeDao.queryFileType(fileType);
		FileTypeExecution ex = new FileTypeExecution();
		if (list != null && list.size() > 0) {
			ex.setState(FileTypeStateEnum.QUERY_SECCESS.getState());
			ex.setEntityList(list);
		} else {
			ex.setState(FileTypeStateEnum.QUERY_FALSE.getState());
		}
		return ex;
	}

	@Override
	public FileTypeExecution getFileTypeList(FileType fileType, int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<FileType> list = null;
		list = fileTypeDao.queryFileTypeList(fileType, rowIndex, pageSize);
		int count = fileTypeDao.queryFileType(fileType).size();
		FileTypeExecution ex = new FileTypeExecution();
		if (list != null && list.size() > 0) {
			ex.setState(FileTypeStateEnum.QUERY_SECCESS.getState());
			ex.setEntityList(list);
			ex.setCount(count);
		} else {
			ex.setState(FileTypeStateEnum.QUERY_FALSE.getState());
		}
		return ex;
	}

	@Override
	@Transactional
	public FileTypeExecution delFileType(FileType fileType) throws RuntimeException {
		Files files = new Files();
		files.setFileType(fileType);
		int num = 0;
		if (filesDao.queryFiles(files).size() > 0) {
			num = filesDao.delFiles(files);
			if (num <= 0) {
				return new FileTypeExecution(FileTypeStateEnum.DEL_FALESE);
			}
		}
		num = fileTypeDao.delFileType(fileType);
		if (num <= 0) {
			return new FileTypeExecution(FileTypeStateEnum.DEL_FALESE);
		} else {
			return new FileTypeExecution(FileTypeStateEnum.DEL_SUCCESS);
		}
	}

	@Override
	public FileTypeExecution addFileType(FileType fileType) throws RuntimeException {
		int num = fileTypeDao.insertFileType(fileType);
		if (num <= 0) {
			return new FileTypeExecution(FileTypeStateEnum.INSERT_FALSE);
		} else {
			return new FileTypeExecution(FileTypeStateEnum.INSERT_SUCCESS);
		}
	}

	@Override
	public FileTypeExecution updateFileType(FileType fileType) throws RuntimeException {
		int num = fileTypeDao.updateFileType(fileType);
		if (num <= 0) {
			return new FileTypeExecution(FileTypeStateEnum.UPDATE_FALSE);
		} else {
			return new FileTypeExecution(FileTypeStateEnum.UPDATE_SUCCESS);
		}
	}


}
