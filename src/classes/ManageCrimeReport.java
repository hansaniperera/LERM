/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import frames.Home;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.table.DefaultTableModel;

/**
 *
 *
 */
public class ManageCrimeReport {
    private PreparedStatement pst;
    private ResultSet rs;
    String[] vName = new String[50];
    int count = 0;
     
    public void setUserInfo(DefaultTableModel model) throws SQLException{
        pst = Db.connection().prepareStatement("select * from crime");
        rs = pst.executeQuery();

        count = 0;
        while(rs.next()){
            int cid = rs.getInt("crid");
            String date = rs.getString("date");
            String time = rs.getString("time");
            String description = rs.getString("description");
            
            Object[] row = {cid,date,time,description};
            
            model.addRow(row);
            count++;
        }
        
    }
    
    public void addReport(String crid, String date, String time, String place, String victimID, String cid, String description) throws SQLException{
        pst = Db.connection().prepareStatement("insert into crime values(?, ?, ?, ?, ?, ?,?)");
        pst.setString(1, crid);
        pst.setString(2, date);
        pst.setString(3, time);
        pst.setString(4, place);
        pst.setString(5, description);
        pst.setString(6, victimID);
        pst.setString(7,cid);
        pst.executeUpdate();
    }
    
    public void updateCriminalReport(PreparedStatement pst) throws SQLException{
        
        pst.execute();
    }
    
    public String[] getUser(String crid) throws SQLException{
        String[] row = new String[6];
        pst = Db.connection().prepareStatement("select * from crime where crid = ?");
        pst.setString(1, crid);
        rs = pst.executeQuery();
        
        while (rs.next()) {            
            row[0] = rs.getString("date");
            row[1] = rs.getString("time");
            row[2] = rs.getString("place");
            row[3] = rs.getString("vid");
            row[5] = rs.getString("description");
        }
        
        pst = Db.connection().prepareStatement("select person.fname, person.lname from person inner join victim on victim.nic = person.nic and victim.vid = ?");
        pst.setString(1, row[3]);
        rs = pst.executeQuery();
        
        while (rs.next()) {            
            row[4] = rs.getString("person.fname") + " " + rs.getString("person.lname");
        }
        
        return row;
    }
    
    
    
    public int getCount(){
        return count+1;
    }
    
    
}


