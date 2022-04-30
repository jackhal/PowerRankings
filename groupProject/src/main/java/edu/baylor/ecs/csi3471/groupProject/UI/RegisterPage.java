package edu.baylor.ecs.csi3471.groupProject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RegisterPage {
	public static RegisterForm registerForm = new RegisterForm(); 				// display registration form
	public static JFrame registerPage = new JFrame("Register"); 				// register page frame with name

	public RegisterPage() {
		registerPage.setExtendedState(JFrame.MAXIMIZED_BOTH); 					// make frame full screen
		registerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar registerMenuBar = new JMenuBar(); 								// title menu bar
		registerMenuBar.setOpaque(true); 										// makes title opaque
		registerMenuBar.setPreferredSize(new Dimension(200, 200)); 				// second parameter controls height
	
		JMenuItem title = new JMenuItem("REGISTRATION"); 						// title name
		title.setOpaque(true); 													// make opaque
		title.setFont(new Font("roboto condensed", Font.PLAIN, 50)); 			// font of title
		title.setBackground(Color.BLACK); 										// make title background black
		registerMenuBar.add(title); 											// add title to title menu bar
		
		final JButton submitButton = new JButton("Submit");						// when clicked the form will be checked
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent submit) {
				Runner.logger.info("User selected submit on the register form.");
				Register register = new Register();
				register.beginRegistration();	
			}
		});
		
		submitButton.setBackground(Color.BLACK);
		registerForm.add(submitButton, BorderLayout.SOUTH);
		
		registerPage.setJMenuBar(registerMenuBar);
		registerPage.getContentPane().add(registerForm);
		registerPage.setVisible(true);
	}
}
