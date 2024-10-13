package com.mycompany;

import com.mycompany.databaseControl.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuickAdd {
    Connection DBC;
    Statement SQLStatement;
//    ResultSet rSet;
//    PreparedStatement pStatement;
    // --------------------------------

    public void connect() {
        DBC = Connect.initDBConnection();
        try {
            SQLStatement = DBC.createStatement();
            System.out.println("\n<SQL Statment ready>");
        } catch (SQLException SQLEx) {
            System.out.println(SQLEx.getMessage());
        }
    }

    String table1 = "CREATE TABLE Cars("
            + "car_name VARCHAR(30), "
            + "num_of_votes INTEGER"
            + ")";
    
    public void wildCard(String sqlOp) {
        System.out.println("\n");
        System.out.println(sqlOp);
        try {
            SQLStatement.executeUpdate(sqlOp);
            System.out.println("<SQL Statement successful>");
        } catch (SQLException SQLEx) {
            System.out.println(SQLEx.getMessage());
        }
    }
    

    // public boolean createAllTables() {
    public void createAllTables() {
        // creating the tables
        wildCard(table1);
    }

  

    // -----------------------------------------
    // TESTING
//    public static void main(String[] args) {
//        QuickAdd obj = new QuickAdd();
//
//        obj.connect();
////        obj.createAllTables();
//        
////        String op1 = "INSERT INTO Cars VALUES('BMW', '100');";
//        String op1 = "INSERT INTO temp VALUES(100, 10);";
//
//        obj.wildCard(op1);
//        
//        System.out.println("\n<END OF TEST>");
//    }
}
