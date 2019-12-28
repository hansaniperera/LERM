

package classes;

import classes.Db;
import classes.LoginClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ManageUser {  
    private static PreparedStatement pst1;
    
    private static ResultSet rst1;
    
    private static String userName, password,nic;
    int[] pNic = new int[100];
    int count = 0;
    int[] cNic = new int[100];
        
    public void addUser(String lname, String fname,int telNumber,String address,int nic, String email) throws SQLException{              
        String userInfo = "insert into person values(?, ?, ?, ?, ?, ?)";
        pst1 = Db.connection().prepareStatement(userInfo);
        pst1.setInt(1, nic);
        pst1.setString(2, fname);
        pst1.setString(3, lname);
        pst1.setString(4, address);
        pst1.setString(5, email);
        pst1.setInt(6, telNumber);
        
                       
        pst1.executeUpdate();          
        System.out.println("INSERT COMPLETE");
    }
    
    public int getCount(){
        return count;
    }
    
    public void updateUser(String lname, String fname,String telNumber,String address,int nic, String email) throws SQLException{
        pst1 = Db.connection().prepareStatement("update person set nic=?, fname=?, lname=?, address=?, email=?, tel=? WHERE (nic = ?)"); 
    
        pst1.setInt(1, nic);
        pst1.setString(2, fname);
        pst1.setString(3, lname);
        pst1.setString(4, address);
        pst1.setString(5, email);
        pst1.setString(6, telNumber);
        pst1.setInt(7, nic);
                       
        pst1.executeUpdate();          
           
        System.out.println("UPDATE COMPLETE");
    }
    
   
    public static void setUserInfo(DefaultTableModel model) throws SQLException{
        pst1 = Db.connection().prepareStatement("select * from officer\n"+
                "UNION\n"+
                "select * from admin");
        rst1 = pst1.executeQuery();
        
        while(rst1.next()){
            int nic = rst1.getInt("nic");
            String username = rst1.getString("username");
                       
            
            Object[] row = {nic,username};
            
            model.addRow(row);

        }
    }
        
     public int[] getIdFilter() throws SQLException{
        count=0;
        pst1 = Db.connection().prepareStatement("select distinct nic from person where nic not in (select nic from admin\n"+
                "union select nic from officer union select nic from criminal union select nic from victim)");
        rst1 = pst1.executeQuery();
        
        
        while (rst1.next()) {
            cNic[count]=rst1.getInt("nic");
            System.out.println(cNic[count]);
            count++;
        }
        return  cNic;
    } 
     
      public String[] getInfo(int nic) throws SQLException{
        String[] row = new String[7];
        pst1 = Db.connection().prepareStatement("select * from person where nic = ?");
        pst1.setInt(1, nic);
        rst1 = pst1.executeQuery();
        
        while (rst1.next()) {            
            row[0] = Integer.toString(rst1.getInt("nic"));
            row[1] = rst1.getString("fname");
            row[2] = rst1.getString("lname");
            row[3] = rst1.getString("address");
            row[4] = rst1.getString("email");
            row[5] = Integer.toString(rst1.getInt("tel"));
        }
        
        return row;
    }
        
    } 
    

   

   
