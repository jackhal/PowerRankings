package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.Persistence.loginDAO;
import edu.baylor.ecs.csi3471.groupProject.UI.HomePage;
import edu.baylor.ecs.csi3471.groupProject.UI.LoginPage;

import java.io.*;
import javax.swing.JOptionPane;

public class Login  {
	private String username = LoginPage.loginForm.getUsernameField().getText();
	private String password = LoginPage.loginForm.getPasswordField().getText();
	private String delim 	= "\t";
	
    public void beginLoginProcess() {
    	boolean fail = false;
    	try {
    		loginDAO d = new loginDAO();
    		
    		if(d.validatePassword(username, password)) {
    			LoginPage.loginPage.setVisible(false);
        		Runner.curUser = new User(d.userData);
        		
        		HomePage h = new HomePage();
        		h.createAndShowGUI(username);
        	}
        	else {
                fail = true;
        	}
        	
        } catch (NullPointerException e) { }
    	
        if(fail) {
            JOptionPane.showMessageDialog(LoginPage.loginForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
