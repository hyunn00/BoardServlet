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
        <td>번호</td><td>아이디</td><td>이름</td>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("UserList");
        if(!users.isEmpty()) {
            for(User user : users) {
    %>
    <tr>
        <td><%= user.getUid()%></td>
        <td><a href="/member-edit?userid=<%= user.getUserid()%>"><%= user.getUserid()%></a></td>
        <td><%= user.getUsername()%></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
