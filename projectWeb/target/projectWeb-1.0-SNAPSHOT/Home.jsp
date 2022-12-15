
<%@page import="java.util.*"%>
<%@page import="Model.Product"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="Model.User"%>
<%@page import="DBconnect.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    String username = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        username = auth.getName();
    }
    

    ProductDAO productdao = new ProductDAO(DBConnection.getConnection());
    List<Product> products = productdao.getAllProduct();
    List<Product> newproducts = productdao.findNewProduct();
    Product hp = productdao.findHotproduct();
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="commons/header.jsp" %>
        <link rel="stylesheet" href="Design/style1.css">
        <title>Shop đồng hồ uy tín số 2 VN</title>
    </head>
    <body>
        
         <%@include file="commons/navbar.jsp" %><br><br><br>

        <div class="container text-center border border-dark">
            <div class="card-header my-mx-3 text-center text-light bg-dark"><h1>HOT PRODUCT</h1></div>
            <h3 class="info text-center">THIS PRODUCT SO SICK, BUY NOW AS SOON AS YOU CAN</h3>
            <h3 class="info text-center">THE BEST CHOICE, MASTERPIECE, THE ONE IN THE WORLD</h3>
            <div class="hotproduct-div mx-3 text-light justify-content-between">
              
                <div class="picture w-100 d-flex bg-dark" style="width: 25rem;"><br>
                <span>
                        <img class="card-img" src="Image/<%= hp.getImage()%>" alt="Card image">
                </span>    
                    
                    <div class="card-body bg-dark" >
                        
                        <h2 class="card-title border border-light"><%= hp.getName()%>  </h2><h2 style="color: red">Hot</h2><br>
                        <h3 class="price ">Price:$<%= hp.getPrice()%></h3>
                        <h3 class="category">Category: <%= hp.getCategory()%></h3>
                        <h3 class="sold">With <%= hp.getSold()%>  have been sold. </h3>
                        <h3 class="quantity">Only <%= hp.getQuantity()%> remand.</h3>
                        <div class="mt-3 justify-content-between">
                            <a href="AddtoCartServlet?id=<%=hp.getId()%>" class="btn btn-light">Add to Cart</a>
                            <a href="Order?id=<%=hp.getId()%>&number=1" class="btn btn-primary">Buy Now</a>
                        </div>
                    </div>
                </div><br>

            </div>
        </div><br>

        <div class="container text-center bg-dark">
            <div class="card-header my-3 text-center text-light"><h1>ALL PRODUCTS</h1></div>
            <div class="row">
                <% if (!products.isEmpty()) {
                            for (Product p : products) {%>
                <div class="col-3 my-3 ">
                    <div class="card w-100 " style="width: 20rem;">
                        
                        <img class="card-img-top" src="Image/<%= p.getImage()%>" alt="Card image">
                        
                        <div class="card-body border-top border-dark" >
                            
                            <h5 class="card-title"><%= p.getName()%></h5>
                            <h6 class="price">Price: $<%= p.getPrice()%></h6>
                            <h6 class="category">Category: <%= p.getCategory()%></h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="AddtoCartServlet?id=<%=p.getId()%>" class="btn btn-dark">Add to Cart</a>
                                <a href="Order?id=<%=p.getId()%>&number=1" class="btn btn-primary">Buy Now</a>
                            </div>
            
                        </div>
                    </div>
                </div>
                <%}
                    }
                %>

            </div>
        </div><br>
        <div class="container border text-center">
            <div class="card-header my-3 text-dark"><h1>NEW PRODUCT</h1></div>
            <div class="row">
                <% if (!newproducts.isEmpty()) {
                            for (Product p : newproducts) {%>
                <div class="col-md-3 my-3 mx-auto">
                    <div class="card w-100 bg-dark text-light" style="width: 22rem;">
                       
                            <img class="card-img-top" src="Image/<%= p.getImage()%>" alt="Card image">
                       
                        <div class="card-body border-top border-light" >
                            <h5 class="new" style="color:yellow">NEW</h5>
                            <h5 class="card-title"><%= p.getName()%></h5>
                            <h6 class="price">Price: $<%= p.getPrice()%></h6>
                            <h6 class="category">Category: <%= p.getCategory()%></h6>
                            <div class="mt-5 d-flex justify-content-between">
                                <a href="AddtoCartServlet?id=<%=p.getId()%>" class="btn btn-light">Add to Cart</a>
                                <a href="Order?id=<%=p.getId()%>&number=1" class="btn btn-primary">Buy Now</a>
                            </div>
                        </div>
                    </div>
                </div>
                <%}
                    }
                %>
            </div>
        </div>
            
        <%@include file="commons/footer.jsp" %>
        
    </body>
</html>
