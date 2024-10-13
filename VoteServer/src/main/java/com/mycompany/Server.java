
package com.mycompany;

import com.mycompany.databaseControl.ServerDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    private ServerSocket serverSocket;
    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerDAO serverDAO;

    public Server() {
        serverDAO = new ServerDAO();
        try {
            System.out.println("Opening server");
            serverSocket = new ServerSocket(6666);
            System.out.println("Server opened");
        } catch (IOException e) {
            System.out.println("Error creating server socket: " + e.getMessage());
        }
        listen();
        createStreams();
        writeResults();
    }

    private void listen(){
        try {
            System.out.println("listening for connection");
            connection = serverSocket.accept();
            System.out.println("connection accepted");
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }
    
    private void createStreams(){
        try {
            System.out.println("streams being created");
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            System.out.println("streams created");
        } catch (IOException e) {
            System.out.println("Error creating streams: " + e.getMessage());
        }
    }

    private void writeResults() { //TODO code that updates server log
        ArrayList<Object[]> results = serverDAO.readRecords();
        try{
            System.out.println("writing to client...");
            output.writeObject(results);
            output.flush();
        }
        catch(IOException e){
            System.out.println("Error writing results: " + e.getMessage());
        }
    }
    
    private String readVehicle() {
        String vehicle = "";
        try {
            System.out.println("\nreading from client...");
            vehicle = (String) input.readObject();    
            System.out.println(vehicle);
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
            if (vehicle.equals("")) {
                break;
            }
            serverDAO.process(vehicle); //TODO code that updates server log
            writeResults();
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
    
//    public static void main(String[] args) {
//        Server server = new Server();
//        server.process();
//    }
}
