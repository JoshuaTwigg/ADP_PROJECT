
package com.mycompany.databaseControl;

import java.sql.*; 
import java.util.ArrayList;
/**
 *
 * @author hp
 */
public class ServerDAO {
    
    private final Connection con;

    public ServerDAO() {
        con = Connect.initDBConnection();
    }
    
    public ArrayList<Object[]> readRecords(){
        ArrayList<Object[]> results = new ArrayList<>();
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM CARS");
            
            if (rs != null) {
                while(rs.next()){
                    Object[] result = new Object[2];
                    result[0] = rs.getString("CAR_NAME");
                    result[1] = rs.getInt("NUM_OF_VOTES");
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error reading records: " + e.getMessage());
        }
        
        return results;       
    }
    
    public String process(String car){
        String output = "";
        
        try {
            PreparedStatement ps = con.prepareStatement("SELECT CAR_NAME FROM CARS WHERE CAR_NAME = ?");
            ps.setString(1, car);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                if (rs.next()) {
                    updateRecord(car);
                    output = "Client: voted for " + car;
                } else {
                    addingCar(car);
                    output = "Client: added " + car;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error processing: " + e.getMessage());
        }
        
        return output;
    }
    
    private void updateRecord(String car){
        int votes = getVote(car) + 1;
        
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE CARS SET NUM_OF_VOTES = ? WHERE CAR_NAME = ?");
            ps.setInt(1, votes);
            ps.setString(2, car);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating votes: " + e.getMessage());
        }
    }
    
    private void addingCar(String car){
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO CARS VALUES(?,0)");
            ps.setString(1, car);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating votes: " + e.getMessage());
        }
    }
    
    private int getVote(String car){
        int votes = -1;
        
        try {
            PreparedStatement ps = con.prepareStatement("SELECT NUM_OF_VOTES FROM CARS WHERE CAR_NAME = ?");
            ps.setString(1, car);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                rs.next();
                votes = rs.getInt("NUM_OF_VOTES");
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting votes: " + e.getMessage());
        }
        
        return votes;
    }
    
    public void terminate() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Error closing: " + e.getMessage());
        }
    }
}
