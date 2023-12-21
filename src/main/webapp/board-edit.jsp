<%@ page import="kroryi.pro07.DTO.Board" %><%--
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
<h1>Modify Post</h1>
<form name="boardForm">
    Title : <input type="text" name="title" value="${Board.title}"><br>
    Writer : <input type="text" name="writer" value="${Board.writer}"><br>
    Content : <textarea name="content" >${Board.content}</textarea><br>
    <input type="hidden" name="board_no" value="${Board.board_no}">
    <input type="button" value="수정" onclick="update_submit()">
    <input type="button" value="삭제" onclick="delete_submit()">
</form>
<script>
    function update_submit() {
        boardForm.method = "post";
        boardForm.action = "/board-edit";
        boardForm.submit();
    }
    function delete_submit() {
        boardForm.method = "get";
        boardForm.action = "/board-delete?board_no=${Board.board_no}";
        boardForm.submit();
    }
</script>
</body>
</html>
