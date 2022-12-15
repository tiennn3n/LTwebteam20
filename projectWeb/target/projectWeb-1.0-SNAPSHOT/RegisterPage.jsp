
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="commons/header.jsp"%>
        <title>Register Page</title>
    </head>
    <body>

        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">Register</div>
                <div class="card-body">
                    <form action="user-register" method="post">
                        <div class="form-group">
                            <label>Email address</label> 
                            <input type="email" name="register-email" class="form-control" placeholder="Enter email" value="${user.email}">
                        </div>
                        <div class="">
                            <p style="color:red;"><i>${message}</i></p>
                        </div>
                        <div class="form-group">
                            <label>Full name</label> 
                            <input type="text" name="register-name" class="form-control" placeholder="Enter your name" value="${user.name}">
                        </div>
                        <div class="form-group">
                            <label>Phone number</label> 
                            <input type="text" name="register-phone" class="form-control" placeholder="Enter phone number" value="${user.phone}">
                        </div>
                        <div class="form-group">
                            <label>Password</label> 
                            <input type="password" name="register-password" class="form-control" placeholder="Password" value="${user.pass}">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Sign up</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="commons/footer.jsp"%>
    </body>
</html>