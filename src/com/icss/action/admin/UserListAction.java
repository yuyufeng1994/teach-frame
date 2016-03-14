package com.icss.action.admin;

import com.icss.service.UserInfoService;
import com.icss.service.impl.UserInfoServiceImpl;
import com.icss.vo.PageVO;
import com.icss.vo.UserInfoVO;

public class UserListAction {

	private UserInfoService userInfoService = new UserInfoServiceImpl();
	
	private PageVO<UserInfoVO> pageVO;
	private int page = 1;
	
	public String execute() {
		pageVO = userInfoService.pageAll(page, 10);
		return "success";
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageVO<UserInfoVO> getPageVO() {
		return pageVO;
	}
	
	
	

}
