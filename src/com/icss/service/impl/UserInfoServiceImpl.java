package com.icss.service.impl;

import java.util.List;

import com.icss.dao.UserInfoDAO;
import com.icss.exception.PasswordErrorException;
import com.icss.exception.UserLockedException;
import com.icss.exception.UserNotExistsException;
import com.icss.service.UserInfoService;
import com.icss.vo.PageVO;
import com.icss.vo.UserInfoVO;

/**
 * 
 * @author J.L.Zhou
 *
 */
public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoDAO dao = new UserInfoDAO();
	
	public UserInfoVO login1(String userName, String userPwd) {
		UserInfoVO vo = dao.findByUserName(userName);
		if(vo.getUserLock()){
			throw new RuntimeException("用户被锁定");
		}
		if(!vo.getUserPwd().equals(userPwd)){
			throw new RuntimeException("密码错误");
		}
		return vo;
	}
	@Override
	public List<UserInfoVO> listAll() {
		return dao.findAll();
	}
	@Override
	public void delete(long userId) {
		dao.delete(new UserInfoVO(userId));
		
	}
	@Override
	public void register(UserInfoVO userInfoVO) {
		try{
			dao.add(userInfoVO);
		}catch (Exception e) {
			throw new RuntimeException("用户名已存在",e);
		}
		
	}
	@Override
	public PageVO<UserInfoVO> pageAll(int page, int rowMax) {
		if(page<1){page=1;}
		PageVO<UserInfoVO> pageVO = new PageVO<UserInfoVO>();
		pageVO.setCurrent(page);
		pageVO.setRowMax(rowMax);
		pageVO.setTotal(dao.countAll());
		if(pageVO.getTotal()==0){
			return null;
		}
		int pageMax = (int)((pageVO.getTotal()-1)/rowMax+1);
		pageVO.setPageMax(pageMax);
		if(page>pageMax){
			pageVO.setCurrent(pageMax);
		}
		pageVO.setRows(dao.findAll(pageVO.getCurrent(), rowMax));
		return pageVO;
	}
	@Override
	public UserInfoVO login(String userName, String userPwd)
			throws PasswordErrorException, UserLockedException,
			UserNotExistsException {
		UserInfoVO vo = null;
		try{
			vo = dao.findByUserName(userName);
		}catch (Exception e) {
			throw new UserNotExistsException(e);
		}
		if(vo.getUserLock()){
			throw new UserLockedException();
		}
		if(!vo.getUserPwd().equals(userPwd)){
			throw new PasswordErrorException();
		}
		return vo;
	}

}
