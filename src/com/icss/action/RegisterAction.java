package com.icss.action;

import com.icss.framework.annotation.Result;
import com.icss.framework.annotation.Results;
import com.icss.service.UserInfoService;
import com.icss.service.impl.UserInfoServiceImpl;
import com.icss.vo.UserInfoVO;

@Results({
	@Result(name="success",value="/register-success.jsp"),
	@Result(name="error",value="/register-error.jsp")
})
public class RegisterAction {
	
	private UserInfoVO vo = new UserInfoVO();
	
	private String msg;
	
	private UserInfoService service = new UserInfoServiceImpl();

	public String execute(){
		
		vo.setUserLock(false);
		System.out.println(vo);
		
		try{
			service.register(vo);
		}catch(Exception ex){
			msg = ex.getMessage();
			return "error";
		}
		return "success";
		
	}

	public UserInfoVO getModel() {
		return vo;
	}

	public String getMsg() {
		return msg;
	}


	
}
