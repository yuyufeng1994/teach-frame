<%@page import="com.icss.Const"%>
<%@page import="com.icss.vo.UserInfoVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//UserInfoVO vo = (UserInfoVO)request.getAttribute("userInfoVO");

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
			<h1><i class="glyphicon glyphicon-ok-sign text-success"></i> 注册成功</h1>
			
	账户：<%--=vo.getUserName() --%>${model.userName }<br/>
	姓名：<%--=vo.getUserRealName() --%>${model.userRealName }<br/>
		</div>
	</div>
</body>
</html>