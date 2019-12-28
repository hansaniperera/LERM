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
public class ManagePoliceReport {
    private static PreparedStatement pst;
    private static ResultSet rst;
    int c,count;
    int[] cNic =new int [100];
     
    public  void setReportDetail(DefaultTableModel model) throws SQLException{
        pst = Db.connection().prepareStatement("select policereport.nic,policereport.dateissued,\n"+
        "person.fname,person.lname\n"+
        "FROM policereport\n"+
        "INNER JOIN person ON policereport.nic=person.nic");
        rst = pst.executeQuery();

        
        while(rst.next()){
            int nic = rst.getInt("nic");
            String name = rst.getString("fname")+" "+rst.getString("lname");
            String date = rst.getString("dateissued");
            
            
            Object[] row = {nic,name,date};
            
            model.addRow(row);

        }
        
    }
    
    public int getCount() throws SQLException{
        count=0;
        pst = Db.connection().prepareStatement("select repid from policereport");
        rst = pst.executeQuery();
        
        
        while (rst.next()) {
            cNic[count]=rst.getInt("repid");
            count++;
        }
        return  count;
    }
    
    public void addReport(int repId,String date,int nic) throws SQLException{
        pst = Db.connection().prepareStatement("insert into policereport values(?, ?, ?)");
        pst.setInt(1, repId);
        pst.setString(2, date);
        pst.setInt(3, nic);
       
        pst.executeUpdate();
    }
    
    
    public void updateUser(String dateissued,int nic) throws SQLException{
        pst = Db.connection().prepareStatement("update policereport set dateissued=?  WHERE (nic = ?)"); 
    
        
        pst.setString(1, dateissued);
        
        pst.setInt(2, nic);
                       
        pst.executeUpdate();          
           
        System.out.println("UPDATE COMPLETE");
    }
    
    public String getDate(String nic) throws SQLException{
         String row = null ;
        pst = Db.connection().prepareStatement("select dateissued from policereport where nic = ?");
        pst.setString(1, nic);
        rst = pst.executeQuery();
        
        while (rst.next()) {            
            row = rst.getString("dateissued");
            
        }
        
        return row;
    }
    
     public int[] getRepId() throws SQLException{
         c=0;
        pst = Db.connection().prepareStatement("select nic from person where nic not in(select nic from policereport)");
              
        rst = pst.executeQuery();
                
        while (rst.next()) {
            cNic[c]=rst.getInt("nic");
            c++;
        }
        return  cNic;
    } 
     
    
    
    
}
