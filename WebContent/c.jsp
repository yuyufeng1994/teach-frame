<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%!
	private static int count = 0;
%><%
long m = 0;
Random random = new Random();
m = random.nextInt(20)*100;
synchronized(this){
	++count;
	out.print(count+":"+m+"<br/>");
}
Thread.sleep(m);

%>