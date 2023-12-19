package kroryi.pro07.Controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.DTO.User;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;

@WebServlet(name="memberJoin", value="/member/join")
public class MemberJoinUs extends HttpServlet {
    UserService userService = new UserServiceImpl();

    public MemberJoinUs() throws NamingException {
    }

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        User user = new User();
        user.setUserid(request.getParameter("userid"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        if(userService.insert(user) > 0) {
            System.out.println("회원 가입 성공");
        } else {
            System.out.println("회원 가입 실패");
        }
    }

    public void destroy() {
    }
}