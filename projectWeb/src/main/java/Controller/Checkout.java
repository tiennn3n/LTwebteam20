/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DBconnect.DBConnection;
import Model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) request.getSession().getAttribute("auth");
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
        String url = "/OrderPage.jsp";

        if (cart_list != null && auth != null) {
            for (Cart c : cart_list) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();

                //Store order
                Order order = new Order();
                order.setId(c.getId());
                order.setMid(auth.getEmail());
                order.setNumber(c.getNumber());
                order.setDate(formatter.format(date));

                // insert database
                try {
                    OrderDAO orderdao = new OrderDAO(DBConnection.getConnection());
                    orderdao.insertOrder(order);
                    ProductDAO pdao = new ProductDAO(DBConnection.getConnection());
                    pdao.UpdateAfterbuy(order.getId(), order.getNumber());
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IncreDecreNumberServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IncreDecreNumberServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            cart_list.clear();
            
        }else{
            if(auth == null)
                url = "/Login.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
