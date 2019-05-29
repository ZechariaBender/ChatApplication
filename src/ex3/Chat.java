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

/**
 *
 */
@WebServlet(name = "Chat", urlPatterns = "/chat")
public class Chat extends HttpServlet {

    @Override
    public void init() {
        this.getServletContext().setAttribute("messages", new ArrayList<String[]>());
    }

    /**handle the chat page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("name") == null)//not a registar user, go to login
            response.sendRedirect("login");
        else {
            ArrayList<String[]> messages = (ArrayList<String[]>) this.getServletContext().getAttribute("messages");
            String name = (String) session.getAttribute("name");
            String message = request.getParameter("message");
            request.setAttribute("error", "false");
            if (message != null) {
                if (!message.trim().equals("")) {
                    String[] pair = {name, message};
                    synchronized (this) {//lock the array from two servlets
                        messages.add(pair);
                    }
                } else
                    request.setAttribute("error", "true");//empty message
            }

            request.setAttribute("messages", messages);
            request.getRequestDispatcher("Chat.jsp?name=" + name).forward(request, response);
        }
    }

    /**handle the jsp page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("name") == null)
            response.sendRedirect("login");
        else
        {
            //request.setAttribute("error", "false");
            doPost(request,response);
        }
    }
}
