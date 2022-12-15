/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBconnect.DBConnection;
import Model.Order;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class OrderDAO {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDAO(Connection con) {
        this.con = con;
    }

    public void insertOrder(Order model) throws SQLException {

        query = "insert into YeuCau(pid, mid, pnumber, odate) values (?,?,?,?)";

        pst = this.con.prepareStatement(query);
        pst.setInt(1, model.getId());
        pst.setString(2, model.getMid());
        pst.setInt(3, model.getNumber());
        pst.setString(4, model.getDate());
        pst.executeUpdate();

    }

    public List<Order> UserOrder(String email) throws SQLException, ClassNotFoundException {
        List<Order> list_order = new ArrayList<>();
        query = "select * from YeuCau where mid = ? order by oid desc";
        pst = this.con.prepareStatement(query);
        pst.setString(1, email);
        rs = pst.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            ProductDAO productdao = new ProductDAO(DBConnection.getConnection());
            int pId = rs.getInt("pid");

            Product product = productdao.getProductfromId(pId);
            order.setOrderId(rs.getInt("oid"));
            order.setId(pId);
            order.setName(product.getName());
            order.setCategory(product.getCategory());
            order.setPrice(product.getPrice() * rs.getInt("pnumber"));
            order.setNumber(rs.getInt("pnumber"));
            order.setDate(rs.getString("odate"));
            list_order.add(order);

        }
        return list_order;
        
       
    }
    public void CancelOrder(int oid) throws SQLException{
            query = "delete from YeuCau where oid=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1,oid);
            pst.executeUpdate();
        }
}