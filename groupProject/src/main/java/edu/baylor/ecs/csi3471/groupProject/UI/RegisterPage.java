package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 *  class RegisterPage
 *  This is the UI for the RegisterPage
 */
public class RegisterPage {
	public static RegisterForm registerForm = new RegisterForm(); 				// display registration form
	public static JFrame registerPage = new JFrame("Register"); 				// register page frame with name
	/**
	 *  RegisterPage
	 */
	public RegisterPage() {
		registerPage.setExtendedState(JFrame.MAXIMIZED_BOTH); 					// make frame full screen
		registerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar registerMenuBar = new JMenuBar(); 								// title menu bar
		registerMenuBar.setOpaque(true); 										// makes title opaque
		registerMenuBar.setPreferredSize(new Dimension(200, 200)); 				// second parameter controls height
	
		JMenuItem title = new JMenuItem("REGISTRATION"); 						// title name
		title.setOpaque(true); 													// make opaque
		title.setFont(new Font("roboto condensed", Font.PLAIN, 50)); 			// font of title
		title.setForeground((Color.WHITE));
		title.setBackground(Color.decode("#051821")); 							// make title background black
		registerMenuBar.add(title); 											// add title to title menu bar
		
		final JButton submitButton = new JButton("Submit");						// when clicked the form will be checked
		submitButton.setBackground(Color.decode("#F58800"));
		submitButton.setForeground(Color.WHITE);
		submitButton.setOpaque(true);
		submitButton.setBorderPainted(false);
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent submit) {
				Runner.logger.info("User selected submit on the register form.");
				Register register = new Register();
				register.beginRegistration();	
			}
		});
		
		registerForm.add(submitButton, BorderLayout.SOUTH);
		registerPage.setJMenuBar(registerMenuBar);
		registerPage.getContentPane().add(registerForm);
		registerPage.setVisible(true);
	}
}

