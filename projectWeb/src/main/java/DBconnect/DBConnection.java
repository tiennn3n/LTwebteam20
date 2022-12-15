/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * "LAPTOP-M24AHQSG\\MSSQLSERVER05";
 *
 * @author USER
 */
public class DBConnection {
    private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null){
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection("jdbc:sqlserver://LAPTOP-M24AHQSG\\MSSQLSERVER05:1433;databaseName=ShopSwatch;encrypt=true;trustServerCertificate=true;","sa","123456");
            System.out.print("connected");
        }
        return connection;
    }

}
