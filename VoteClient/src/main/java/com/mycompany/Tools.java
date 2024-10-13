
package com.mycompany;

import javax.swing.JOptionPane;

public class Tools {
    Tools(){}
    // ===========================================

    public static void closeApp(){
        int option = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to close the app?",
                "Close app", 
                JOptionPane.YES_NO_OPTION
        );
        // System.out.println(option);
        
        if (option == 0){
            System.exit(0);
        }
    }
    
    // ===========================================
    // TEST
//    public static void main(String[] args){
//        Tools tools = new Tools();
//
//    }

    // ===========================================

}
