/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
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

@WebServlet("/user-register")
/**
 *
 * @author USER
 */
public class RegisterServlet extends HttpServlet {

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
        String url = "/Login.jsp";
        String message = null;
        String email = request.getParameter("register-email");
        String name = request.getParameter("register-name");
        String phone = request.getParameter("register-phone");
        String pass = request.getParameter("register-password");
        
        // Store
        User user = new User(email,name,phone,pass); 
//        try {
//            UserDAO userdao = new UserDAO(DBConnection.getConnection());
//            userdao.InsertUser(email, name, phone, pass );
//            message = "Your account have been created,you can login now";
//            request.setAttribute("message", message);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            UserDAO userdao = new UserDAO(DBConnection.getConnection());
            if (!userdao.Foundemail(email)) {
                userdao.InsertUser(email, name, phone, pass);
                message = "Your account have been created,you can login now";
                request.setAttribute("message", message);
            }else{
                message = "This email have already existed.";
                url = "/RegisterPage.jsp";
            }
            request.setAttribute("message", message);
            request.setAttribute("user",user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
