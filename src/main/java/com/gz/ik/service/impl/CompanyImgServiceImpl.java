package com.gz.ik.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.gz.ik.dao.CompanyImgDao;
import com.gz.ik.dto.CompanyImgExecution;
import com.gz.ik.entity.Company;
import com.gz.ik.entity.CompanyImg;
import com.gz.ik.enums.CompanyImgStateEnum;
import com.gz.ik.service.CompanyImgService;
import com.gz.ik.util.FileUtil;
import com.gz.ik.util.ImageUtil;

@Service
public class CompanyImgServiceImpl implements CompanyImgService {
	
	@Autowired
	private CompanyImgDao companyimgdao;
	
	@Override
	public CompanyImgExecution querCheck(CompanyImg companyimg) throws RuntimeException {
		if(companyimg == null){
			return new CompanyImgExecution(CompanyImgStateEnum.NULL_INPUT);
		}else{
			CompanyImg t_companyimg = companyimgdao.querCompanyImgById(companyimg);
			if(t_companyimg !=null){
				return new CompanyImgExecution(CompanyImgStateEnum.SUCCESS,t_companyimg);
			}else{
				return new CompanyImgExecution(CompanyImgStateEnum.FAILED);
			}
		}
	}

	@Override
	public CompanyImgExecution querPagingCheck(Map<String, Object> pageMap) throws RuntimeException {
		return new CompanyImgExecution(CompanyImgStateEnum.SUCCESS,companyimgdao.querCompanyImgPaging(pageMap));
	}

	@Override
	public CompanyImgExecution querAllCheck() throws RuntimeException {
		return new CompanyImgExecution(CompanyImgStateEnum.SUCCESS,companyimgdao.querAllCompanyImg());
	}

	@Override
	public CompanyImgExecution addCheck(CompanyImg companyimg, CommonsMultipartFile img) throws RuntimeException {
		if(companyimg == null){
			return new CompanyImgExecution(CompanyImgStateEnum.NULL_INPUT);
		}else{
			CompanyImg t_companyimg = companyimgdao.querCompanyImgById(companyimg);
			if(t_companyimg != null){
				return new CompanyImgExecution(CompanyImgStateEnum.ID_ERROR);
			}else{
				if(img != null){
					addCompanyImg(companyimg,img);
				}
				int count = companyimgdao.insertCompanyImg(companyimg);
				if(count > 0){
					return new CompanyImgExecution(CompanyImgStateEnum.SUCCESS);
				}else{
					return new CompanyImgExecution(CompanyImgStateEnum.FAILED);
				}
			}
		}
	}

	@Override
	public CompanyImgExecution updateCheck(CompanyImg companyimg) throws RuntimeException {
		if(companyimg == null){
			return new CompanyImgExecution(CompanyImgStateEnum.NULL_INPUT);
		}else{
			CompanyImg t_companyimg = companyimgdao.querCompanyImgById(companyimg);
			if(t_companyimg == null){
				return new CompanyImgExecution(CompanyImgStateEnum.ID_ERROR);
			}else{
				int count = companyimgdao.updateCompanyImg(companyimg);
				if(count > 0){
					return new CompanyImgExecution(CompanyImgStateEnum.SUCCESS);
				}else{
					return new CompanyImgExecution(CompanyImgStateEnum.FAILED);
				}
			}
		}
	}

	@Override
	public CompanyImgExecution deleteCheck(CompanyImg companyimg) throws RuntimeException {
		if(companyimg == null){
			return new CompanyImgExecution(CompanyImgStateEnum.NULL_INPUT);
		}else{
			Company company = companyimgdao.querCompanyByCompanyImgId(companyimg.getCompanyImgId());
			if(company !=null){
				return new CompanyImgExecution(CompanyImgStateEnum.FAILED);
			}else{
				CompanyImg t_companyimg = companyimgdao.querCompanyImgById(companyimg);
				if(t_companyimg == null){
					return new CompanyImgExecution(CompanyImgStateEnum.ID_ERROR);
				}else{
					if(t_companyimg.getCompanyImgPath() != null){
						FileUtil.deleteFile(t_companyimg.getCompanyImgPath());
					}
					boolean index = companyimgdao.deleteCompanyImgById(companyimg.getCompanyImgId());
					if(index){
						return new CompanyImgExecution(CompanyImgStateEnum.SUCCESS);
					}else{
						return new CompanyImgExecution(CompanyImgStateEnum.FAILED);
					}
				}
			}
		}
	}
	
	private void addCompanyImg(CompanyImg companyimg, CommonsMultipartFile img) {
		String dest = FileUtil.getCompanyImgPath();
		String imgAddr = ImageUtil.generateThumbnail(img, dest);
		companyimg.setCompanyImgPath(imgAddr);
	}

}
