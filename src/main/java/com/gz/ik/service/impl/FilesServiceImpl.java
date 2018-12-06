package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.FilesDao;
import com.gz.ik.dto.FilesExecution;
import com.gz.ik.entity.Files;
import com.gz.ik.enums.FilesStateEnum;
import com.gz.ik.service.FilesService;
import com.gz.ik.util.PageCalculator;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesDao filesDao;
	
	@Override
	public FilesExecution queryFilesList(Files files) throws RuntimeException {
		List<Files> list = null;
		list=filesDao.queryFiles(files);
		FilesExecution ex = new FilesExecution();
		if (list != null && list.size() > 0) {
			ex.setState(FilesStateEnum.QUERY_SECCESS.getState());
			ex.setEntityList(list);
		} else {
			ex.setState(FilesStateEnum.QUERY_FALSE.getState());
		}
		return ex;
	}
	
	@Override
	public FilesExecution getFilesList(Files files, int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Files> list = null;
		list=filesDao.queryFilesList(files, rowIndex, pageSize);
		int count =filesDao.queryFiles(files).size();
		FilesExecution ex = new FilesExecution();
		if (list != null && list.size() > 0) {
			ex.setState(FilesStateEnum.QUERY_SECCESS.getState());
			ex.setEntityList(list);
			ex.setCount(count);
		} else {
			ex.setState(FilesStateEnum.QUERY_FALSE.getState());
		}
		return ex;
	}

	@Override
	public FilesExecution delFiles(Files files) throws RuntimeException {
		int num=filesDao.delFiles(files);
		if(num<=0) {
			return new FilesExecution(FilesStateEnum.DEL_FALESE);
		}else {
			return new FilesExecution(FilesStateEnum.DEL_SUCCESS);
		}
	}

	@Override
	public FilesExecution addFiles(Files files) throws RuntimeException {
		int num=filesDao.insertFiles(files);
		if(num<=0) {
			return new FilesExecution(FilesStateEnum.INSERT_FALSE);
		}else {
			return new FilesExecution(FilesStateEnum.INSERT_SUCCESS);
		}
	}

	@Override
	public FilesExecution updateFiles(Files files) throws RuntimeException {
		int num=filesDao.updateFiles(files);
		if(num<=0) {
			return new FilesExecution(FilesStateEnum.UPDATE_FALSE);
		}else {
			return new FilesExecution(FilesStateEnum.UPDATE_SUCCESS);
		}
	}

	@Override
	public FilesExecution getFilesListByUId(int uId, int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Files> list = null;
		list=filesDao.getFilesListByUId(uId, rowIndex, pageSize);
		int count =filesDao.getFilesByUId(uId).size();
		FilesExecution ex = new FilesExecution();
		if (list != null && list.size() > 0) {
			ex.setState(FilesStateEnum.QUERY_SECCESS.getState());
			ex.setEntityList(list);
			ex.setCount(count);
		} else {
			ex.setState(FilesStateEnum.QUERY_FALSE.getState());
		}
		return ex;
	}


}
