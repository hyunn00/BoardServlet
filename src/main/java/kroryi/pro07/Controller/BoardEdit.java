package kroryi.pro07.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.pro07.DTO.Board;
import kroryi.pro07.DTO.User;
import kroryi.pro07.Service.BoardService;
import kroryi.pro07.Service.BoardServiceImpl;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/board-edit")
public class BoardEdit extends HttpServlet {
    BoardService boardService;
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            boardService = new BoardServiceImpl();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        int board_no = Integer.parseInt(request.getParameter("board_no"));
        Board board = boardService.select(board_no);
        request.setAttribute("Board", board);

        RequestDispatcher dispatcher = request.getRequestDispatcher("board-edit.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int board_no = Integer.parseInt(request.getParameter("board_no"));
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");

        Board board = new Board();
        board.setBoard_no(board_no);
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);

        int result = boardService.update(board);
        if(result > 0) {
            response.sendRedirect("/board");
        } else {
            request.setAttribute("Board", board);

            RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit.jsp");
            dispatcher.forward(request, response);
        }
    }
    public void destroy() {
    }
}