package kroryi.pro07.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kroryi.pro07.DTO.User;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/member-edit")
public class MemberEdit extends HttpServlet {
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
        String userid = request.getParameter("userid");
        User user = userService.select(userid);
        System.out.println("11111->" +user);
        request.setAttribute("User", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        User user = new User();
        user.setUid(uid);
        user.setUserid(userid);
        user.setUsername(username);

        int result = userService.update(user);
        if(result > 0) {
            response.sendRedirect("/member");
        } else {
            request.setAttribute("User", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit.jsp");
            dispatcher.forward(request, response);
        }
    }
    public void destroy() {
    }
}