package kroryi.pro07.Controller;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.DTO.Board;
import kroryi.pro07.Service.BoardService;
import kroryi.pro07.Service.BoardServiceImpl;

import javax.naming.NamingException;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
    BoardService boardService;

    public void init() {
        System.out.println("init 호출");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            boardService = new BoardServiceImpl();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        List<Board> boardList = boardService.list();

        request.setAttribute("BoardList", boardList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("board-list.jsp");
        dispatcher.include(request, response);
    }

    public void destroy() {
    }
}