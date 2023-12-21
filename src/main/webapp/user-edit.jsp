<%@ page import="kroryi.pro07.DTO.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Member View</h1>
<form name="userForm">
    아이디 : <input type="text" name="userid" value="${User.userid}" readonly><br>
    이름 : <input type="text" name="username" value="${User.username}"><br>
    비밀번호 : <input type="password" name="password" value="${User.password}" readonly><br>
    <input type="hidden" name="uid" value="${User.uid}"><br>
    <input type="button" value="수정" onclick="update_submit()">
    <input type="button" onclick="delete_submit()" value="삭제">
</form>
<script>
    function update_submit() {
        userForm.method = "post";
        userForm.action = "/member-edit";
        userForm.submit();
    }
    function delete_submit() {
        userForm.method = "get";
        userForm.action = "/member-delete?uid=${User.uid}";
        userForm.submit();
    }
</script>
</body>
</html>
