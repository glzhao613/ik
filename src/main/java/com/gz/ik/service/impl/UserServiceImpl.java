package com.gz.ik.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.ik.dao.UserDao;
import com.gz.ik.dto.UserExecution;
import com.gz.ik.entity.Course;
import com.gz.ik.entity.UC;
import com.gz.ik.entity.User;
import com.gz.ik.enums.UserStateEnum;
import com.gz.ik.service.UserService;
import com.gz.ik.util.FileUtil;
import com.gz.ik.util.ImageUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserExecution loginCheck(User user) throws RuntimeException {

		User t_user = null;
		if (user == null || user.getUserAccount() == null || user.getUserPwd() == null) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		} else {
			t_user = userDao.queryUserByUserAccount(user.getUserAccount());
			if (t_user == null) {
				return new UserExecution(UserStateEnum.NO_ACCOUNT);
			} else {
				if (!(user.getUserPwd().equals(t_user.getUserPwd()))) {
					return new UserExecution(UserStateEnum.PWD_ERROR);
				}
			}

		}
		return new UserExecution(UserStateEnum.PASS, t_user);
	}

	@Override
	@Transactional
	public UserExecution userAdd(User user) throws RuntimeException {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		User t_user = null;
		int num = 0;
		if (user == null || user.getUserAccount() == null || user.getUserPwd() == null || user.getCourseList() == null
				|| user.getCourseList().size() <= 0) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		} else {
			t_user = userDao.queryUserByUserAccount(user.getUserAccount());
			if (t_user != null) {
				return new UserExecution(UserStateEnum.EXIST_ACCOUNT);
			} else {
				user.setUserImg(basePath + "default.png");
				num = userDao.insertUser(user);
				if (num <= 0) {
					return new UserExecution(UserStateEnum.REG_FAlSE);
				} else {
					t_user = userDao.queryUserByUserAccount(user.getUserAccount());
					UC uc = new UC();
					uc.setuId(t_user.getUserId());
					for (Course c : user.getCourseList()) {
						uc.setcId(c.getCourseId());
						if (userDao.savaUserCourse(uc) <= 0) {
							return new UserExecution(UserStateEnum.REG_FAlSE);
						}
					}
				}
			}
		}
		return new UserExecution(UserStateEnum.REG_SUCCESS, t_user);
	}
/*	
	@Override
	@Transactional
	public UserExecution userDel(String userAccount) throws RuntimeException {
		User t_user = userDao.queryUserByUserAccount(userAccount);
		int num=0;
		num=userDao.delUserAllCourse(t_user.getUserId());
		if(num<=0) {
			return new UserExecution(UserStateEnum.DELCOURSE_FALSE);
		}else {
			num=userDao.delUser(userAccount);
			if(num<=0) {
				return new UserExecution(UserStateEnum.DELUSER_FALSE);
			}
		}
		
		return new UserExecution(UserStateEnum.DELUSER_SUCCESS, t_user);
	}

	@Override
	@Transactional
	public UserExecution addUserCourse(User user) throws RuntimeException {
		if (user == null || user.getUserId()==-1 || user.getCourseList() == null
				|| user.getCourseList().size() <= 0) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		} 
		else {
			UC uc = new UC();
			uc.setuId(user.getUserId());
			for (Course c : user.getCourseList()) {
				uc.setcId(c.getCourseId());
				if (userDao.savaUserCourse(uc) <= 0) {
					return new UserExecution(UserStateEnum.ADDCOURSE_FALSE);
				}
			}
			
		}
		return new UserExecution(UserStateEnum.ADDCOURSE_SUCCESS);
	}

	@Override
	@Transactional
	public UserExecution delUserCourse(User user) throws RuntimeException {
		if (user == null || user.getUserId()==-1 || user.getCourseList() == null
				|| user.getCourseList().size() <= 0) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		} 
		else {
			UC uc = new UC();
			List<Course> cList=null;
			uc.setuId(user.getUserId());
			for (Course c : user.getCourseList()) {
				uc.setcId(c.getCourseId());
				cList=userDao.queryCourseByUserId(user.getUserId());
				if(cList.size()<=1) {
					return new UserExecution(UserStateEnum.NOT_ALL);
				}
				if (userDao.delUserCourse(uc) <= 0) {
					return new UserExecution(UserStateEnum.DELCOURSE_FALSE);
				}
			}
			
		}
		return new UserExecution(UserStateEnum.DELCOURSE_SUCCESS);
	}

	*/
	
	@Override
	public UserExecution updataUserPwd(User user, String newPwd) throws RuntimeException {
		User t_user = null;
		if (user == null || user.getUserAccount() == null || user.getUserPwd() == null||newPwd==null) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		} else {
			t_user = userDao.queryUserByUserAccount(user.getUserAccount());
			if (t_user == null) {
				return new UserExecution(UserStateEnum.NO_ACCOUNT);
			} else {
				if (!(user.getUserPwd().equals(t_user.getUserPwd()))) {
					return new UserExecution(UserStateEnum.PWD_ERROR);
				}
			}

		}
		int num=0;
		user.setUserPwd(newPwd);
		num=userDao.updataUser(user);
		if(num<=0) {
			return new UserExecution(UserStateEnum.UPDATAPWD_FALSE);
		}
		
		return new UserExecution(UserStateEnum.UPDATAPWD_SUCCESS);
	}
	
	@Override
	@Transactional
	public UserExecution updataUserInfo(User user, CommonsMultipartFile img) throws RuntimeException {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		User t_user = null;
		int num=0;
		if (user == null && img==null) {
			return new UserExecution(UserStateEnum.NULL_INPUT);
		}else {
			if(user.getUserAccount()==null) {
				return new UserExecution(UserStateEnum.NO_LOGIN);
			}else {
				if(img!=null) {
					t_user = userDao.queryUserByUserAccount(user.getUserAccount());
					if(!t_user.getUserImg().equals(basePath + "default.png")) {
						FileUtil.deleteFile(t_user.getUserImg());
					}
					addUserImg(user, img);
					
				}
				num=userDao.updataUser(user);
				if(num<=0) {
					return new UserExecution(UserStateEnum.UPDATAINFO_FALSE);
				}
				
			}	
		}
		t_user = userDao.queryUserByUserAccount(user.getUserAccount());
		return new UserExecution(UserStateEnum.UPDATAINFO_SUCCESS,t_user);
	}

	
	private void addUserImg(User user, CommonsMultipartFile img) {
		String dest = FileUtil.getUserImgPath();
		String imgAddr = ImageUtil.generateThumbnail(img, dest);
		user.setUserImg(imgAddr);
	}


	

}
