package kroryi.pro07.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.pro07.Service.BoardService;
import kroryi.pro07.Service.BoardServiceImpl;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/board-delete")
public class BoardDelete extends HttpServlet {

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
        int result = boardService.delete(board_no);
        if(result > 0) {
            System.out.println("게시글 삭제 성공");
            response.sendRedirect("/board");
        } else {
            System.out.println("게시글 삭제 실패");

//            request.setAttribute("Message", "사용자 삭제에 문제가 발생했습니다.\n 다시 확인 부탁드립니다.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("board-edit.jsp");
            dispatcher.forward(request, response);

        }
    }

    public void destroy() {
    }
}