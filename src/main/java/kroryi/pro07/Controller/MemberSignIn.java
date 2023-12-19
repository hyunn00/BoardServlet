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
                if(userid.equals("admin")) {
                    response.sendRedirect("/admin.jsp");
                }
                System.out.println("로그인 성공");
                response.sendRedirect("/home.jsp");
            }
        }
    }

    public void destroy() {
    }
}