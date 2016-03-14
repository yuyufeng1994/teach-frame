<%@page import="com.icss.Const"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %><% 
String path=request.getContextPath(); 
String basePath=request.getScheme()+ "://"+request.getServerName()+ ":"+request.getServerPort()+path+ "/"; 
%><!DOCTYPE HTML>
<html>

<head>
	<base href="<%=basePath%>">
	<title>演示首页</title>
	<jsp:include page="/inc.jsp"/>
</head>
<body style="width: 400px; margin: 10px auto">

						<a class="btn btn-primary btn-lg btn-block" href="register.jsp">用户注册</a>
						<a class="btn btn-primary btn-lg btn-block" href="login.jsp">用户登陆</a>
						<a class="btn btn-primary btn-lg btn-block" href="admin/user-list.action">用户列表</a>
						

</body>

</html>












