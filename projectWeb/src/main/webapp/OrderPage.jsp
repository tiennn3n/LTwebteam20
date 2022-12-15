<%@page import="DBconnect.DBConnection"%>
<%@page import="java.util.List"%>
<%@page import="Model.*"%>
<%@page import="DAO.*"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    String username = null;
    List<Order> list_order = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        username = auth.getName();
        
        list_order = new OrderDAO(DBConnection.getConnection()).UserOrder(auth.getEmail());
    }else{
        response.sendRedirect("/Login.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="commons/header.jsp" %>
        <title>Order</title>
    </head>
    <body>
        <%@include file="commons/navbar.jsp" %>
        
        
        <div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(list_order != null){
				for(Order o:list_order){%>
					<tr>
						<td><%=o.getDate() %></td>
						<td><%=o.getName() %></td>
						<td><%=o.getCategory() %></td>
						<td><%=o.getNumber() %></td>
						<td>$<%=o.getPrice()%></td>
						<td><a class="btn btn-sm btn-danger" href="#">Cancel Order</a></td>
					</tr>
				<%}
			}
			%>
			
			</tbody>
		</table>
	</div>
        
        
        <%@include file="commons/footer.jsp" %>
    </body>
</html>
