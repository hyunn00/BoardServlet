package kroryi.pro07.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.pro07.DTO.Board;
import kroryi.pro07.Service.BoardService;
import kroryi.pro07.Service.BoardServiceImpl;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name="board-write", value="/board-write")
public class BoardWrite extends HttpServlet {
    BoardService boardService = new BoardServiceImpl();

    public BoardWrite() throws NamingException {
    }

    public void init() {
        System.out.println("init 호출");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        Board board = new Board();
        board.setTitle(request.getParameter("title"));
        board.setWriter(request.getParameter("writer"));
        board.setContent(request.getParameter("content"));

        if(boardService.insert(board) > 0) {
            System.out.println("글 등록 성공");
            response.sendRedirect("/board");
        } else {
            System.out.println("글 등록 실패");
        }
    }

    public void destroy() {
    }
}
