package ex3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null)
            response.sendRedirect("chat");
        else {
            response.setContentType("text/html");
            String name = request.getParameter("name");
            if (!name.trim().equals("")) {
                request.getSession().setAttribute("name",name);
                response.sendRedirect("chat");
            } else request.getRequestDispatcher("Login.html").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null)
            response.sendRedirect("chat");
        else request.getRequestDispatcher("Login.html").forward(request,response);
    }
}
