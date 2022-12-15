/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DBconnect.DBConnection;
import Model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "CancelOrder", urlPatterns = {"/CancelOrder"})
public class CancelOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/OrderPage.jsp";
        String id = request.getParameter("id");
        User auth = (User) request.getSession().getAttribute("auth");

        if (id != null && auth != null) {
            try {
                OrderDAO orderdao = new OrderDAO(DBConnection.getConnection());
                orderdao.CancelOrder(Integer.parseInt(id));
                url = "/OrderPage.jsp";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CancelOrder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CancelOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
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
