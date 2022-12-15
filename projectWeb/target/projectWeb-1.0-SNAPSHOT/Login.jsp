

<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    String username = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        username = auth.getName();
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/commons/header.jsp" %>
        <title>Login</title>
    </head>
    <body>
        <%@include file="/commons/navbar.jsp" %>


        <div class="container"><br><br>
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">User Login</div>
                <div class="card-body">             
                    <form action="user-login" method="post">
                        <div class="form-group">
                            <label>Email address</label> 
                            <input type="email" name="login-email" class="form-control" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label>Password</label> 
                            <input type="password" name="login-password" class="form-control" placeholder="Password">
                        </div>
                        <div class="text-center">
                            <p style="color:red;"><i>${message}</i></p>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div><br>
                        <div class="text-center">
                            <p style="color:blue;">You don't have account ?</p> 
                            <p style="color:blue;">Create your account here</p> 
                        </div>
                        <div class="text-center">
                            <a href="RegisterPage.jsp" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">Register</a>
                        </div>
                        


                    </form>

                </div>
            </div>
        </div>
        <%@include file="/commons/footer.jsp" %>
    </body>
</html>
