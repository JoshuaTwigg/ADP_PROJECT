package com.mycompany;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

// add loop somewhere
/**
 * Used to client an instance of a Client object. Used to send and retrieve
 * messages from a server
*
 */
public class Client {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client() {
        try {
            System.out.println("creating socket");
            socket = new Socket("127.0.0.1", 6666);
            System.out.println("socket created");
        } catch (IOException e) {
            System.out.println("Error creating socket: " + e.getMessage());
        }
        createStreams();
    }

    private void createStreams() {
        try {
            System.out.println("creating streams");
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("streams created");
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }

    public ArrayList<Object[]> readResults() {
        ArrayList<Object[]> results = new ArrayList<>();
        try {
            System.out.println("reading results");
            results = (ArrayList<Object[]>) input.readObject();
        } catch (IOException e) {
            System.out.println("Error getting results: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return results;
    }

    public void writeVehicle(String vehicle) {
        try {
            System.out.println("writing vehicle");
            output.writeObject(vehicle);
            output.flush();
        } catch (IOException e) {
            System.out.println("Error writing object: " + e.getMessage());
        }
    }

    public void communicate() {
        Scanner userIn = new Scanner(System.in);
        boolean userLeft = false;
        do {
            System.out.print("\nEnter a message: ");
            String str = userIn.nextLine();
            System.out.println(str);

            if (str.equals("exit")) {
                userLeft = true;
                System.out.println("ending loop...");
                this.close();
            } 
            else {
                // writing
                this.writeVehicle(str);

                // reading and displaying
                ArrayList<Object[]> items = this.readResults();
                System.out.println("Cars \t\t\t Price");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(items.get(i)[0] + "\t\t\t" + items.get(i)[1]);
                }
            }
        } while (!userLeft);

    }

    public void close() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Error closing soket: " + e.getMessage());
        }
    }

    
//    public static void main(String[] args) {
//        Client client = new Client();
//        
//        client.communicate();
//        
////        ArrayList<Object[]> items = client.readResults();
////        System.out.println("Cars \t\t\t Price");
////        for (int i = 0; i < items.size(); i++) {
////            System.out.println(items.get(i)[0] + "\t\t\t" + items.get(i)[1]);
////        }
////        System.out.println("\nWrite Car1");
////        client.writeVehicle("Car1");
////        items = client.readResults();
////        System.out.println("Cars \t\t\t Price");
////        for (int i = 0; i < items.size(); i++) {
////            System.out.println(items.get(i)[0] + "\t\t\t" + items.get(i)[1]);
////        }
////        System.out.println("\nWrites Car");
////        client.writeVehicle("Car");
////        items = client.readResults();
////        System.out.println("Cars \t\t\t Price");
////        for (int i = 0; i < items.size(); i++) {
////            System.out.println(items.get(i)[0] + "\t\t\t" + items.get(i)[1]);
////        }
//    }
}
