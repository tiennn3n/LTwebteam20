/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "AddtoCartServlet", urlPatterns = {"/AddtoCartServlet"})
public class AddtoCartServlet extends HttpServlet {

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
        String url = null;
        String message = null;
        ArrayList<Cart> cartList = new ArrayList<>();

        int id = Integer.parseInt(request.getParameter("id"));
        Cart cart = new Cart();
        cart.setId(id);
        cart.setNumber(1);

        HttpSession session = request.getSession();
        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart_list");

        if (cart_list == null) {
            cartList.add(cart);
            session.setAttribute("cart_list", cartList);
            message = "Add successfully";
            url = "/Home.jsp";
        } else {
            cartList = cart_list;
            boolean exist = false;

            for (Cart c : cart_list) {
                if (c.getId() == id) {
                    exist = true;
                    message = "This item already exist";
                    url = "/Home.jsp";
                    
                }
            }
            if (!exist){
                cartList.add(cart);
                url = "/Home.jsp";
                message = "Thêm thành công";
            }
        }
        
        request.setAttribute("message", message);
        
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
