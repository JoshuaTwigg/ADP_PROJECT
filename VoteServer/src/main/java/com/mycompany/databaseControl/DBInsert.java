package com.mycompany.databaseControl;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBInsert {
    private Connection con = null;
    private Statement SQLStatement = null;
   

    public DBInsert() {
        this.connect();
    }
    // -------------------------

    // CONNECT
    public void connect() {
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
    // =====================================

    // TERMINATE
    public void terminate() {
        try {
            SQLStatement.close();
            System.out.println("\n<SQL Statement method terminated>");
            con.close();
            System.out.println("<Connection terminated>");
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // =====================================


    // INSERT
    // using prepared statements
    
    // =====================================
    // ONLY HAS STRING VALUE PARAMETERS
    // =====================================    
    /* 
    * for a tables that has 2 columns
    * all columns store string values
    * can specific the table name to insert to
    */
    public void insertRecord(
            String table, 
            String value1, 
            String value2
    ) {
        try {
            System.out.println("attemping to add...");

            String sql = "insert into " + table + " values(?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, value1);
            pStatement.setString(2, value2);

            int res = pStatement.executeUpdate();
            // System.out.println(res + " records inserted");
            System.out.println("<INSERTION SUCCESSFUL>");
            JOptionPane.showMessageDialog(null, "Insertion Successful");

        } catch (SQLException SQLErr) {
            System.out.println("*Error*");
            System.out.println(SQLErr.getMessage());
        }
    }
    // ***OVERLOAD*** //
    /* 
    * for a tables that has 3 columns
    * all columns store string values
    * can specific the table name to insert to
    */
    public void insertRecord(
            String table, 
            String value1, 
            String value2, 
            String value3
    ) {
        try {
            System.out.println("attemping to add...");

            String sql = "insert into " + table + " values(?,?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, value1);
            pStatement.setString(2, value2);
            pStatement.setString(3, value3);

            pStatement.executeUpdate();
            System.out.println("<INSERTION SUCCESSFUL>");
            JOptionPane.showMessageDialog(null, "Insertion Successful");

        } catch (SQLException SQLErr) {
            System.out.println("*Error*");
            System.out.println(SQLErr.getMessage());
        }
    }
    // ***OVERLOAD*** //
    public void insertRecord(
            String table, 
            String value1, 
            String value2, 
            String value3, 
            String value4
    ) {
        try {
            System.out.println("attemping to add...");

            String sql = "insert into " + table + " values(?,?,?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, value1);
            pStatement.setString(2, value2);
            pStatement.setString(3, value3);
            pStatement.setString(4, value4);

            pStatement.executeUpdate();
            System.out.println("<INSERTION SUCCESSFUL>");
            JOptionPane.showMessageDialog(null, "Insertion Successful");

        } catch (SQLException SQLErr) {
            System.out.println("*Error*");
            System.out.println(SQLErr.getMessage());
        }
    }
    // ***OVERLOAD*** //
    public void insertRecord(
            String table, 
            String value1, 
            String value2, 
            String value3, 
            String value4,
            String value5
    ) {
        try {
            System.out.println("attemping to add...");

            String sql = "insert into " + table + " values(?,?,?,?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, value1);
            pStatement.setString(2, value2);
            pStatement.setString(3, value3);
            pStatement.setString(4, value4);
            pStatement.setString(5, value5);

            pStatement.executeUpdate();
            System.out.println("<INSERTION SUCCESSFUL>");
            JOptionPane.showMessageDialog(null, "Insertion Successful");
        } 
        catch (SQLException SQLErr) {
            System.out.println("*Error*");
            System.out.println(SQLErr.getMessage());
        }
    }
    
    
    
    // ==========================================
    // HAS STRING AND INT VALUES AS PARAMETERS
    // ==========================================

    // ***OVERLOAD*** //
    public void insertRecord(
            String table, 
            String value1, 
            int value2
    ) {
        try {
            System.out.println("attemping to add...");

            String sql = "insert into " + table + " values(?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, value1);
            pStatement.setInt(2, value2);


            int res = pStatement.executeUpdate();
            System.out.println("<Record inserted>");
        } catch (SQLException SQLErr) {
            System.out.println("*Error*");
            System.out.println(SQLErr.getMessage());
        }
    }
    
    public void insertRecord(
            String table, 
            int value1, 
            int value2
    ) {
        try {
            System.out.println("attemping to add...");

            String sql = "insert into " + table + " values(?,?)";
            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setInt(1, value1);
            pStatement.setInt(2, value2);


            int res = pStatement.executeUpdate();
            System.out.println("<Record inserted>");
        } catch (SQLException SQLErr) {
            System.out.println("*Error*");
            System.out.println(SQLErr.getMessage());
        }
    }
    // =====================================
}