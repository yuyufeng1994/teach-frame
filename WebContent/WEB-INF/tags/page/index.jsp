<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="pagination">
<c:forEach items="${list }" var="p">
  <li<c:if test="${p.isCurrent }"> class="active"</c:if>><a data-page=${p.page } href="${p.url }">${p.text }</a></li>
</c:forEach>
</ul>
