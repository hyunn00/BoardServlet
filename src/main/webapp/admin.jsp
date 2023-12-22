<%--
  Created by IntelliJ IDEA.
  User: hyunn
  Date: 23. 12. 19.
  Time: 오후 4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%= "Admin page" %></h1>
<p>This page can only be accessed with an admin account.</p>
<%
    String userid = (String) session.getAttribute("userid");
    String username = (String) session.getAttribute("username");
    if(userid != null && username != null) {
%>
<p><%=username%>(<%=userid%>)님 반갑습니다.</p>
<%
} else {
%>
<p>세션이 없습니다.</p>
<a href="/sign-in.jsp">Sign In</a><br>
<%
    }
%>
<a href="/member">Member List</a><br>
<a href="/sign-out">Sign Out</a><br>
</body>
</html>
