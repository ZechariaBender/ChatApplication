package ex3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        if (!name.trim().equals("")) {
            request.getRequestDispatcher("chat").forward(request,response);
        } else {
            request.getRequestDispatcher("Login.html").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            String name = (String) session.getAttribute("name");
//            response.getWriter().print("Hello, " + name + " Welcome to your Profile");
//            request.getRequestDispatcher("chat").forward(request,response);
//        } else {
            request.getRequestDispatcher("Login.html").forward(request,response);
//        }
    }
}
