<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/etc" prefix="etc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<!DOCTYPE HTML>
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<title>用户列表</title>
<jsp:include page="/inc.jsp"></jsp:include>
<style type="text/css">
.icon-false,.icon-true {color:#F00; font-size: 18px; line-height: 18px; text-decoration:none;}
.icon-true,a.icon-false:hover { color:#090;text-decoration:none;}
a.icon-true:hover {color:#F00;text-decoration:none;}
</style>
</head>

<body>

<div class="panel panel-primary" style="width: 800px; margin: 10px auto;">
      <div class="panel-heading"><c:set value="学生信息列表" var="b" scope="page"></c:set>
      <%--pageContext.setAttribute("b", "<b>学生信息列表</b>"); --%>
        <h3 class="panel-title"><i class="glyphicon glyphicon-th-list"></i> 
        <c:out escapeXml="false" value="${b }" default="default"></c:out>
        <c:remove var="b" scope="page"/>
		${b }
        </h3>
      </div>
      <%--<div class="panel-body">
      	<a href="logout" class="btn btn-danger"><i class="glyphicon glyphicon-off"></i> 安全退出</a>
      </div> --%>
     
	<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="50" style="text-align: center;">#</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>锁定状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%--
			int count = 0;
			//List<UserInfoVO> list = (List<UserInfoVO>)request.getAttribute("list");
			PageVO<UserInfoVO> pageVO = (PageVO<UserInfoVO>)request.getAttribute("pageVO");
			List<UserInfoVO> list = pageVO.getRows();
			while(true){
				++count;
				if(count>=list.size()){
					break;
				}
				UserInfoVO vo = list.get(count-1);
				pageContext.setAttribute("count", count);
				pageContext.setAttribute("vo", vo);
		 --%><%--<etc:list list="${pageVO.rows }" var="vo" varIndex="count"> --%>
		 <%--<c:forEach begin="1" end="10" var="i"> --%><%--for(int i=1;i<=10;i++) --%>
		 <c:set var="a" scope="page" value="0"></c:set>
		 <c:forEach items="${pageVO.rows }" var="vo" varStatus="count">
		  <c:set var="a" scope="page" value="${a+1 }"></c:set>
		<tr>
			<td  align="center"><%--=count --%><%-- ${count.index } ${count.count } --%>${a } </td>
			<td><%--=vo.getUserName() --%>${vo.userName }</td>
			<td><%--=vo.getUserRealName() --%>${vo.userRealName }</td>
			<td>
				<c:if test="${vo.userLock }">
					<a href="#" class="glyphicon glyphicon-remove-sign icon-false"></a>
				</c:if>
				<c:if test="${!vo.userLock }">
					<a href="#" class="glyphicon glyphicon-ok-sign icon-true"></a>
				</c:if>
			<%-- 
			<%if(vo.getUserLock()) {%>
			<a href="#" class="glyphicon glyphicon-remove-sign icon-false"></a>
			<%}else { %>
			<a href="#" class="glyphicon glyphicon-ok-sign icon-true"></a>
			<%} %>--%>
			</td>
			<td>
				<a class="btn btn-success btn-xs" href="#"><i class="glyphicon glyphicon-tags"></i> 修改</a>
				<a onclick="return confirm('您确定要删除这条记录吗？')" class="btn btn-danger btn-xs" href="admin/user-delete?userId=<%--=vo.getUserId()--%>${vo.userId}"><i class="glyphicon glyphicon-remove"></i> 删除</a>
			</td>
		</tr>
		</c:forEach>
		<%--
		doAfterBody
		}
		doEndTag
		 --%><%--</etc:list> --%>
		
</tbody>
	</table>
</div>
<div class="text-center">
<etc:page url="admin/user-list.action?page={page}" page="${pageVO }"/>
<br/>


</div>


</body>
</html>