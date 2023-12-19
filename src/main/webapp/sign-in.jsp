<%--
  Created by IntelliJ IDEA.
  User: hyunn
  Date: 23. 12. 19.
  Time: 오후 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>로그인</h1>
<form action="/sign-in" method="post">
    아이디 : <input type="text" name="userid"><br>
    비밀번호 : <input type="password" name="password"><br>
    <input type="submit" value="로그인">
    <input type="reset" value="초기화">
</form>
</body>
</html>
