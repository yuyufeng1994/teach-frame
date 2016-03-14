package com.icss.service;

import java.util.List;

import com.icss.exception.PasswordErrorException;
import com.icss.exception.UserLockedException;
import com.icss.exception.UserNotExistsException;
import com.icss.vo.PageVO;
import com.icss.vo.UserInfoVO;

/**
 * 用户相关业务接口
 * @author J.L.Zhou
 *
 */
public interface UserInfoService {

	/**
	 * 	 * 根据用户名，密码，判断用户是否是合法用户<br/>
	 * 如果合法，返回用户对象<br/>
	 * 如果不合法，抛出异常，错误原因在异常消息里
	 * @return	用户信息
	 * @param userName	用户名
	 * @param userPwd	密码
	 * @return
	 * @throws PasswordErrorException
	 * @throws UserLockedException
	 * @throws UserNotExistsException
	 */
	UserInfoVO login(String userName,String userPwd)throws PasswordErrorException,UserLockedException,UserNotExistsException;
	
	
	/**
	 * 
	 * @param userInfoVO
	 * @throws runtimeexception
	 */
	void register(UserInfoVO userInfoVO);
	
	/**
	 * 
	 * @return
	 */
	List<UserInfoVO> listAll();
	
	PageVO<UserInfoVO> pageAll(int page,int rowsMax);
	/**
	 * 
	 * @param userId
	 */
	void delete(long userId);
}
