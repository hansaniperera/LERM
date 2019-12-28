/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hansani
 */
public class ManageCriminals extends ManageUser{
    private static PreparedStatement pst;
    private static ResultSet rst;
    int c=0,count;
    int[] cNic=new int[100];
     
    public void setCriminalInfo(DefaultTableModel model) throws SQLException{
        pst = Db.connection().prepareStatement("select * from criminal");
        rst = pst.executeQuery();

        
        while(rst.next()){
            int nic = rst.getInt("nic");
            int pid = rst.getInt("pid");
            String offense = rst.getString("offense");
            
            
            Object[] row = {nic,pid,offense};
            
            model.addRow(row);

        }
        
    }
    
    public void setCriminalHistory(DefaultTableModel model) throws SQLException{
        pst = Db.connection().prepareStatement("select criminal.cid,criminal.nic,person.fname,person.lname from person,crime,criminal\n" +
              "where crime.cid = criminal.cid and\n" +
              "person.nic=criminal.nic");
        rst = pst.executeQuery();

        
        while(rst.next()){
            int cid = rst.getInt("cid");
            int nic = rst.getInt("nic");
            String fname = rst.getString("fname");
            String lname=rst.getString("lname");
            
            
            Object[] row = {nic,fname,lname,cid};
            
            model.addRow(row);

        }
        
    }
    
    public void setSelectedCriminalHistory(DefaultTableModel model,int cid) throws SQLException{
        pst = Db.connection().prepareStatement("select criminal.cid,criminal.nic,person.fname,person.lname from person,crime,criminal\n" +
             "where crime.cid = criminal.cid and\n" +
             "person.nic=criminal.nic and crime.cid =?");
        pst.setInt(1, cid);
        rst = pst.executeQuery();

        
        while(rst.next()){
            int cid1 = rst.getInt("cid");
            int nic = rst.getInt("nic");
            String fname = rst.getString("fname");
            String lname=rst.getString("lname");
            
            
            Object[] row = {nic,fname,lname,cid};
            
            model.addRow(row);

        }
        
    }
    
       
    public int[] loadCrimeId() throws SQLException{
        c=0;
        pst = Db.connection().prepareStatement("select crid from crime");
        rst = pst.executeQuery();
        
        
        while (rst.next()) {
            cNic[c]=rst.getInt("crid");
            c++;
        }
        return  cNic;
    }
    
    public void addUser(int cid, String offense, int pid,int nic) throws SQLException{
        pst = Db.connection().prepareStatement("insert into criminal values(?, ?, ?, ?)");
        pst.setInt(1, cid);
        pst.setString(2, offense);
        pst.setInt(3, pid);
        pst.setInt(4, nic);
        pst.executeUpdate();
    }
    
    
    public void updateUser(String offense,int nic) throws SQLException{
        pst = Db.connection().prepareStatement("update criminal set offense= ? WHERE (nic = ?)"); 
    
       pst.setString(1, offense);
       
        pst.setInt(2,nic);
        
                       
        pst.executeUpdate();          
           
        System.out.println("UPDATE COMPLETE");
    }
    
    public int[] getCriminalID() throws SQLException{
        pst = Db.connection().prepareStatement("select cid from criminal");
        rst = pst.executeQuery();
        count=0;
        
        while (rst.next()) {
            cNic[count]=rst.getInt("cid");
            count++;
        }
        return  cNic;
    }
    
    public int getCriminalCount(){
        return count;
    }
   
}
