import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Login {
	
	public static void beginLoginProcess() {
		
		final JFrame loginPage = new JFrame("Login");						// login page frame with name
		loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// closes frame upon clicking 'x'
		loginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);					// make frame full screen
		
		JMenuBar loginMenuBar = new JMenuBar();    							// title menu bar
		loginMenuBar.setFont(new Font("sans-serif", Font.PLAIN, 12));
		loginMenuBar.setOpaque(true);										// make opaque
		loginMenuBar.setPreferredSize(new Dimension(200, 200));				// second parameter controls height
	    		
	    JMenuItem title = new JMenuItem("LOGIN");							// title name
	    title.setOpaque(true);												// make opaque
	    title.setFont(new Font("roboto condensed", Font.PLAIN, 50));		// font of title
	    title.setBackground(Color.BLACK);									// make title background black
	    loginMenuBar.add(title);											// add title to title menu bar
	    
	    final LoginForm loginForm = new LoginForm();			
	    loginForm.setPreferredSize(new Dimension(2000, 180));
	    loginForm.setLayout(new GridLayout(5, 1));							// sets the number of rows, columns 
		
	    JButton submitButton = new JButton("Submit");						// buttons in the login page
	    submitButton.setBackground(Color.black);
	    JButton registerButton = new JButton("Register");
	    registerButton.setBackground(Color.black);
	    JButton forgotPasswordButton = new JButton("Forgot Password");
	    forgotPasswordButton.setBackground(Color.black);
	    JButton forgotUsernameButton = new JButton("Forgot Username");
	    forgotUsernameButton.setBackground(Color.black);
	    
	    // confirms the users input
	    submitButton.addActionListener(new ActionListener() {				
			
	    	public void actionPerformed(ActionEvent submit) {
	    		boolean fail = false;

				if (loginForm.getUsernameField().getText().isEmpty()) {
					fail = true;
				} 
				else {
					validateUsername(loginForm.getUsernameField().getText());
				}
				if(loginForm.getPasswordField().getText().isEmpty()) {
					fail = true;
				}
				else {
					validatePassword(loginForm.getPasswordField().getText());
				}
				
				if(fail) {
					JOptionPane.showMessageDialog(loginForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
				}
	    	}
	    });
	    
	    
	    forgotPasswordButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent forgotPassword) {
	    		JFrame frame = new JFrame("Forgot Password");				// creating instance of JFrame
	    	    frame.setSize(500, 500);									// 500 width and 500 height
	    	    frame.setLayout(new GridLayout(3, 2));		
	    	    frame.setVisible(true);										// making the frame visible
	    	       
	    	    final JLabel label = new JLabel("Please enter username: ");
	    	    label.setBounds(100, 110, 100, 40);
	    	    
	    	    JButton submit = new JButton("Submit");						// creating instance of JButton
	    	    submit.setBounds(100, 70, 100, 40);							// x axis, y axis, width, height
	    	    
	    	    final JLabel username = new JLabel("");
	    	    final JTextField emailField = new JTextField(30);
	    	    
	    	    submit.addActionListener(new ActionListener() {
	                //@Override
	                public void actionPerformed(ActionEvent e) {
	                    Boolean found = false;
	                    
	                    try {
	                    	// THIS SHOULD BE THE DATABASE
	                        Scanner scanner = new Scanner(new FileReader("UserFile.csv"));
	                        while(scanner.hasNextLine()){
	                            String line = scanner.nextLine();
	                            String [] data = line.split(";");
	                            if(data[2].equals(emailField.getText())){
	                                username.setText(data[0]);
	                                found = true;
	                            }
	                        }
	                        if(!found){
	                            username.setText("User not found");
	                        }

	                    } catch (FileNotFoundException f) {
	                       // BAD FILE
	                    }
	                }
	            });

	    	    frame.add(label);
	    	    frame.add(emailField);
	    	    frame.add(submit);							// adding button in JFrame
	    	 
	    	  }
	    });
	    
	    forgotUsernameButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent forgotUsername) {
	    		JFrame frame = new JFrame("Forgot Username");				// creating instance of JFrame
	    	    frame.setSize(500, 500);									// 500 width and 500 height
	    	    frame.setLayout(new GridLayout(3, 2));		
	    	    frame.setVisible(true);										// making the frame visible
	    	       
	    	    final JLabel label = new JLabel("Please enter Email:");
	    	    label.setBounds(100, 110, 100, 40);
	    	    
	    	    JButton submit = new JButton("Submit");						// creating instance of JButton
	    	    submit.setBounds(100, 70, 100, 40);							// x axis, y axis, width, height
	    	    
	    	    final JLabel email = new JLabel("");
	    	    final JTextField usernameField = new JTextField(30);
	    	    
	    	    submit.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    Boolean found = false;
	                    
	                    // This will have to read from the database some information
	                    /*
	                    try {
	                   
	                        // THIS WILL BE THE DATABASE
	                    	Scanner scanner = new Scanner(new FileReader("UserFile.csv"));
	                        while(scanner.hasNextLine()){
	                            
	                        	String line = scanner.nextLine();
	                            String [] data = line.split(";");
	                            
	                            if(data[2].equals(usernameField.getText())){
	                                email.setText(data[0]);
	                                found = true;
	                            }
	                        }
	                        if(!found){
	                            email.setText("Email not found");
	                        }

	                    } catch (FileNotFoundException f) {
	                       // BAD FILE
	                    }*/
	                }
	            });

	    	    frame.add(label);
	    	    frame.add(usernameField);
	    	    frame.add(submit);							// adding button in JFrame
	    	 
	    	  }
	    });
	    
	    
	    registerButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent r) {
	    		Register register = new Register();
				register.beginRegistration();
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
	
	// TODO FINISH ALL FUNCTIONS BELOW must have access to database to see if the person exists
	static void validateUsername(String username) {
		boolean pass = false;
		
		if(pass == true) {
			
		}
		else {
			validateUserEmail();
		}
	}
	
	// TODO
	static void validatePassword(String userId) {
		boolean pass = false;
		
		if(pass == true) {
			
		}
		else {
			validateUserEmail();
		}
		
	}
	
	static // TODO
	void validateUserEmail() {
		boolean pass = false;
		Integer attempts = 0;
		
		while(attempts < 3 && !pass) {
			
			// call user class or database to get unique ID
			
			if(pass == true) {
			
			}
		
			else {
				JOptionPane.showMessageDialog(null,"Invalid email","ERROR", JOptionPane.ERROR_MESSAGE);
				attempts++;
			}
		}
	}
	
	// TODO
	void endLoginProcess() {
		
	}
	
	
	void activeUser(String userId) {
		
		// sets the user to active 
	}
	
	
	void searchDataBase() {
		
	}
	
}