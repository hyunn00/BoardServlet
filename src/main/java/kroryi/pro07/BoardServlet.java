package kroryi.pro07;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.DTO.Board;
import kroryi.pro07.Service.BoardService;
import kroryi.pro07.Service.BoardServiceImpl;

import javax.naming.NamingException;

@WebServlet(name = "board", value = "/board")
public class BoardServlet extends HttpServlet {
    BoardService boardService = new BoardServiceImpl();

    public BoardServlet() throws NamingException {
    }

    public void init() {
        System.out.println("init 호출");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        List<Board> boardList = new ArrayList<>();
        boardList = boardService.list();

        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>Board List</h1>");
        out.println("<a href='/board-write.jsp'>Write Post</a>");
        out.println("<table border=1><tr align='center' bgcolor='lightgreen'>");
        out.println("<td>번호</td><td>제목</td><td>작성자</td><td>작성일자</td><td>수정일자</td></tr>");

        for(Board board : boardList) {
            int no = board.getBoard_no();
            String title = board.getTitle();
            String writer = board.getWriter();
            String content = board.getContent();
            String regDate = board.getReg_date();
            String updDate = board.getUpd_date();

            out.printf("<tr><td>%d</td><td><a href='/board-read.jsp'>%s</a></td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    no, title, writer, regDate, updDate);
        }
        out.println("</table></body></html>");
    }

    public void destroy() {
    }
}