package kroryi.pro07.Controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kroryi.pro07.DTO.User;
import kroryi.pro07.Service.UserService;
import kroryi.pro07.Service.UserServiceImpl;

import javax.naming.NamingException;

@WebServlet("/sign-in")
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
            HttpSession session = request.getSession();
            if(!password.equals(user.getPassword())) {
                session.invalidate();
                response.sendRedirect("/sign-in.jsp");
            } else {
                System.out.println("로그인 성공");

                session = request.getSession();
                if(session.isNew() || session.getAttribute("userid") == null) {
                    session.setAttribute("userid", user.getUserid());
                    session.setAttribute("username", user.getUsername());
                    if(session.isNew()) {
                        System.out.println("create new session");
                    }

                } else {
                    System.out.println("현재 로그인 상태");
                    String s_userid = (String) session.getAttribute("userid");
                    System.out.println("세션 저장된 값 : "+s_userid);
                }
                response.sendRedirect("/index.jsp");
            }
        }
    }

    public void destroy() {
    }
}