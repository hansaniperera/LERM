/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.*;


/**
 *
 * @author Hansani Perera
 */
public class Db {
    private static Connection con;
    
    public static Connection connection(){
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/lem","root","r00t");
            
        } catch (Exception e) {
            System.err.println("error:"+e);
        }
        return con;
    }
    
    
}
