<%@page import="Model.User"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    String username = null;
    String email =null;
    String pass = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        username = auth.getName();
        email = auth.getEmail();
        pass = auth.getPass();
    }else{
        response.sendRedirect("/Login.jsp");
    }

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/commons/header.jsp" %>
        <title>Update Password</title>
    </head>
    <body>
        <%@include file="/commons/navbar.jsp" %>
        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">Update Password</div>
                <div class="card-body">
                    <form action="UpdatePass?action=confirm" method="post">
                        <div class="text-center">
                            <p style="color:red;"><i>${message}</i></p>
                        </div>
                        <div class="form-group">
                            <label>New password</label> 
                            <input type="password" name="newpass" class="form-control" placeholder="Enter new password" >
                        </div>
                        <div class="form-group">
                            <label>Confirm password</label> 
                            <input type="password" name="confirmpass" class="form-control" placeholder="Confirm">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Update Password</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="/commons/footer.jsp" %>
    </body>
</html>
