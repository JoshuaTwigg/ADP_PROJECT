package com.mycompany;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUIClient extends JFrame {

    private JPanel topPanel, panel4, centerPanel, leftPanel, rightPanel, rightBottomPanel;
    private JTable table;
    private JLabel label1, exitLabel, addCarWindowLabel, addCarLabel;
    private DefaultTableModel tableModel;

    private final Client client;
    private ArrayList<Object[]> items;

    public GUIClient() {
        super("Car-Voting App");
        client = new Client();
        setGui();
    }
    // --------------------------------------

    /**
     * Used to create and organize the graphical interface for the client
    *
     */
    private void setGui() {
        System.out.println("CLIENT");
        // window; jframe 
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // ----------------------------------

        // records from the database
        items = client.readResults();

        // combobox
        JComboBox<Object> comboBox = new JComboBox<>();
        comboBox.setBorder(BorderFactory.createEmptyBorder(55, 55, 55, 55));
        comboBox.setBackground(Color.black);
        comboBox.setForeground(Color.WHITE);
        // ----------------------------------

        // table model
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name of car", "Number of votes"
                }
        );

        // adding values to combobox and jtable
        for (int i = 0; i < items.size(); i++) {
            comboBox.addItem(items.get(i)[0]);
            tableModel.addRow(items.get(i));
        }

        // jtable
        table = new JTable(tableModel);
        table.setBackground(new Color(41, 41, 41)); // Background color
        table.setForeground(Color.WHITE); // Text color
        table.setGridColor(Color.WHITE); // Grid color
        table.setSelectionBackground(new Color(100, 100, 100)); // Selection background color
        table.setSelectionForeground(Color.WHITE); // Selection text color
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Font settings

        JScrollPane scrollPane = new JScrollPane(table);
        // ----------------------------------

        // PANELS
        topPanel = new JPanel();
        topPanel.setBackground(Color.black);
        topPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Add padding

        // heading
        JLabel headingLabel = new JLabel("ADP Voting System");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);

        topPanel.add(headingLabel);

        centerPanel = new JPanel(new GridLayout(1, 2));
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        rightBottomPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.white);
        rightPanel.setLayout(new BorderLayout());
        rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.Y_AXIS));

        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 3));
        // ----------------------------------

        // BUTTONS
        // Vote Button
        label1 = new JLabel("Vote");
        label1.setFont(new Font("Arial", Font.PLAIN, 24));
        label1.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label1.setPreferredSize(new Dimension(30, 30)); // Set preferred size
        label1.setForeground(Color.WHITE);
        label1.setBackground(Color.BLACK);
        label1.setOpaque(true);
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Vote Added");
                String selectedItem = (String) comboBox.getSelectedItem();
                client.writeVehicle(selectedItem);

                // clear jtable
                tableModel.setNumRows(0);

                // refresh data in jtable
                items = client.readResults();
                for (int i = 0; i < items.size(); i++) {
                    tableModel.addRow(items.get(i));
                }

            }

            // --hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setBackground(new Color(160, 40, 60)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label1.setBackground(Color.BLACK); // Revert to original color
            }
        });

        // Exit button
        exitLabel = new JLabel("Exit");
        exitLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        exitLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text
        exitLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        exitLabel.setPreferredSize(new Dimension(30, 30));
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setBackground(Color.BLACK);
        exitLabel.setOpaque(true);
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tools.closeApp();
            }

            // --hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                exitLabel.setBackground(new Color(160, 40, 60)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitLabel.setBackground(Color.BLACK); // Revert to original color
            }
        });

        // Add-car button
        addCarWindowLabel = new JLabel("Add car");
        addCarWindowLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        addCarWindowLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text
        addCarWindowLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        addCarWindowLabel.setPreferredSize(new Dimension(30, 30));
        addCarWindowLabel.setForeground(Color.WHITE);
        addCarWindowLabel.setBackground(Color.BLACK);
        addCarWindowLabel.setOpaque(true);
        addCarWindowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // CREATING ANOTHER WINDOW
                // jframe
                JFrame newWindow = new JFrame("Add a car");
                newWindow.setLayout(new BorderLayout());

                // jpanel
                JPanel newWindowPanel = new JPanel();
                newWindowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                // textfield
                JTextField textField = new JTextField("");
                textField.setPreferredSize(new Dimension(300, 40));
                textField.setBorder(new EmptyBorder(0, 10, 0, 10));
                textField.setFont(new Font("Arial", Font.PLAIN, 16));

                // button
                addCarLabel = new JLabel("Add Car");
                addCarLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                addCarLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text
                addCarLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
                addCarLabel.setPreferredSize(new Dimension(100, 30));
                addCarLabel.setForeground(Color.WHITE);
                addCarLabel.setBackground(Color.BLACK);
                addCarLabel.setOpaque(true);
                addCarLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String selectedItem = textField.getText().replaceAll(" ", "");
                        String newCar = Tools.capitalize(selectedItem);

                        // clear textfield
                        textField.setText("");

                        // checking if car already exists in database
                        boolean carExists = false;
                        for (int i = 0; i < items.size(); i++) {
                            if (newCar.equals(items.get(i)[0])) {
                                carExists = true;
                            }
                        }

                        if (!carExists) {
                            int option = JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure you want to add this car",
                                    "Add car?",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (option == 0) {
                                JOptionPane.showMessageDialog(null, ("'" + newCar + "' will be added"));
                            client.writeVehicle(newCar);

                            // updating items
                            items = client.readResults();

                            // updating the jtable
                            tableModel.addRow(new Object[]{newCar, 0});

                            // updating the combobox
                            comboBox.addItem(newCar);
                            
                            System.out.println("\n<Car added>");
                            }

                            
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    ("'" + newCar + "' already exists"),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }

                    // --hover effect
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        addCarLabel.setBackground(new Color(160, 40, 60)); // Change color on hover
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        addCarLabel.setBackground(Color.BLACK); // Revert to original color
                    }
                });

                // adding to jpanel
                newWindowPanel.setBackground(Color.black);
                newWindowPanel.add(textField);
                newWindowPanel.add(addCarLabel);

                // jframe; customising 
                newWindow.setBackground(Color.black);
                newWindow.setSize(400, 250);
                newWindow.setResizable(false);
                newWindow.setLocationRelativeTo(GUIClient.this);
                newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newWindow.setVisible(true);

                newWindow.add(newWindowPanel, BorderLayout.CENTER);
            }

            // --hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                addCarWindowLabel.setBackground(new Color(160, 40, 60)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addCarWindowLabel.setBackground(Color.BLACK); // Revert to original color
            }
        });
        // ----------------------------------

        // images
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/carOne.png")); // must get from resource folder within project.
        ImageIcon imageIconTwo = new ImageIcon(getClass().getResource("/carTwo.png")); // must get from resource folder within project.

        ImageIcon resizedImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(500, 320, java.awt.Image.SCALE_SMOOTH));
        ImageIcon resizedImageIconTwo = new ImageIcon(imageIconTwo.getImage().getScaledInstance(500, 320, java.awt.Image.SCALE_SMOOTH));

        JLabel imageLabel = new JLabel(resizedImageIcon);
        JLabel imageLabelTwo = new JLabel(resizedImageIconTwo);

        imageLabel.setBorder(new EmptyBorder(0, 0, 10, 0)); // SET SPACE BETWEEN IMAGES Y AXIS perfecto
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(imageLabel);
        leftPanel.add(imageLabelTwo);
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // add left panel that contains 2 images
        centerPanel.add(leftPanel);
        // ----------------------------------

        // other panels
        panel4.add(label1);
        panel4.add(addCarWindowLabel);
        panel4.add(exitLabel);

        rightBottomPanel.add(comboBox);//add buttons and combobox to right bottom panel
        rightBottomPanel.add(panel4);

        rightPanel.setBackground(Color.black);
        rightPanel.add(scrollPane, BorderLayout.NORTH);
        rightPanel.add(rightBottomPanel, BorderLayout.SOUTH);

        centerPanel.add(rightPanel);

        // set entire frame to borderlayout
        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // repacks everything to fit, fixed non display until maximise
        pack();
    }
    // --------------------------------------

    // TESTING
    public static void main(String[] args) {
        GUIClient clientGUI = new GUIClient();
    }
}
