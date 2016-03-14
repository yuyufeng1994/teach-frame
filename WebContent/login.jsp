<%@page import="com.icss.commons.CookieUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
CookieUtil cookieUtil = new CookieUtil(request);
%><!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
 <jsp:include page="/inc.jsp"/>
  </head>
  
  <body>
<div class="panel panel-primary" style="width: 400px; margin: 0 auto; margin-top: 50px;">
<div class="panel-heading"><h3 class="panel-title"><i class="glyphicon glyphicon-user"></i> 用户登陆</h3></div>  
<div class="panel-body">
<form action="login.action" method="post" class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label">用户名</label>
    <div class="col-sm-8">
      <input name="userName" value="<%=cookieUtil.get("userName")==null?"":cookieUtil.get("userName").getValue() %>" type="text" class="form-control" id="inputEmail3" placeholder="请输入用户名">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">密码</label>
    <div class="col-sm-8">
      <input name="userPwd" value="<%=cookieUtil.get("userPwd")==null?"":cookieUtil.get("userPwd").getValue() %>"  type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-8">
      <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-user"></i> 登  录</button>
      <a href="register.jsp" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 注 册</a>
    </div>
  </div>
</form>
</div>
</div>
  </body>
</html>