<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zkbnd
  Date: 26-May-19
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<head>
    <title>Chat</title>
</head>
<body>
<header style="background: black; color: white; padding: 30px 30px">
    <div class="row">
        <div class="col-sm-8">
            <h1 style="margin: 0">Welcome to the chatroom</h1>
        </div>
        <div class="col-sm-4">
            <a href="login">
                <button type="button" class="btn btn-primary pull-right">Logout</button>
            </a>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div>
            <h1>Welcome <%=request.getSession().getAttribute("name")%></h1>
            <form action="chat" method="post">
                <div class="form-group">
                    <h3>Type your message below. No empty messages allowed!</h3>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="message"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" style="background: blue; color: white">Send</button>
                </div>
            </form>
        </div>
        <div>
            <%
                ArrayList<String> messages = (ArrayList<String>) request.getAttribute("messages");
                for (String message : messages) {
                    out.println("<p><b>" +request.getParameter("name")
                            + ":   </b>" + message + "</p>");
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
