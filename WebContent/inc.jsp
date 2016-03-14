<%@ page language="java"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%><link rel="stylesheet"
	href="<%=basePath %>resources/bootstrap-3.3.2-dist/css/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="<%=basePath %>resources/bootstrap-3.3.2-dist/css/bootstrap-theme.min.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="<%=basePath %>resources/css/my.css"
	type="text/css"></link>
<script type="text/javascript" src="<%=basePath %>resources/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
