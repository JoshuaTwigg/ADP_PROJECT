
package com.mycompany;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

// add loop somewhere
/**
* Used to client an instance of a Client object. 
* Used to send and retrieve messages from a server
**/
public class Client {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client() {      
        try {
            socket = new Socket("127.0.0.1", 6666);
        } catch (IOException e) {
            System.out.println("Error creating socket: " + e.getMessage());
        }
        createStreams();
    }
    
    private void createStreams(){
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }
    
    public ArrayList<Object> readResults(){
        ArrayList<Object> results = new ArrayList<>();
        try {
            results = (ArrayList<Object>) input.readObject();
        } catch (IOException e) {
            System.out.println("Error getting results: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return results;
    }
    
    private void writeVehicle(String vehicle){
        try {
            output.writeObject(vehicle);
            output.flush();
        } catch (IOException e) {
            System.out.println("Error writing object: " + e.getMessage());
        }
    }
    
    public void close(){
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Error closing soket: " + e.getMessage());
        }
    }
    
    // TESTING
    public static void main(String[] args){
        System.out.println("CLIENT");

        Client client = new Client();
        client.writeVehicle("vehicle text");
        String r = client.readResults(); 
        System.out.println(r);
        
        client.close();
    }
}
