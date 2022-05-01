package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Persistence.ForgotDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * ForgotUserName class
 */
public class ForgotUsername extends JPanel {

    /**
     * ForgotUsername
     * This function is the default constructor for the ForgotUserName
     */
    public ForgotUsername(){

        Runner.logger.info("Forgot password has been launched");

        //JFrame frame = new JFrame("Forgot Username");				// creating instance of JFrame
        this.setSize(500, 500);									// 500 width and 500 height
        this.setLayout(new GridLayout(3, 2));
        this.setVisible(true);										// making the frame visible

        final JLabel label = new JLabel("Please enter Email:");
        label.setBounds(100, 110, 100, 40);

        final JLabel email = new JLabel("");
        final JTextField usernameField = new JTextField(30);
        final JTextField emailField = new JTextField(30);
        final JLabel username = new JLabel("");
        username.setBounds(100, 100, 100, 40);
        JButton submit = new JButton("Submit");
        submit.setBounds(50, 70, 50, 40);

        this.add(new JLabel("Email: "));
        this.add(emailField);
        //this.add(username);



        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runner.logger.info("User submitted to get their email back");

            	ForgotDAO f = new ForgotDAO();
            	username.setText(f.findUsername(emailField.getText()));
            }
        });


        this.add(submit);
        this.add(username);


    }


    /**
     * createAndShowGUI
     * creates prompt for the forgotten username prompt
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        //Create and set up the content pane.
        ForgotUsername newContentPane = new ForgotUsername();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

