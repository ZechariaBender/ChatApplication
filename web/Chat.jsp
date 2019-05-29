/**
*
*/
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
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
            <a href="logout">
                <button type="button" class="btn btn-primary pull-right" onclick="clearMessageBox()">Logout</button>
            </a>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div>
            <h1>Welcome <%=request.getParameter("name")%></h1>
            <form action="chat" method="post" onsubmit=clearMessageBox()>
                <div class="form-group">
                    <h3>Type your message below. No empty messages allowed!</h3>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="message" name="message" autofocus="autofocus"/>
                    <% if(request.getAttribute("error").equals("true")) { %>
                    <div class="alert alert-danger" role="alert">No message!</div>
                    <% } %>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" style="background: blue; color: white">Send</button>
                </div>
            </form>
        </div>
        <div>
            <%
                response.setIntHeader("Refresh", 30);
                ArrayList<String[]> messages = (ArrayList<String[]>) request.getAttribute("messages");
                for (String[] message : messages) {
                    out.println("<p><b>" + message[0]
                            + ":   </b>" + message[1] + "</p>");
                }
            %>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        document.getElementById("message").value = localStorage.getItem("message");
    });
    $(window).on('beforeunload', function() {
        localStorage.setItem("message",document.getElementById("message").value);
    });
    if (window.history.replaceState)
        window.history.replaceState(null, null, window.location.href);
    function clearMessageBox() {
        $(window).on('beforeunload', function() {
            localStorage.setItem("message", "");
        });
    }
</script>
</body>
</html>
