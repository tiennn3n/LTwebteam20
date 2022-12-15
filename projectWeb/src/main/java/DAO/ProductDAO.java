/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author USER
 */
public class ProductDAO {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDAO(Connection con) {
        this.con = con;
    }

    public List<Product> getAllProduct() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        query = "select * from SanPham";
        pst = this.con.prepareStatement(query);
        rs = pst.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getInt("price"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setSold(rs.getInt("sold"));

            products.add(product);
        }
        return products;
    }

    public List<Product> findNewProduct() throws SQLException {
        // khoi tao doi tuong products
        List<Product> products = new ArrayList<Product>();
        // tao cau sql
        query = "select top(3) * from SanPham Order By id DESC";
        pst = this.con.prepareStatement(query);
        rs = pst.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getInt("price"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setSold(rs.getInt("sold"));

            products.add(product);
        }
        return products;
    }

    public Product findHotproduct() throws SQLException {
        Product product = new Product();
        query = "select top(1) * from SanPham Order by sold DESC";
        pst = this.con.prepareStatement(query);
        rs = pst.executeQuery();
        while (rs.next()) {
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getInt("price"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));
            product.setSold(rs.getInt("sold"));
        }
        return product;
    }

    public List<Cart> getCartProduct(ArrayList<Cart> cartList) throws SQLException {
        List<Cart> products = new ArrayList<Cart>();
        if (cartList.size() > 0) {
            for (Cart item : cartList) {
                query = "select * from SanPham where id = ?";
                pst = this.con.prepareStatement(query);
                pst.setInt(1, item.getId());
                rs = pst.executeQuery();
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setId(rs.getInt("id"));
                    cart.setName(rs.getString("name"));
                    cart.setCategory(rs.getString("category"));
                    cart.setPrice(rs.getInt("price") * item.getNumber());
                    cart.setNumber(item.getNumber());

                    products.add(cart);
                }
            }
        }
        return products;
    }

    public int getTotalprice(ArrayList<Cart> cartList) throws SQLException {
        int sum = 0;
        if (cartList.size() > 0) {
            for (Cart c : cartList) {
                query = "select price from SanPham where id=?";
                pst = this.con.prepareStatement(query);
                pst.setInt(1, c.getId());
                rs = pst.executeQuery();
                while(rs.next()) {
                    sum += rs.getInt("price") * c.getNumber();
                }
            }
        }
        return sum;
    }
    public Product getProductfromId(int pId) throws SQLException{
        Product product = new Product();
        
        query = " select * from SanPham where id = ? ";
        pst = this.con.prepareStatement(query);
        pst.setInt(1, pId);
        rs = pst.executeQuery();
        
        while(rs.next()){
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getInt("price"));
            product.setImage(rs.getString("image"));    
        }
        
        return product;
    }
    public void UpdateAfterbuy(int id, int number) throws SQLException{
        query = "update SanPham set quantity = quantity - ?, sold = sold + ? where id = ? ";
        pst = this.con.prepareStatement(query);
        pst.setInt(1,number);
        pst.setInt(2,number);
        pst.setInt(3,id);
        pst.executeUpdate();
    }

}
