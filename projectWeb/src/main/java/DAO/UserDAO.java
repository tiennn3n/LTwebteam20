/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author USER
 */
public class UserDAO {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public User userLogin(String email, String pass) {
        User user = null;
        try {
            query = "Select * from KhachHang where Email= ? and Pass = ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setEmail(rs.getString("Email"));
                user.setName(rs.getString("Name"));
                user.setPhone(rs.getString("Phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return user;
    }

    public void InsertUser(String email, String name, String phone, String pass) {
        query = "Insert into KhachHang values (?,?,?,?)";
        try {
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, name);
            pst.setString(3, phone);
            pst.setString(4, pass);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Foundemail(String email) throws SQLException {
        query = "select * from KhachHang where Email = ?";
        pst = this.con.prepareStatement(query);
        pst.setString(1, email);
        rs = pst.executeQuery();
        if(rs.next())
        {
            return true;
        }
        else return false;
    }
    public void Updatepass(String email, String newpass) throws SQLException
    {
        query = "update KhachHang set Pass=? where Email =?";
        pst = this.con.prepareStatement(query);
        pst.setString(1, newpass);
        pst.setString(2,email);
        pst.executeUpdate();
    }
}
