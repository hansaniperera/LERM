/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hansani
 */
public class ManageOfficer extends ManageUser{
    
    PreparedStatement pst;
    ResultSet rs;
    private int c=0;
    
    public int loadId() throws SQLException{
        pst = Db.connection().prepareStatement("select nic from officer");
        rs = pst.executeQuery();
        
        while (rs.next()) {
            pNic[c]=rs.getInt("officer.nic");
            c++;
        }
        return c ;
    }
    
    public int[] loadOfficerId() throws SQLException{
        pst = Db.connection().prepareStatement("select pid from officer");
        rs = pst.executeQuery();
        
        while (rs.next()) {
            pNic[count]=rs.getInt("officer.pid");
            count++;
        }
        return pNic ;
    }
    
        
       
    public void addUser(int val,String username, int nic,String password){
        try {
            String userInfoAdmin = "insert into officer values(?, ?, ?,?)";
            pst = Db.connection().prepareStatement(userInfoAdmin);
            pst.setInt(1,++val);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setInt(4, nic);
            pst.executeUpdate();          
            System.out.println("INSERT COMPLETE");
        } catch (SQLException ex) {
            Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateUser(int val,String username, String password,int nic) throws SQLException{
        pst = Db.connection().prepareStatement("update officer set pid = ? ,username = ? ,pwd=?, nic=? WHERE (nic = ?)"); 
    
        pst.setInt(1,val);
        pst.setString(2, username);
        pst.setString(3, password);
        pst.setInt(4, nic);
        pst.setInt(5, nic);
        
                       
        pst.executeUpdate();          
           
        System.out.println("UPDATE COMPLETE");
    }
    
}
