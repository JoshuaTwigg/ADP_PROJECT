
package com.mycompany;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket serverSocket;
    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Server() {
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Error creating server socket: " + e.getMessage());
        }
        listen();
        createStreams();
        writeResults();
    }

    private void listen(){
        try {
            connection = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }
    
    private void createStreams(){
        try {
            input = new ObjectInputStream(connection.getInputStream());
            output = new ObjectOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error creating streams: " + e.getMessage());
        }
    }

    private void writeResults() {
        //TODO code that retrieves results from database and put them in output stream
        try{
            output.writeObject("message out");
            System.out.println("message sent");
        }
        catch(IOException e){
            System.out.println("Error writing results: " + e.getMessage());
        }
    }
    
    private String readVehicle() {
        String vehicle = "";
        try {
            vehicle = (String) input.readObject();           
        } catch (IOException e) {
            System.out.println("Error reading vehicle: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return vehicle;
    }

    public void process(){       
        do {
            String vehicle = readVehicle();
            System.out.println(vehicle);
            
            // TODO code that increases vode of the vehicle by one or add 
            // the vehicle to the database if it doesn't exist
            writeResults(); //loads new results to the output stream
        } while (true);
    }
    
    public void close(){
        try {
            serverSocket.close();
            connection.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // TESTING
    public static void main(String[] args){
        System.out.println("SERVER");
        
        Server server = new Server();
        server.process();
        server.close();
    }
}
