package kroryi.pro07.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;

@WebServlet(name = "pro07", value = "/member-delete")
public class MemberDelete extends HttpServlet {

    UserService userService;
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            userService = new UserServiceImpl();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int result = userService.delete(uid);
        if(result > 0) {
            System.out.println("회원 삭제 성공");
            response.sendRedirect("/member");
        } else {
            System.out.println("회원 삭제 실패");

//            request.setAttribute("Message", "사용자 삭제에 문제가 발생했습니다.\n 다시 확인 부탁드립니다.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit.jsp");
            dispatcher.forward(request, response);

        }
    }

    public void destroy() {
    }
}