package ex3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Chat", urlPatterns = "/chat")
public class Chat extends HttpServlet {

    @Override
    public void init() {
        this.getServletContext().setAttribute("messages", new ArrayList<String[]>());
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("name") == null)
            response.sendRedirect("login");
        else {
            ArrayList<String[]> messages = (ArrayList<String[]>) this.getServletContext().getAttribute("messages");
            String name = (String) session.getAttribute("name");
            String message = request.getParameter("message");
            if (message != null && !message.trim().equals("")) {
                String[] pair = {name, message};
                messages.add(pair);
            }
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("Chat.jsp?name=" + name).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("name") == null)
            response.sendRedirect("login");
        else doPost(request,response);
    }
}
