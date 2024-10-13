
package com.mycompany;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*; 


public class GUIServer extends JFrame{
    JPanel mainPanel;
    JTextArea logArea; 
    JLabel label1; 
    JPanel panel1; 
    
    GUIServer(){
        super("Server");
        this.setFrame();
        this.initComponents();
        this.setVisible(true);
        
        Server server = new Server();
        server.process();
//        server.close();
    }
    // ------------------------------
    
    private void setFrame(){
        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }
    
    private void initComponents(){
        mainPanel = new JPanel();
        // add a scrollpane also
        logArea = new JTextArea("for logs", 4, 6);
        label1 = new JLabel("Logs");
        panel1 = new JPanel();
        
        this.add(mainPanel);
        
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.add(label1, BorderLayout.NORTH);
        this.mainPanel.add(logArea, BorderLayout.CENTER);
        this.mainPanel.add(panel1, BorderLayout.SOUTH);

        this.mainPanel.setBackground(Color.ORANGE);
        this.panel1.setBackground(Color.ORANGE);
    }
    
    // TESTING
    public static void main(String[] args){
        GUIServer server = new GUIServer();
//        server.setVisible(true);
    }
}
