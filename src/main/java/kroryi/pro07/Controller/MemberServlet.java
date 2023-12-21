package kroryi.pro07.Controller;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.DTO.User;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
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
        List<User> userList = userService.list();

        request.setAttribute("UserList", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}