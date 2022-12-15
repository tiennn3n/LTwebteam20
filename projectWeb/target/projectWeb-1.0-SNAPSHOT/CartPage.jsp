

<%@page import="DBconnect.DBConnection"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Cart"%>
<%@page import="Model.User"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    String username = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        username = auth.getName();
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart_list");
    List<Cart> cartProduct = null;
    int Total = 0;
    if (cart_list != null) {
        ProductDAO productdao = new ProductDAO(DBConnection.getConnection());
        cartProduct = productdao.getCartProduct(cart_list);
        //request.setAttribute("cart_list", cart_list);

        Total = productdao.getTotalprice(cart_list);

    }
    //request.setAttribute("total",Total);


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="commons/header.jsp" %>
        <title>Cart</title>
    </head>
    <body>
        <%@include file="commons/navbar.jsp" %>

        <div class="container text-center"><br>
            <div class="d-flex py-5">
                <h3>Total Price: $ <%=Total%> </h3>
                <a class="mx-3 btn btn-primary " href="Checkout" >Check out</a>
            </div>
            <table class="table table-loght ">
                <thead>
                    <tr>
                        <td scope="col">Name</td>
                        <td scope="col">Category</td>
                        <td scope="col">Price</td>
                        <td scope="col">Buy Now</td>
                        <td scope="col">Cancel</td>
                    </tr>
                </thead>
                <tbody>

                    <% if (cart_list != null) {
                            for (Cart c : cartProduct) {
                    %>
                    <tr>
                        <td><%=c.getName()%></td>
                        <td><%=c.getCategory()%></td>
                        <td>$<%=c.getPrice()%></td>

                        <td>
                            <form action="Order" method="post" class="form-inline">
                                <input type="hidden" name="id" value="<%=c.getId()%>" class="form input">
                                <div class="form-group d-flex justify-content-between w-20">
                                    <a class="btn btn-sm btn-decre" href="IncreDecreNumberServlet?action=decre&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>

                                    <input type="text" name="number" class="form-control w-25" value="<%=c.getNumber()%>" readonly>
                                    <a class="btn btn-sm btn-incre" href="IncreDecreNumberServlet?action=incre&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
                                </div>
                                    <button type="submit" class="btn btn-primary btn-sm">Buy Now</button>
                            </form>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-danger " href="IncreDecreNumberServlet?action=remove&id=<%=c.getId()%>">Remove</a>
                        </td>
                    </tr>
                    <%}
                        }
                    %>


                </tbody>
            </table><br>
        </div>
        <br><!-- comment -->
        <br>
        <br><!-- comment -->
        <br>





        <%@include file="commons/footer.jsp" %>

    </body>
</html>
