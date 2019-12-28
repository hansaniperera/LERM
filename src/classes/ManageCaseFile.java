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
public class ManageCaseFile {
    PreparedStatement pst;
    ResultSet rst;
    int c,count;
    int[] cNic = new int[100];
    
    public  void setCase(DefaultTableModel model) throws SQLException{
        pst = Db.connection().prepareStatement("select * from casefile");
               
        rst = pst.executeQuery();

        
        while(rst.next()){
            int caseid = rst.getInt("caseid");
            String status = rst.getString("status");
            int crid = rst.getInt("crid");
            
            
            Object[] row = {caseid,status,crid};
            
            model.addRow(row);

        }
    }
    
    public void updateCase(String status,int crid) throws SQLException{
        pst = Db.connection().prepareStatement("update casefile set status=?  WHERE (crid = ?)"); 
    
        
        pst.setString(1, status);
        
        pst.setInt(2, crid);
                       
        pst.executeUpdate();          
           
        System.out.println("UPDATE COMPLETE");
    }
    
    public int[] loadCaseData() throws SQLException{
        c=0;
        pst = Db.connection().prepareStatement("select crid from casefile");
        rst = pst.executeQuery();
        
        
        while (rst.next()) {
            cNic[c]=rst.getInt("crid");
            c++;
        }
        return  cNic;
    }
    
    public String getStatus(String caseid) throws SQLException{
         String row = null ;
        pst = Db.connection().prepareStatement("select status from casefile where caseid = ?");
        pst.setString(1, caseid);
        rst = pst.executeQuery();
        
        while (rst.next()) {            
            row = rst.getString("status");
            
        }
        
        return row;
    }
    
    public void addCase(int caseid,String status,int crid) throws SQLException{
        pst = Db.connection().prepareStatement("insert into casefile values(?, ?, ?)");
        pst.setInt(1, caseid);
        pst.setString(2, status);
        pst.setInt(3, crid);
       
        pst.executeUpdate();
    }
    
    public int getCaseID(){
        return c+1;
    }
    
    public int[] getIdList() throws SQLException{
         count=0;
        pst = Db.connection().prepareStatement("select crid from crime where crid not in(select crid from casefile)");
              
        rst = pst.executeQuery();
        
        
        while (rst.next()) {
            cNic[count]=rst.getInt("crid");
            count++;
        }
        return  cNic;
    }   
    
    
}
