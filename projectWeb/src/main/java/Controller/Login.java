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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user-login")
/**
 *
 * @author USER
 *
 */
public class Login extends HttpServlet {

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
        String url = null;
        String message;
        String email = request.getParameter("login-email");
        String pass = request.getParameter("login-password");
        UserDAO userdao;
        try {
            userdao = new UserDAO(DBConnection.getConnection());
            User user1 = userdao.userLogin(email, pass);

            if (user1 != null) {
                request.getSession().setAttribute("auth", user1);
                request.setAttribute("email", email);
                request.setAttribute("name", user1.getName());
                url = "/Home.jsp";
                message = null;
            } else {
                url = "/Login.jsp";
                message = "Wrong email or password";
                request.setAttribute("message",message);
                
               
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

//                response.setContentType("text/html;charset=UTF-8");
//                try(PrintWriter out = response.getWriter())
//                {
//                    String email = request.getParameter("login-email");
//                    String pass = request.getParameter("login-password");
//                    //out.print(email + pass);
//                    String message ;
//                    try {
//                        UserDAO userdao = new UserDAO(DBConnection.getConnection());
//                        User user = userdao.userLogin(email, pass);
//                        if (user != null){
//                            //out.print("Đăng nhập thành công");
//                            request.getSession().setAttribute("auth",user);
//                            //out.print(user);
//                            url = "Home.jsp";
//                            //response.sendRedirect("Home.jsp");
//                        }
//                        else {
//                            message = "Tài khoản hoặc mật khẩu không chính xác ";
//                            request.setAttribute(message,message);
//                            //response.sendRedirect("Login.jsp");
//                            //out.print(message);
//                            url = "Login.jsp"
//                            
//                        }
//                    } catch (ClassNotFoundException ex) {
//                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
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

    private void print(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
