/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hansani
 */
public class ManageVictim extends ManageUser{
    private static PreparedStatement pst1;
    private static ResultSet rst1;
    private int c=0,count;
    int[] vid = new int[100];
    String[] vName = new String[100];
    
    public void addUser(int vid,int nic) throws SQLException{              
        String userInfo = "insert into victim values(?, ?)";
        pst1 = Db.connection().prepareStatement(userInfo);
        pst1.setInt(1, vid);
        pst1.setInt(2, nic);
        
        pst1.executeUpdate();          
        System.out.println("INSERT COMPLETE");
    }
    
    
    public int[] getVid() throws SQLException{
        c=0;
        pst1 = Db.connection().prepareStatement("select vid from victim");
        rst1 = pst1.executeQuery();
        
        while (rst1.next()) {
            vid[c]=rst1.getInt("vid");
            c++;
        }
        return vid;
    }
    
    public int getVidCount(){
        return c+1;
    }
    
    public String[] loadVictimData() throws SQLException{
        pst1 = Db.connection().prepareStatement("select person.nic, victim.vid, victim.nic, person.fname, person.lname from person inner join victim on victim.nic = person.nic");
        rst1 = pst1.executeQuery();
        
        count = 0;
        while (rst1.next()) {
            vName[count]=rst1.getString("person.fname");
            vName[count]+=" ";
            vName[count]+=rst1.getString("person.lname");
            count++;
        }
        return  vName;
    }
    
}
