// add to tools class?
// 
//package com.mycompany.GUI;
//
////import com.mycompany.databaseControl.DBRead;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
///**
//* Used to refresh text on a GUI 
//**/ 
//public class Refresh {
//     public static void insertToJTable(JTable tableUser) {
//
//        DBRead readObj = new DBRead();
//        //connect to db
//        readObj.connect();
//        //use read method
//        readObj.readAllRecords("user_temp", 5);
////        readObj.readAllRecords(tableName, numOfCols);
//
//        // JTable
//        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
//
//        // clear table
//        model.setRowCount(0); 
//
//              
//        try {
//            ResultSet res = readObj.getResult();            
//            if (res == null) {
//                System.out.println("ResultSet is null.");
//                return; 
//            }
//            res.beforeFirst();
//
//            int numOfCols = 5;
//            Object[] row = new Object[numOfCols];
//            while (res.next()) {        
//                System.out.println("First loop");
//
//                for (int i = 1; i <= numOfCols; i++) { 
//                    row[i - 1] = res.getObject(i); 
//                }
//                model.addRow(row);
//            }
//        } 
//        catch (SQLException ex) {
//            System.out.println("this catch" + ex);
//        }
//
//        readObj.terminate();
//    }
//}
