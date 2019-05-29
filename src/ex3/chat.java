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
    private String name;
    private ArrayList<String> messages;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("name") == null) {
            request.getRequestDispatcher("login").forward(request, response);

        } else {

            if (request.getParameter("name") != null) {
                name = request.getParameter("name");
                messages = new ArrayList<>();
            } else {
                String message = request.getParameter("message");
                if (!message.trim().equals("")) {
                    String[] pair = {(String) session.getAttribute("name"), message};
                    messages.add(message);
                    synchronized (this) {
                        //((ArrayList<String>) this.getServletContext().getAttribute("messages")).add(pair);
                    }

                }
                request.setAttribute("messages", messages);
                request.getRequestDispatcher("Chat.jsp?name=" + name).forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("name") == null) {
            response.sendRedirect("login");
        }
        else {
            request.getRequestDispatcher("Chat").include(request, response);
        }
    }
}
