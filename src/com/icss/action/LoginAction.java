package com.icss.action;

import com.icss.Const;
import com.icss.framework.ServletActionContext;
import com.icss.framework.annotation.Result;
import com.icss.framework.annotation.Results;
import com.icss.service.UserInfoService;
import com.icss.service.impl.UserInfoServiceImpl;
import com.icss.vo.UserInfoVO;

@Results({
	@Result(name="success",value="/login-success.jsp"),
	@Result(name="error",value="/login-error.jsp")
})
public class LoginAction {
	
	private String userName;
	private String userPwd;
	private String msg;
	
	private UserInfoService service = new UserInfoServiceImpl();

	public String execute() throws Exception{
		System.out.println("userName:"+userName+"\tuserPwd:"+userPwd);
		UserInfoVO vo = null;
		
		try{
			vo = service.login(userName, userPwd);
			ServletActionContext.getRequest().getSession().setAttribute(Const.SESSION_USER, vo);
			msg = "登录成功";
			return "success";
		}catch (Exception e) {

			msg = e.getMessage();
			return "error";
			
		}
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
}
