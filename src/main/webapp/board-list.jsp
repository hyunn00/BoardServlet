
<%@ page import="java.util.List" %>
<%@ page import="kroryi.pro07.DTO.Board" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Board List</h1>
<a href="/board-write.jsp">Write Post</a>
<table border="1">
    <tr align="center" bgcolor="lightgreen">
        <td>번호</td><td>제목</td><td>작성자</td><td>작성일자</td><td>수정일자</td>
    </tr>
    <%
        List<Board> boards = (List<Board>) request.getAttribute("BoardList");
        if(!boards.isEmpty()) {
            for(Board board : boards) {
    %>
    <tr>
        <td><%= board.getBoard_no()%></td>
        <td><a href="/board-edit?board_no=<%= board.getBoard_no()%>"><%= board.getTitle()%></a></td>
        <td><%= board.getWriter()%></td>
        <td><%= board.getReg_date()%></td>
        <td><%= board.getUpd_date()%></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
