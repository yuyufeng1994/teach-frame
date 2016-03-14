package com.icss.vo;

public class UserInfoVO {
	
	private Long userId;
	private String userName;
	private String userPwd;
	private String userRealName;
	private Boolean userLock;
	
	public UserInfoVO() {
		super();
	}
	
	
	public UserInfoVO(Long userId) {
		super();
		this.userId = userId;
	}



	public UserInfoVO(Long userId, String userName, String userPwd,
			String userRealName, Boolean userLock) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userRealName = userRealName;
		this.userLock = userLock;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Boolean getUserLock() {
		return userLock;
	}
	public void setUserLock(Boolean userLock) {
		this.userLock = userLock;
	}
	
	public String getVersion(){
		return "hahah";
	}


	@Override
	public String toString() {
		return "\nUserInfoVO [userId=" + userId + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", userRealName=" + userRealName
				+ ", userLock=" + userLock + "]";
	}


	

}
