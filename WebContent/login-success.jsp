<%@page import="com.icss.Const"%>
<%@page import="com.icss.vo.UserInfoVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserInfoVO vo = (UserInfoVO)session.getAttribute(Const.SESSION_USER);
	if(vo==null){
		response.sendRedirect("login.jsp");
		return ;
	}
%><!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录成功</title>
<jsp:include page="/inc.jsp"/>
</head>

<body>
	<div class="panel panel-default"
		style="width: 400px; margin: 0 auto; margin-top: 50px;">
		<div class="panel-body" style="text-align: center;">
			<h1><i class="glyphicon glyphicon-ok-sign text-success"></i> </h1>
			
	账户：${user.userName}<%--=vo.getUserName() --%><br/>
	姓名：${sessionScope.user.userRealName}<%--=vo.getUserRealName() --%><br/>
	<a href="<%=response.encodeURL("login-success.jsp")%>">重新加载</a><br/>
	<a href="<%=response.encodeURL("admin/user-list.action")%>">用户列表</a><br/>
	<a onclick="return window.confirm('您真的要退出我们的系统吗？');" href="<%=response.encodeURL("logout")%>">安全退出</a><br/>
		</div>
	</div>
</body>
</html>