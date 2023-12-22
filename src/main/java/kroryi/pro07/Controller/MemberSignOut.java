package kroryi.pro07.Controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/sign-out")
public class MemberSignOut extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        if(session.getAttribute("userid") != null) {
            session.removeAttribute("userid");
            session.removeAttribute("username");
        }
        response.sendRedirect("/index.jsp");
    }

    public void destroy() {
    }
}