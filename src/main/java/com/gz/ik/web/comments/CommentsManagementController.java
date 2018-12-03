package com.gz.ik.web.comments;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gz.ik.dto.CommentsExecution;

import com.gz.ik.entity.Comments;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.User;
import com.gz.ik.enums.CommentsStateEnum;

import com.gz.ik.service.CommentsService;
import com.gz.ik.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/commentsadmin")
public class CommentsManagementController {

	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value = "/setcourseid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> setByUserId(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int id = HttpServletRequestUtil.getInt(request, "courseid");
		if (id != -1) {
			request.getSession().setAttribute("bycourseid", id);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		return modelMap;

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getUserList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Comments comments=new Comments();
		if (request.getSession().getAttribute("bycourseid") != null) {
			Course course=new Course();
			course.setCourseId((Integer)request.getSession().getAttribute("bycourseid"));
			comments.setCommentCourse(course);
			
		}
		if ((pageIndex > -1) && (pageSize > -1)) {
			CommentsExecution ex = commentsService.getCommentsList(comments, pageIndex, pageSize);
			if (ex.getState() == CommentsStateEnum.QUERY_SECCESS.getState()) {
				System.out.println(ex.getEntityList().get(0).getCommentUser().getUserAccount());
				modelMap.put("list", ex.getEntityList());
				modelMap.put("count", (ex.getCount() - 1) / pageSize + 1);
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ex.getStateInfo());

			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/delcomments", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> delComments(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int commentId=(int)HttpServletRequestUtil.getInt(request, "commentid");
		Comments comments=new Comments();
		comments.setCommentId(commentId);
		CommentsExecution ex =commentsService.delComments(comments);
		if(ex.getState()==CommentsStateEnum.DEL_SUCCESS.getState()) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ex.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/addcomments", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addComments(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String commentArticle=(String)HttpServletRequestUtil.getString(request, "article");
		Comments comments=new Comments();
		if(commentArticle==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入评论内容");
		}else {
			User user=(User)request.getSession().getAttribute("loginuser");
			if(user==null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "请先登录");
			}else {
				comments.setCommentArticle(commentArticle);
				Course course=new Course();
				course.setCourseId((Integer)request.getSession().getAttribute("bycourseid"));
				comments.setCommentCourse(course);
				comments.setCommentUser(user);
			}
		}
		CommentsExecution ex =commentsService.addComments(comments);
		if(ex.getState()==CommentsStateEnum.INSERT_SUCCESS.getState()) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ex.getStateInfo());
		}
		return modelMap;
	}
	
	

	
}
