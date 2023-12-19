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
<h1>Write Post</h1>
<form action="/board-write" method="post">
    Title : <input type="text" name="title"><br>
    Writer : <input type="text" name="writer"><br>
    Content : <textarea name="content"></textarea><br>
    <input type="submit" value="글등록">
    <input type="reset" value="초기화"><br>
</form>
</body>
</html>
