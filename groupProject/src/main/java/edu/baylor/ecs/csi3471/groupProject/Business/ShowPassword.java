package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.UI.LoginForm;


/**
 * Class ShowPassword
 */
public class ShowPassword{

	/**
	 * handlePassword
	 * This function processes the password.
	 * @param condition sets if the password is shown
	 */
	public void handlePassword(Integer condition) {
		if(condition == 1) {
			Runner.logger.info("User selected show password");
    		LoginForm.passwordField.setEchoChar((char)0);
    	}
		else {
			Runner.logger.info("User selected hide password");
			String contents = LoginForm.passwordField.getText();
			LoginForm.passwordField.setEchoChar('*');
			LoginForm.passwordField.setText("");
			LoginForm.passwordField.setText(contents);
		}
	}
}
