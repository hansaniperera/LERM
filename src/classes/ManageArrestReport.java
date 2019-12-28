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
public class ManageArrestReport {
    private PreparedStatement pst1;
    private ResultSet rs;
    private ResultSet rs2;
    String[] cName = new String[50];
    String[] criminalNIC = new String[50];
    String[] oName = new String[50];
    String[] officerID = new String[50];
    int count = 0;
     
    public void setArrestInfo(DefaultTableModel model) throws SQLException{
        pst1 = Db.connection().prepareStatement("select person.nic, person.fname, person.lname, criminal.offense, criminal.nic, criminal.pid from person inner join criminal on criminal.nic = person.nic");
        rs = pst1.executeQuery();

        count = 0;
        while(rs.next()){
            String nic = rs.getString("person.nic");
            String name = rs.getString("person.fname")+" "+rs.getString("person.lname");
            cName[count] = name;
            criminalNIC[count] = rs.getString("criminal.nic");
            String offense = rs.getString("criminal.offense");
            String arrestedBy = null;
            
            pst1 = Db.connection().prepareStatement("select person.fname, person.lname from person inner join officer on officer.nic = person.nic and officer.pid = ?");
            pst1.setString(1, rs.getString("criminal.pid"));
            officerID[count] = rs.getString("criminal.pid");
            rs2 = pst1.executeQuery();
            while (rs2.next()) {
                arrestedBy = rs2.getString("person.fname")+" "+rs2.getString("person.lname");
                oName[count] = arrestedBy;
            }
            
            Object[] row = {nic,name,offense,arrestedBy};
            
            model.addRow(row);
            count++;
        }
        
    }
    
    public String[] loadCriminalName() throws SQLException{
        
        return  cName;
    }
    
    public String[] loadCriminalNIC() throws SQLException{
        
        return  criminalNIC;
    }
    
    public String[] loadOfficerName() throws SQLException{
        
        return  oName;
    }
    
    public String[] loadOfficerID() throws SQLException{
        
        return  officerID;
    }
    
    public void addArrest(String cid, String offense, String pid, String nic) throws SQLException{
        pst1 = Db.connection().prepareStatement("insert into criminal values(?, ?, ?, ?)");
        pst1.setString(1, cid);
        pst1.setString(2, offense);
        pst1.setString(3, pid);
        pst1.setString(4, nic);
        pst1.executeUpdate();
    }
    
    public void updateArrestReport(PreparedStatement pst) throws SQLException{
        
        pst.execute();
    }
    
    public String[] getArrest(String cid) throws SQLException{
        String[] row = new String[6];
        pst1 = Db.connection().prepareStatement("select person.nic, person.fname, person.lname, criminal.offense, criminal.nic, criminal.pid, criminal.cid from person inner join criminal on criminal.nic = person.nic and criminal.cid=?");
        pst1.setString(1, cid);
        rs = pst1.executeQuery();
        
        while (rs.next()) {            
            row[0] = rs.getString("person.nic");
//            System.out.println(row[0]);
            row[1] = rs.getString("person.fname")+" "+rs.getString("person.lname");
            row[2] = rs.getString("criminal.offense");
            row[3] = rs.getString("criminal.pid");
            row[5] = rs.getString("criminal.cid");
        }
        
        pst1 = Db.connection().prepareStatement("select person.fname, person.lname from person inner join officer on officer.nic = person.nic and officer.nic = ?");
        pst1.setString(1, row[3]);
        rs = pst1.executeQuery();
        
        while (rs.next()) {            
            row[4] = rs.getString("person.fname") + " " + rs.getString("person.lname");
        }
        
        return row;
    }
    
    public int getCount(){
        return count+1;
    }
    
}
