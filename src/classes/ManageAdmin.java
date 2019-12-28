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
public class ManageAdmin extends ManageUser {
    PreparedStatement pst;
    ResultSet rs;
    private int c=0,d=0;
    
    public ManageAdmin(){
        
    }
             
        
    
    public int loadId() throws SQLException{
        pst = Db.connection().prepareStatement("select nic from admin");
        rs = pst.executeQuery();
        
        c=0;
        while (rs.next()) {
            pNic[c]=rs.getInt("admin.nic");
            c++;
        }
        return c ;
    }
    
    public int[] loadAdminOfficerId() throws SQLException{
        
        pst = Db.connection().prepareStatement("select admin.nic from admin UNION select officer.nic from officer ");
        rs = pst.executeQuery();
             
        int[] aoNIC = new int[50];
        d=0;
        while (rs.next()) {
            if(rs.getInt("nic")!=0){
            aoNIC[d]=rs.getInt("nic");
            System.out.println(rs.getInt("nic"));
            d++;
            }
        }
        return aoNIC ;
    }
    
         
    public void addUser(int val,String username,String password,int nic){
        try {
            String userInfoAdmin = "insert into admin VALUES (?, ?, ?,?)";
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
        pst = Db.connection().prepareStatement("update admin set aid=?, username=?, password=?, nic=? WHERE (nic = ?)"); 
    
        pst.setInt(1,val );
        pst.setString(2, username);
        pst.setString(3, password);
        pst.setInt(4, nic);
        pst.setInt(5, nic);
        
                       
        pst.executeUpdate();          
           
        System.out.println("UPDATE COMPLETE");
    }
    
}
