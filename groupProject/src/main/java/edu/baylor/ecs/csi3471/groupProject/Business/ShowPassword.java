package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.UI.LoginForm;

public class ShowPassword{

	/**
	 * @param condition sets if the password is shown
	 */
	public void handlePassword(Integer condition) {
		if(condition == 1) {
    		LoginForm.passwordField.setEchoChar((char)0);
    	}
		else {
			String contents = LoginForm.passwordField.getText();
			LoginForm.passwordField.setEchoChar('*');
			LoginForm.passwordField.setText("");
			LoginForm.passwordField.setText(contents);
		}
	}
}
