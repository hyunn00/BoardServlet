<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Begin Wrapper -->
<div id="wrapper">

    <!-- Begin Header -->
    <div id="header">
        <b><%= "Hyunn's Board" %></b><br>

        <%
            String userid = (String) session.getAttribute("userid");
            String username = (String) session.getAttribute("username");
            if(userid != null && username != null) {
        %>
        <p><%=username%>(<%=userid%>)님 반갑습니다.</p>
        <%
            if(userid.equals("admin")) {
        %>
        <b><%= "Admin page" %></b>
        이 페이지는 관리자 계정으로만 접속할 수 있습니다.
        <a href="/sign-out">로그아웃</a>
        <%
        } else {
        %>
        jsp & Servlet으로 개발한 게시판입니다!
        <a href="/sign-out">로그아웃</a>
        <%
            }
        } else {
        %>
        게시판 사용을 위해 로그인이 필요합니다.
        <a href="<%= request.getContextPath() %>/sign-in.jsp">로그인</a>
        <%
            }
        %>
        <br>
        <a href="<%= request.getContextPath() %>/join-us.jsp">회원가입</a>
        <a href="<%= request.getContextPath() %>/member">회원리스트</a>
        <a href="<%= request.getContextPath() %>/board">게시판리스트</a>

    </div>
    <!-- End Header -->