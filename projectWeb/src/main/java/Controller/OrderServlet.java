/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DBconnect.DBConnection;
import Model.Cart;
import Model.Order;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/Order"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/OrderPage.jsp";
        User auth = (User) request.getSession().getAttribute("auth");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        if (auth != null) {

            String productId = request.getParameter("id");
            int productnumber = Integer.parseInt(request.getParameter("number"));

//            Order orderModel = new Order();
//            orderModel.setId(Integer.parseInt(productId));
//            orderModel.setMid(user.getEmail());
//            orderModel.setNumber(productnumber);
//            orderModel.setDate(formatter.format(date));
            try {
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setMid(auth.getEmail());
                orderModel.setNumber(productnumber);
                orderModel.setDate(formatter.format(date));

                OrderDAO orderdao = new OrderDAO(DBConnection.getConnection());
                orderdao.insertOrder(orderModel);

                ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
                if (cart_list != null) {
                    for (Cart c : cart_list) {
                        if (c.getId() == Integer.parseInt(productId)) {
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                    url = "/OrderPage.jsp";
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            url = ("/Login.jsp");
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
