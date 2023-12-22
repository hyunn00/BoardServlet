<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%= "Hyunn's Board" %></h1>
<%
    String userid = (String) session.getAttribute("userid");
    String username = (String) session.getAttribute("username");
    if(userid != null && username != null) {
%>
<p>Welcome my Board developed by jsp & Servlet!</p>
<p><%=username%>(<%=userid%>)님 반갑습니다.</p>
<a href="/board">Board</a><br>
<a href="/sign-out">Sign Out</a><br>
<%
    } else {
%>
<p>게시판 사용을 위해 로그인이 필요합니다.</p>
<a href="/join-us.jsp">Join Us</a><br>
<a href="/sign-in.jsp">Sign In</a><br>
<%
    }
%>
</body>
</html>
