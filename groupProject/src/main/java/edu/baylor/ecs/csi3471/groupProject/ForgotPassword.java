package edu.baylor.ecs.csi3471.groupProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ForgotPassword extends JPanel {
    public ForgotPassword(){

        //JFrame frame = new JFrame("Forgot Username");				// creating instance of JFrame
        this.setSize(500, 500);									// 500 width and 500 height
        this.setLayout(new GridLayout(3, 2));
        this.setVisible(true);										// making the frame visible

        final JLabel label = new JLabel("Please enter Username:");
        label.setBounds(100, 110, 100, 40);

        final JLabel user = new JLabel("");
        final JTextField usernameField = new JTextField(30);
        final JTextField userField = new JTextField(30);
        final JLabel password = new JLabel("");
        user.setBounds(100, 100, 100, 40);
        JButton submit = new JButton("Submit");
        submit.setBounds(50, 70, 50, 40);

        this.add(new JLabel("Username: "));
        this.add(userField);
        //this.add(username);



        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean found = false;
                try {
                    Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
                    while(scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        String [] data = line.split("\t");
                        if(data[0].equals(userField.getText())){
                            password.setText(data[1]);
                            found = true;
                        }
                    }
                    if(!found){
                        password.setText("User not found");
                    }

                } catch (FileNotFoundException f) {
                    //idk anymore
                }
            }
        });


        this.add(submit);
        this.add(password);


    }



    protected void createAndShowGUI2() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        //Create and set up the content pane.
        ForgotPassword newContentPane = new ForgotPassword();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}