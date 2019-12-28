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
 * @author Binith
 */
public class ManageEvidenceLOg {
    private PreparedStatement pst1;
    private ResultSet rs;
    private ResultSet rs2;
    String[] criminalID = new String[50];
    String[] officerID = new String[50];
    int count = 0;
     
    public void setEvidenceInfo(DefaultTableModel model) throws SQLException{
        pst1 = Db.connection().prepareStatement("select * from evidence");
        rs = pst1.executeQuery();

        count = 0;
        while(rs.next()){
            String crid = rs.getString("crid");
            criminalID[count] = crid;
            String date = rs.getString("date");
            String time = rs.getString("time");
            String pid = rs.getString("pid");
            officerID[count] = pid;
            String description = rs.getString("details");
            
            Object[] row = {crid,date,time,description};
            
            model.addRow(row);
            count++;
        }
        
    }
    
    public String[] loadCriminalID() throws SQLException{
        
        String cid[] = new String[50];
        pst1 = Db.connection().prepareStatement("select cid from criminal");
        rs = pst1.executeQuery();

        count = 0;
        while(rs.next()){
            cid[count++] = rs.getString("cid");
        }
        
        return  cid;
    }
    
    public String[] loadOfficerID() throws SQLException{
        
        String pid[] = new String[50];
        pst1 = Db.connection().prepareStatement("select pid from officer");
        rs = pst1.executeQuery();

        count = 0;
        while(rs.next()){
            pid[count++] = rs.getString("pid");
        }
        
        return  pid;
    }
    
    public void addEvidence(String crid, String description, String date, String time, String pid) throws SQLException{
        pst1 = Db.connection().prepareStatement("insert into evidence values(?, ?, ?, ?, ?, ?)");
        pst1.setString(1, crid);
        pst1.setString(2, Integer.toString(count + 1));
        pst1.setString(3, description);
        pst1.setString(4, date);
        pst1.setString(5, time);
        pst1.setString(6, pid);
        pst1.executeUpdate();
    }
    
    public void updateEvidenceLog(PreparedStatement pst) throws SQLException{
        
        pst.execute();
    }
    
    public String[] getEvidence(String eid) throws SQLException{
        String[] row = new String[6];
        pst1 = Db.connection().prepareStatement("select * from evidence where eid = ?");
        pst1.setString(1, eid);
        rs = pst1.executeQuery();
        
        while (rs.next()) {            
            row[0] = rs.getString("crid");
            row[1] = rs.getString("date");
            row[2] = rs.getString("time");
            row[3] = rs.getString("pid");
            row[4] = rs.getString("details");
            row[5] = rs.getString("eid");
        }
        
        return row;
    }
    
    public int getCount(){
        return count+1;
    }
    
}
