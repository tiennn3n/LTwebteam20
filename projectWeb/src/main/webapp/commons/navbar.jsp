
<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <div class="container">
        <a class="navbar-brand" href="Home.jsp">E-Shop Watch</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="Home.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="CartPage.jsp">Cart <span class="badge badge-danger">${cart_list.size()}</span> </a></li>
                    <%
                        if (auth != null) {
                    %>
                <li class="nav-item"><a class="nav-link" href="OrderPage.jsp">Orders</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%=username%>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">${email}</a>
                        <a class="dropdown-item" href="UpdatePass?action=changepass">Change password</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="Logout">Logout</a>
                    </div>
                </li>
                <%
                } else {
                %>
                <li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
                    <%
                        }
                    %>
            </ul>
        </div>
    </div>
</nav>