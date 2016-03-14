<%@ page language="java" import="java.util.*" errorPage="true" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%><!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录失败</title>
<jsp:include page="/inc.jsp"/>
</head>

<body>
	<div class="panel panel-default"
		style="width: 400px; margin: 0 auto; margin-top: 50px;">
		<div class="panel-body" style="text-align: center;">
			<h1><i class="glyphicon glyphicon-remove-sign text-danger"></i> 
				${msg }
			</h1>
		</div>
	</div>
	
	
</body>
</html>