package com.mycompany.databaseControl;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBUpdate {
//    public static String dbURL = "jdbc:derby://localhost:1527/CarVote";
//    public static String username = "root";
//    public static String password = "root";
            
    private static Connection con;
    private static Statement SQLStatement;
//    private static ResultSet rSet = null; 

    public DBUpdate(){
//        con = Connect.initDBConnection();
//        try {
//            SQLStatement = con.createStatement();
//            System.out.println("<SQL Statement ready>");
//        } catch (SQLException sqlErr) {
//            System.out.println("*Error*");
//            System.out.println(sqlErr.getMessage());
//        } catch (Exception e) {
//            System.out.println("*Error*");
//            System.out.println(e.getMessage());
//        }
    }
    // =====================================

    // CONNECT
    public static void connect() {
        System.out.println("Trying to connect to database...");
        try {
            String dbURL = "jdbc:derby://localhost:1527/CarVote";
            String username = "root";
            String password = "root";

            System.out.println("About to get a connection....");
            con = DriverManager.getConnection(dbURL, username, password);
            System.out.println("\n<Connection Established Successfully>");
            
            SQLStatement = con.createStatement();
            System.out.println("<SQL Statement ready>");
        } catch (SQLException sqlErr) {
            System.out.println("*Error*");
            System.out.println(sqlErr.getMessage());
        } catch (Exception e) {
            System.out.println("*Error*");
            System.out.println(e.getMessage());
        }
    }

    // TERMINATE
    public static void terminate() {
        try {
            SQLStatement.close();
            System.out.println("\n<SQL Statement method terminated>");
            con.close();
            System.out.println("<Connection terminated>");
        } catch (SQLException ex) {
            Logger.getLogger(DBUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // =====================================
    
    
    public static void updateRecord(
            String tableName,
            String optionColumn,
            String IDKey, 
            String ID, 
            String newValue
    ){
        System.out.println(optionColumn);
        System.out.println(newValue);
        System.out.println("about to update");
        System.out.println("table affected: " + tableName + "," + "column name: " + optionColumn);
        System.out.println("ID ROW: " + " " + ID);
        
        String sql = "UPDATE " + tableName + " SET " + optionColumn + "=?" + " WHERE " + IDKey + "=?";
        System.out.println(sql);
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newValue);
            pstmt.setString(2, ID);

            // Execute the update statement
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(tableName + " table updated successfully.");
                JOptionPane.showMessageDialog(null, "Table updated successfully");
            } 
            
            else {
                System.out.println("No rows affected.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    // OVERLOAD
    public static void updateRecord(
            String tableName,
            String optionColumn,
            String IDKey, 
            int ID, 
            int change
    ){
        System.out.println(optionColumn);
        System.out.println(change);
        System.out.println("about to update");
        System.out.println("table affected: " + tableName + "," + "column name: " + optionColumn);
        System.out.println("ID ROW: " + " " + ID);
        
        String sql = "UPDATE " + tableName + " SET " + optionColumn + "=?" + " WHERE " + IDKey + "=?";
//        String sql = "UPDATE DISORDER SET " + optionColumn + "=?" + " WHERE disorder_ID  = ? ";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, change);
            pstmt.setInt(2, ID);

            // Execute the update statement
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(tableName + " table updated successfully.");
                JOptionPane.showMessageDialog(null, "Table updated successfully");
            } 
            
            else {
                System.out.println("No rows affected.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
