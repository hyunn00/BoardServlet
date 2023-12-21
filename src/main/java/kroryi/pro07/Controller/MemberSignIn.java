package kroryi.pro07.Controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.DTO.User;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;

@WebServlet(name = "signIn", value = "/sign-in")
public class MemberSignIn extends HttpServlet {

    UserService userService = new UserServiceImpl();

    public MemberSignIn() throws NamingException {
    }

    public void init() {
        System.out.println("init 호출");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        User user = userService.select(userid);
        if(user == null) {
            response.sendRedirect("/sign-in.jsp");
        } else {
            if(!password.equals(user.getPassword())) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                response.sendRedirect("/sign-in.jsp");
            } else {
                System.out.println("로그인 성공");
                if(userid.equals("admin")) {
                    System.out.println("관리자 계정 로그인");
                    response.sendRedirect("/admin.jsp");
                } else {
                    System.out.println("일반 회원 로그인");
                    response.sendRedirect("/home.jsp");
                }
            }
        }
    }

    public void destroy() {
    }
}