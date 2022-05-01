package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.Persistence.loginDAO;
import edu.baylor.ecs.csi3471.groupProject.UI.HomePage;
import edu.baylor.ecs.csi3471.groupProject.UI.LoginPage;

import java.io.*;
import javax.swing.JOptionPane;

/**
 * Class Login
 */
public class Login  {
	private String username = LoginPage.loginForm.getUsernameField().getText();
	private String password = LoginPage.loginForm.getPasswordField().getText();
	private String delim 	= "\t";

	/**
	 * beginLoginProcess
	 * This function starts the login process
	 * in which the user starts use of the application.
	 */
    public void beginLoginProcess() {
    	boolean fail = false;
    	try {
    		loginDAO d = new loginDAO();
    		
    		if(d.validatePassword(username, password)) {
    			LoginPage.loginPage.setVisible(false);
        		Runner.curUser = new User(d.userData);
				Runner.logger.info(Runner.curUser.getUsername() + " signed in");
        		
        		HomePage h = new HomePage();
        		h.createAndShowGUI(username);
        	}
        	else {
                fail = true;
				Runner.logger.info("failed to sign in with User: " + username + ", password: " + password);
        	}
        	
        } catch (NullPointerException e) { }
    	
        if(fail) {
            JOptionPane.showMessageDialog(LoginPage.loginForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
