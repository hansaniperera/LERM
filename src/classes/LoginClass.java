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
 * @author Hansani Perera
 */
public class LoginClass {
    
    private static int loginStatus=0; //0-no one, 1-officer, 2-admin
    private static String userName;
    private static PreparedStatement pst1;
    private static PreparedStatement pst2;
    private static ResultSet rs1;
    private static ResultSet rs2;
    
    public static boolean login(String userName,String password) throws SQLException{
        pst1=Db.connection().prepareStatement("select * from officer where username=? and pwd=?");
        pst1.setString(1, userName);
        pst1.setString(2, password);
        rs1=pst1.executeQuery();
        
        pst2=Db.connection().prepareStatement("select * from admin where username=? and password=?");
        pst2.setString(1, userName);
        pst2.setString(2, password);
        rs2=pst2.executeQuery();
        
        LoginClass.userName=userName;
        if(rs1.next()){
            loginStatus=1;
                return true;
            }else if(rs2.next()){
                loginStatus=2;
                return true;
            }else{ 
            return false;
            }
        }
           
        
        
      
    public static String getUsername(){
        return userName;
    }
    public static int getLoginStatus(){
        return loginStatus;
    }
    
    public static void setUserName(String name){
        LoginClass.userName = name;
    }
    public static void logout(){
        userName=null;
        loginStatus=0;
    }
    
}
