<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <link rel="stylesheet" type="text/css" href="views/css/stylesheet.css"/>
</head>
<body>
<%@ include file="views/includes/Header.jsp"%>
<div id="content">
  <%@ include file="views/includes/Sidebar.jsp"%>
  <jsp:include page="${contentPage}"></jsp:include>
</div>
<%@ include file="views/includes/Footer.jsp"%>
</body>
</html>