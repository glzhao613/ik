package com.gz.ik.service;

import com.gz.ik.dto.CommentsExecution;
import com.gz.ik.entity.Comments;

public interface CommentsService {
	
	CommentsExecution getCommentsList(Comments comments,int pageIndex, int pageSize)throws RuntimeException;

	CommentsExecution delComments(Comments comments)throws RuntimeException;
	
	CommentsExecution addComments(Comments comments)throws RuntimeException;
	
}
