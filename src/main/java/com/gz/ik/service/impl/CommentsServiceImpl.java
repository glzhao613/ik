package com.gz.ik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.ik.dao.CommentsDao;
import com.gz.ik.dto.CommentsExecution;
import com.gz.ik.entity.Comments;
import com.gz.ik.enums.CommentsStateEnum;
import com.gz.ik.service.CommentsService;
import com.gz.ik.util.PageCalculator;

@Service
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	private CommentsDao commentsDao;

	@Override
	public CommentsExecution getCommentsList(Comments comments, int pageIndex, int pageSize) throws RuntimeException {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Comments> list = null;
		list=commentsDao.queryCommentsList(comments, rowIndex, pageSize);
		int count = commentsDao.queryComments(comments).size();
		CommentsExecution ex = new CommentsExecution();
		if (list != null && list.size() > 0) {
			ex.setState(CommentsStateEnum.QUERY_SECCESS.getState());
			ex.setEntityList(list);
			ex.setCount(count);
		} else {
			ex.setState(CommentsStateEnum.QUERY_FALSE.getState());
		}
		return ex;
	}

	@Override
	public CommentsExecution delComments(Comments comments) throws RuntimeException {
		int num=commentsDao.delComments(comments);
		if(num<=0) {
			return new CommentsExecution(CommentsStateEnum.DEL_FALESE);
		}else {
			return new CommentsExecution(CommentsStateEnum.DEL_SUCCESS);
		}
	}

	@Override
	public CommentsExecution addComments(Comments comments) throws RuntimeException {
		int num=commentsDao.insertComments(comments);
		if(num<=0) {
			return new CommentsExecution(CommentsStateEnum.INSERT_FALSE);
		}else {
			return new CommentsExecution(CommentsStateEnum.INSERT_SUCCESS);
		}
	}
	

}
