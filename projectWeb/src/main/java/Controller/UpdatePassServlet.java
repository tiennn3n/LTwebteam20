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

/**
 *
 * @author USER
 */
@WebServlet(name = "UpdatePassServlet", urlPatterns = {"/UpdatePass"})
public class UpdatePassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User auth = (User) request.getSession().getAttribute("auth");
        String action = request.getParameter("action");
        String url = null;
        String message = null;

        if (action.equals("changepass") && auth != null) {
            url = "/ChangePassPage.jsp";

        }
        if (action.equals("confirm") && auth != null) {

            url = "/Home.jsp";
            String newpass = request.getParameter("newpass");
            String confirmpass = request.getParameter("confirmpass");
            if (newpass.equals(confirmpass)) {

                try {
                    UserDAO userdao = new UserDAO(DBConnection.getConnection());
                    userdao.Updatepass(auth.getEmail(), newpass);
                    url = "/Home.jsp";
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UpdatePassServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdatePassServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                message = "Confirm password didn't valid ";
                url = "/ChangePassPage.jsp";
                //request.setAttribute("message", message);
            }
            request.setAttribute("message", message);
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
