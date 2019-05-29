<!DOCTYPE html>
<html lang="en" contentType="text/html;charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<head>
    <title>Login</title>
</head>
<body style="background-color: #f5f5f5">
<br><br><br><br><br><br><br><br>
<div class="container" align="center">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h1>Join the chatroom</h1>
            <form action="login" method="post">
                <div class="form-group">
                    <h3>Enter your name</h3>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder=" name" autofocus="autofocus"/>
                    <% if(request.getAttribute("error").equals("true")) { %>
                    <div class="alert alert-danger" role="alert">Missing name!</div>
                    <% } %>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" style="background: blue; color: white">Enter</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>