package edu.baylor.ecs.csi3471.groupProject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


/**
 * class LoginForm
 * The UI for the LoginForm
 */
public class LoginForm extends JPanel implements PropertyChangeListener{
    private String username = "";
    private String password = "";

    // Labels
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    // Label names
    private static String usernameString 	= "(case sensitive) Username: ";
    private static String passwordString 	= "(case sensitive) Password: ";

    // text field for the input
    private JFormattedTextField usernameField;
    public static JPasswordField passwordField;
 
    /**
     * getUserName
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUsername
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getPassword()
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword()
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getUsernameLabel()
     * @return the usernameLabel
     */
    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    /**
     * setUsernameLabel()
     * @param usernameLabel the usernameLabel to set
     */
    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    /**
     * getPasswordLabel()
     * @return the passwordLabel
     */
    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    /**
     * setPasswordLabel()
     * @param passwordLabel the passwordLabel to set
     */
    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    /**
     * getUsernameString()
     * @return the usernameString
     */
    public static String getUsernameString() {
        return usernameString;
    }

    /**
     * setUsernameString()
     * @param usernameString the usernameString to set
     */
    public static void setUsernameString(String usernameString) {
        LoginForm.usernameString = usernameString;
    }

    /**
     * getPasswordString()
     * @return the passwordString
     */
    public static String getPasswordString() {
        return passwordString;
    }

    /**
     * setPasswordString()
     * @param passwordString the passwordString to set
     */
    public static void setPasswordString(String passwordString) {
        LoginForm.passwordString = passwordString;
    }

    /**
     * getUsernameField()
     * @return the usernameField
     */
    public JFormattedTextField getUsernameField() {
        return usernameField;
    }

    /**
     * setUsernameField()
     * @param usernameField the usernameField to set
     */
    public void setUsernameField(JFormattedTextField usernameField) {
        this.usernameField = usernameField;
    }

    /**
     * getPasswordField()
     * @return the passwordField
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * setPasswordField()
     * @param passwordField the passwordField to set
     */
    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    /**
     * LoginForm()
     * This function is the default constructor for the LoginForm class
     */
    public LoginForm() {
        super(new BorderLayout());

        usernameLabel 	= new JLabel(usernameString);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel 	= new JLabel(passwordString);
        passwordLabel.setForeground(Color.WHITE);

        usernameField 	= new JFormattedTextField();
        usernameField.setValue(new String(username));
        usernameField.setColumns(30);								// changes the width of textFields
        usernameField.addPropertyChangeListener("value", this);

        passwordField = new JPasswordField();
        passwordField.setText(new String(password));
        passwordField.setEchoChar('*');
        passwordField.setColumns(30);	
        passwordField.addPropertyChangeListener("value",this);

        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setLabelFor(passwordField);

        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
		c.gridx = 0;								// row
		c.gridy = 0;								// column
        pane.add(usernameLabel, c);
        
        c.gridx = 1;								
		c.gridy = 0;
		pane.add(usernameField, c);
		
		c.gridx = 0;								
		c.gridy = 1;
        pane.add(passwordLabel, c);
        
        c.gridx = 1;								
		c.gridy = 1;
        pane.add(passwordField, c);
        
        // creates a show password box
        c.gridx = 0;								
		c.gridy = 2;
        
		JCheckBox showPassword = new JCheckBox("Show password", false); 	// check box with label and initial set value (false == not checked)
        showPassword.setForeground(Color.WHITE);							// set check-box label to white
        
        showPassword.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {    
            	if(e.getStateChange()== 1) {
            		passwordField.setEchoChar((char)0);
            	}
            	else {
            		String contents = passwordField.getText();
            		passwordField.setEchoChar('*');
            		passwordField.setText("");
            		passwordField.setText(contents);
            	}
            }    
         });    
        
        showPassword.setBackground(Color.decode("#07566"));
        pane.add(showPassword);
        pane.setBackground(Color.decode("#07566"));
        
        add(pane);
    }

    /**
     * propertyChange()
     * This function changes the properties of the file.
     * @param e
     */
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == usernameField) {
            username = usernameField.getText();
        }
        else if (source == passwordField) {
            password = passwordField.getText();
        }
    }
}
