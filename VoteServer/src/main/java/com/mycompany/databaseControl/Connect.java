
package com.mycompany.databaseControl; 

import java.sql.*; 

public class Connect {
    
    public static Connection initDBConnection(){
        Connection dbc = null; 
        String DBURL = "jdbc:derby://localhost:1527/CarVote";
        String username = "root";
        String password = "root";
        
        System.out.println("attempting to establish connection...");
        try{
            dbc = DriverManager.getConnection(DBURL, username, password);
            System.out.println("\n<CONNECTION ESTABLISHED>");
        }
        catch(SQLException SQLEx){
            System.out.println(SQLEx.getMessage());
        }
        return dbc; 
    }
    // OVERLOAD
    public static void initDBConnection(Connection DBC, Statement SQLStatement){
        // Connection dbc = null; 
        String DBURL = "jdbc:derby://localhost:1527/CarVote";
        String username = "root";
        String password = "root";
        
        System.out.println("attempting to establish connection...");
        try{
            DBC = DriverManager.getConnection(DBURL, username, password);
            System.out.println("\n<CONNECTION ESTABLISHED>");

            SQLStatement = DBC.createStatement();
            System.out.println("\n<SQL Statement ready>");
        }
        catch(SQLException SQLEx){
            System.out.println(SQLEx.getMessage());
        }
    }
}
