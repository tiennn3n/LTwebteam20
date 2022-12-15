/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.*;
import DAO.*;
import DBconnect.DBConnection;
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
@WebServlet(name = "IncreDecreNumberServlet", urlPatterns = {"/IncreDecreNumberServlet"})
public class IncreDecreNumberServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        //User auth = (User) request.getSession().getAttribute("auth");
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
        String url = "/CartPage.jsp";

        if (action != null) {
            if (action.equals("incre")) {
                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        int number = c.getNumber();
                        number++;
                        c.setNumber(number); // cập nhật số lượng lên 1
                    }

                }
            }
            if (action.equals("decre")) {
                for (Cart c : cart_list) {
                    if (c.getId() == id && c.getNumber() > 1) {
                        int number = c.getNumber();
                        number--;
                        c.setNumber(number); // cập nhật số lượng lên 1
                    }

                }
            }
            if (action.equals("remove") && cart_list != null) {
                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        cart_list.remove(cart_list.indexOf(c));
                        break;
                    }
                }
            }
//            if (action.equals("checkout") && cart_list != null && auth != null) {
//                for (Cart c : cart_list) {
//
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date = new Date();
//
//                    //Store order
//                    Order order = new Order();
//                    order.setId(c.getId());
//                    order.setMid(auth.getEmail());
//                    order.setNumber(c.getNumber());
//                    order.setDate(formatter.format(date));
//
//                    // insert database
//                    OrderDAO orderdao;
//                    try {
//                        orderdao = new OrderDAO(DBConnection.getConnection());
//                        orderdao.insertOrder(order);
//                    } catch (ClassNotFoundException ex) {
//                        Logger.getLogger(IncreDecreNumberServlet.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (SQLException ex) {
//                        Logger.getLogger(IncreDecreNumberServlet.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }

        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
