
package edu.baylor.ecs.csi3471.groupProject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.baylor.ecs.csi3471.groupProject.Business.Login;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

/**
 * Class LoginPage
 */
public class LoginPage {
	public final static JFrame loginPage 	= new JFrame("Fantasy Fight Club Login");	
	public final static LoginForm loginForm = new LoginForm();

	/**
	 * LoginPage
	 * This function creates the LoginPage and is the default constructor for this class.
	 */
	public LoginPage() {
		loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// closes frame upon clicking 'x'
	    loginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);					// make full screen
	    
	    JMenuBar loginMenuBar = new JMenuBar();    							// title (menu bar because better formatting)
	    loginMenuBar.setFont(new Font("sans-serif", Font.PLAIN, 12));		// title font
	    loginMenuBar.setOpaque(true);										// make opaque
	    loginMenuBar.setPreferredSize(new Dimension(200, 200));				// second parameter controls height of frame ignore first parameter
	
	    JMenuItem title = new JMenuItem("LOGIN");							// title name
	    title.setOpaque(true);									
	    title.setFont(new Font("roboto condensed", Font.PLAIN, 50));		// title font
	    title.setBackground(Color.decode("#051821"));						// title background color
	    title.setBorderPainted(false);	
	    title.setForeground(Color.WHITE);									// title font color
	  
	    loginMenuBar.add(title);											
	
	    //final LoginForm loginForm = new LoginForm();
	    loginForm.setPreferredSize(new Dimension(2000, 180));
	    loginForm.setLayout(new GridLayout(5, 1));							// sets the number of rows, columns
	
	    JButton submitButton = new JButton("Submit");						// buttons in the login page
	    submitButton.setBackground(Color.decode("#1A4645"));
	    submitButton.setOpaque(true);										// need for mac OS
	    submitButton.setBorderPainted(false);								// need for mac OS
	    submitButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
	    submitButton.setForeground(Color.WHITE);
	   
	    JButton registerButton = new JButton("Register");
	    registerButton.setBackground(Color.decode("#266867"));
	    registerButton.setOpaque(true);
	    registerButton.setBorderPainted(false);
	    registerButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
	    registerButton.setForeground(Color.WHITE);
	    
	    JButton forgotPasswordButton = new JButton("Forgot Password");
	    forgotPasswordButton.setBackground(Color.decode("#F58800"));
	    forgotPasswordButton.setOpaque(true);
	    forgotPasswordButton.setBorderPainted(false);
	    forgotPasswordButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
	    forgotPasswordButton.setForeground(Color.WHITE);
	    
	    JButton forgotUsernameButton = new JButton("Forgot Username");
	    forgotUsernameButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
	    forgotUsernameButton.setBackground(Color.decode("#F8BC24"));
	    forgotUsernameButton.setBorderPainted(false);
	    forgotUsernameButton.setForeground(Color.WHITE);
	    forgotUsernameButton.setOpaque(true);
	
	    // confirms the users input
	    submitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent submit) {
			Runner.logger.info("User clicked submit and is attempting to login.");
	        	Login login = new Login();
	        	login.beginLoginProcess();
	        }
	    });
	
	    forgotPasswordButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent forgotPassword) {
			Runner.logger.info("User selected forgot password.");
	            ForgotPassword forgotPass = new ForgotPassword();
	            forgotPass.createAndShowGUI2();
	        }
	    });
	
	    forgotUsernameButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent forgotUsername) {
			Runner.logger.info("User selected forgot username.");
	            ForgotUsername forgotUser = new ForgotUsername();
	            forgotUser.createAndShowGUI();
	        }
	    });
	
	    registerButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent r) {
			Runner.logger.info("User selected register.");
	        	RegisterPage registerPage = new RegisterPage();
	        }
	    });
	
	    loginForm.add(submitButton);
	    loginForm.add(registerButton);
	    loginForm.add(forgotUsernameButton);
	    loginForm.add(forgotPasswordButton);
	    
	    loginPage.getContentPane().add(loginForm, BorderLayout.CENTER);
	    loginPage.setJMenuBar(loginMenuBar);
	    loginPage.setVisible(true);
	}
}

