
package com.mycompany;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 Comments
 - table should only have 2 columns
 - need to add buttons; exit, add car, vote, update table (or create an auto-refresh functionality)
 - remove images
 - 
**/

public class GUIClient extends JFrame implements MouseListener{
    private JPanel topPanel,
            panel4,
            centerPanel,
            leftPanel, 
            rightPanel, 
            rightBottomPanel;
    
    private JTable table;
    
    private JLabel label1, 
            label2, 
            label3;
    
    public GUIClient() {
        super("Car-Voting App");
        setGui();
        
        // client connection
        Client client = new Client();
        client.communicate();
//        client.readResults(); 
//        client.writeVehicle("vehicle text");
    }
    // --------------------------------------

    
    public void setGui(){
        // window 
        setSize(800, 800);   
        //center
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setVisible(true);
        setResizable(false);
        // ----------------------------------

        // combo box
        String[] options = {"Option 1", "Option 2", "Option 3"};
       
        JComboBox<String> comboBox = new JComboBox<>(options);  
        comboBox.setBorder(BorderFactory.createEmptyBorder(55, 55, 55, 55));
        Color comboBoxColor = new Color(41, 41, 41); // Dark color
        comboBox.setBackground(comboBoxColor);
        comboBox.setForeground(Color.WHITE);
        // ----------------------------------

        // table
        String[][] data = {
            {"car", "1"},
            {"car2", "2"}        
        };
        // change to int? 
        
        String[] columnNames = {"Name of car", "Number of votes"};

        
        table = new JTable(data, columnNames);
        
        table.setBackground(new Color(41, 41, 41)); // Background color
        table.setForeground(Color.WHITE); // Text color
        table.setGridColor(Color.WHITE); // Grid color
        table.setSelectionBackground(new Color(100, 100, 100)); // Selection background color
        table.setSelectionForeground(Color.WHITE); // Selection text color
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Font settings
           
        JScrollPane scrollPane = new JScrollPane(table);
        // ----------------------------------

        // panels
        topPanel = new JPanel();
        topPanel.setBackground(new Color(41, 41, 41));
        topPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Add padding
        JLabel headingLabel = new JLabel("ADP Voting System");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);
        topPanel.add(headingLabel);
        
        centerPanel = new JPanel(new GridLayout(1, 2)); // Split into two columns  
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1,2));
        
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(41, 41, 41));
        rightPanel.setLayout(new BorderLayout());
        
        rightBottomPanel = new JPanel();
        rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.Y_AXIS));
        // ----------------------------------

        // buttons
        // Button 1
        // Vote Button
        label1 = new JLabel("Vote");
        label1.setFont(new Font("Arial", Font.PLAIN, 24)); 
        label1.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label1.setPreferredSize(new Dimension(30,30)); // Set preferred size
        label1.setForeground(Color.WHITE);
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Vote Added!");
                String selectedItem = (String) comboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null, "You selected: " + selectedItem);
            }
        });

        //Button 2
        label2 = new JLabel("Exit");
        label2.setFont(new Font("Arial", Font.PLAIN, 24)); 
        label2.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label2.setPreferredSize(new Dimension(30, 30)); 
        label2.setForeground(Color.WHITE);
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                System.out.println("\n<program closed by user>");
            }
        });
        
        //Extra button, not used.
        label3 = new JLabel("Label 3");
        label3.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label3.setPreferredSize(new Dimension(50, 50)); 
        // ----------------------------------

                    
//        ImageIcon imageIcon = new ImageIcon("E:/Users/User/Documents/NetBeansProjects/ADPVotingSystem/src/main/java/image/carOne.png");
//        ImageIcon imageIconTwo = new ImageIcon("E:/Users/User/Documents/NetBeansProjects/ADPVotingSystem/src/main/java/image/carTwo.png");
//          
//        ImageIcon resizedImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(400, 220, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon resizedImageIconTwo = new ImageIcon(imageIconTwo.getImage().getScaledInstance(400, 220, java.awt.Image.SCALE_SMOOTH));
//   
//        JLabel imageLabel = new JLabel(resizedImageIcon);
//
//        JLabel imageLabelTwo = new JLabel(resizedImageIconTwo);
//        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        imageLabelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ----------------------------------

        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//        leftPanel.add(imageLabel);
//        leftPanel.add(imageLabelTwo);
        
        centerPanel.add(leftPanel);
      
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightBottomPanel.add(comboBox);
        rightBottomPanel.add(panel4);
        
   
        rightPanel.add(rightBottomPanel, BorderLayout.SOUTH);
        centerPanel.add(rightPanel);
    
        
        panel4.add(label1);
        panel4.add(label2);
        // ----------------------------------

        setLayout(new BorderLayout());
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
       
        // repacks everything to fit, fixed non display until maximise
        pack();
    }
    // --------------------------------------

    // EVENTS
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // --------------------------------------
    
    // TESTING
    public static void main(String[] args){
       GUIClient clientGUI = new GUIClient();
       
       
////        ArrayList<Object> r = client.readResults(); // ammars was : String r = client.readResults(); arraylist error cnnot be converted to string
////        System.out.println(r);
//        
//        client.close();
    }
}
