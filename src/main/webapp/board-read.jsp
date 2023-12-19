<%--
  Created by IntelliJ IDEA.
  User: hyunn
  Date: 23. 12. 19.
  Time: 오후 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Read Post</h1>
<form action="/board-read" method="post">
    Title : <input type="text" name="title" readonly><br>
    Writer : <input type="text" name="writer" readonly><br>
    Content : <textarea name="content" readonly></textarea><br>
    <input type="button" value="수정">
    <input type="button" value="삭제"><br>
</form>
</body>
</html>
