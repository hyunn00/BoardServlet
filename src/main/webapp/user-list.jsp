<%@ page import="kroryi.pro07.DTO.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Member List</h1>
<table border="1">
    <tr align="center" bgcolor="lightgreen">
        <td>번호</td><td>아이디</td><td>이름</td><td>삭제</td>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("UserList");
        if(!users.isEmpty()) {
            for(User user : users) {
    %>
    <tr>
        <td><%= user.getUid()%></td>
        <td><a href="/member-edit?uid=<%= user.getUid()%>"><%= user.getUserid()%></a></td>
        <td><%= user.getUsername()%></td>
        <td><a href="/member-delete?uid=<%= user.getUid()%>">삭제</a></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
