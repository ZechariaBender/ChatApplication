package ex3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        //check if the user is login, take him to the chat page
        if (session != null && session.getAttribute("name") != null)
            response.sendRedirect("chat");
        else {//a new user
            response.setContentType("text/html");
            String name = request.getParameter("name");
            if (!name.trim().equals("")) {//not an empty name
                request.getSession().setAttribute("name",name);
                response.sendRedirect("chat");
            } else
            {
                request.setAttribute("error", "true");
                request.getRequestDispatcher("Login.jsp").forward(request,response);
            }
        }
    }

    /**handel the jsp page
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null)
            response.sendRedirect("chat");
        else {
                request.setAttribute("error", "false");
                request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }
}
