package edu.baylor.ecs.csi3471.groupProject.UI;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * RegisterForm
 * This is the UI for the Register Form
 */
public class RegisterForm extends JPanel implements PropertyChangeListener {

	Integer rowSize = 40;
	// Default form data
	private String userName = "";
	private String nickName = "";
	private String age = "";
	private String email = "";
	private String password = "";

	// Labels
	private JLabel userNameLabel;
	private JLabel nickNameLabel;
	private JLabel ageLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;

	// Label names
	private static String userNameString 	= "(Mandatory) Username:  ";
	private static String nickNameString 	= "(Optional) Nickname:  ";
	private static String ageString 		= "(Mandatory) Age: ";
	private static String emailString 		= "(Mandatory) Email: ";
	private static String passwordString 	= "(Mandatory) Password: ";

	// text field for the input
	private JFormattedTextField userNameField;
	private JFormattedTextField nickNameField;
	private JFormattedTextField ageField;
	private JFormattedTextField emailField;
	private JFormattedTextField passwordField;
 
	/**
	 * getUserName
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * setUserName()
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * getNickName()
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * setNickname()
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * getAge()
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * setAge()
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * getEmail()
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setEmail()
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * getUserNameLabel()
	 * @return the userNameLabel
	 */
	public JLabel getUserNameLabel() {
		return userNameLabel;
	}


	/**
	 * setUserNameLabel()
	 * @param userNameLabel the userNameLabel to set
	 */
	public void setUserNameLabel(JLabel userNameLabel) {
		this.userNameLabel = userNameLabel;
	}

	/**
	 * getNickNameLabel()
	 * @return the nickNameLabel
	 */
	public JLabel getNickNameLabel() {
		return nickNameLabel;
	}

	/**
	 * setNickNameLabel()
	 * @param nickNameLabel the nickNameLabel to set
	 */
	public void setNickNameLabel(JLabel nickNameLabel) {
		this.nickNameLabel = nickNameLabel;
	}

	/**
	 * getAgeLabel()
	 * @return the ageLabel
	 */
	public JLabel getAgeLabel() {
		return ageLabel;
	}

	/**
	 * setAgeLabel()
	 * @param ageLabel the ageLabel to set
	 */
	public void setAgeLabel(JLabel ageLabel) {
		this.ageLabel = ageLabel;
	}

	/**
	 * getEmailLabel()
	 * @return the emailLabel
	 */
	public JLabel getEmailLabel() {
		return emailLabel;
	}

	/**
	 * setEmailLabel()
	 * @param emailLabel the emailLabel to set
	 */
	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
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
	 * getUserNameString()
	 * @return the userNameString
	 */
	public static String getUserNameString() {
		return userNameString;
	}

	/**
	 * setUserNameString()
	 * @param userNameString the userNameString to set
	 */
	public static void setUserNameString(String userNameString) {
		RegisterForm.userNameString = userNameString;
	}

	/**
	 * getNickNameString()
	 * @return the nickNameString
	 */
	public static String getNickNameString() {
		return nickNameString;
	}

	/**
	 * setNickNameString()
	 * @param nickNameString the nickNameString to set
	 */
	public static void setNickNameString(String nickNameString) {
		RegisterForm.nickNameString = nickNameString;
	}

	/**
	 * getAgeString()
	 * @return the ageString
	 */
	public static String getAgeString() {
		return ageString;
	}

	/**
	 * setAgeString()
	 * @param ageString the ageString to set
	 */
	public static void setAgeString(String ageString) {
		RegisterForm.ageString = ageString;
	}

	/**
	 * getEmailString()
	 * @return the emailString
	 */
	public static String getEmailString() {
		return emailString;
	}

	/**
	 * setEmailString()
	 * @param emailString the emailString to set
	 */
	public static void setEmailString(String emailString) {
		RegisterForm.emailString = emailString;
	}

	/**
	 * getPasswrodString()
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
		RegisterForm.passwordString = passwordString;
	}

	/**
	 * getUserNameField()
	 * @return the userNameField
	 */
	public JFormattedTextField getUserNameField() {
		return userNameField;
	}

	/**
	 * setUserNameField()
	 * @param userNameField the userNameField to set
	 */
	public void setUserNameField(JFormattedTextField userNameField) {
		this.userNameField = userNameField;
	}

	/**
	 * getNicknameField()
	 * @return the nickNameField
	 */
	public JFormattedTextField getNickNameField() {
		return nickNameField;
	}

	/**
	 * setNickNameField()
	 * @param nickNameField the nickNameField to set
	 */
	public void setNickNameField(JFormattedTextField nickNameField) {
		this.nickNameField = nickNameField;
	}

	/**
	 * getAgeField()
	 * @return the ageField
	 */
	public JFormattedTextField getAgeField() {
		return ageField;
	}

	/**
	 * setAgeField()
	 * @param ageField the ageField to set
	 */
	public void setAgeField(JFormattedTextField ageField) {
		this.ageField = ageField;
	}

	/**
	 * getEmailField()
	 * @return the emailField
	 */
	public JFormattedTextField getEmailField() {
		return emailField;
	}

	/**
	 * setEmailField()
	 * @param emailField the emailField to set
	 */
	public void setEmailField(JFormattedTextField emailField) {
		this.emailField = emailField;
	}

	/**
	 * getPasswordField()
	 * @return the passwordField
	 */
	public JFormattedTextField getPasswordField() {
		return passwordField;
	}

	/**
	 * setPasswordField()
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JFormattedTextField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * RegisterForm()
	 * This function is thedefault constructor for the class that creates the register form.
	 */
	public RegisterForm() {
		super(new BorderLayout());
		// generates all labels
		userNameLabel = new JLabel(userNameString);
		nickNameLabel = new JLabel(nickNameString);
		ageLabel = new JLabel(ageString);
		emailLabel = new JLabel(emailString);
		passwordLabel = new JLabel(passwordString);
		
		userNameLabel.setFont(new Font("sans-serif", Font.PLAIN, 10));
		userNameLabel.setForeground(Color.WHITE);
		nickNameLabel.setFont(new Font("sans-serif", Font.PLAIN, 10));
		nickNameLabel.setForeground(Color.WHITE);
		ageLabel.setFont(new Font("sans-serif", Font.PLAIN, 10));
		ageLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("sans-serif", Font.PLAIN, 10));
		emailLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("sans-serif", Font.PLAIN, 10));
		passwordLabel.setForeground(Color.WHITE);

		userNameField = new JFormattedTextField();
		userNameField.setValue(new String(userName));
		userNameField.setColumns(rowSize);
		userNameField.addPropertyChangeListener("value", (PropertyChangeListener) this);
		
		passwordField = new JFormattedTextField();
		passwordField.setValue(new String(password));
		passwordField.setColumns(rowSize);
		passwordField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		nickNameField = new JFormattedTextField();
		nickNameField.setValue(new String(nickName));
		nickNameField.setColumns(rowSize);
		nickNameField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		emailField = new JFormattedTextField();
		emailField.setValue(new String(email));
		emailField.setColumns(rowSize);
		emailField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		ageField = new JFormattedTextField();
		ageField.setValue(new String(age));
		ageField.setColumns(rowSize);
		ageField.addPropertyChangeListener("value", (PropertyChangeListener) this);

		userNameLabel.setLabelFor(userNameField);
		emailLabel.setLabelFor(emailField);
		ageLabel.setLabelFor(ageField);
		nickNameLabel.setLabelFor(nickNameField);
		passwordLabel.setLabelFor(passwordField);

		
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		pane.setBackground(Color.decode("#1A4645"));
		
		c.ipady = 12;								// vertically sizes label and text fields
		c.insets = new Insets(40,0,0,0);  			// creates vertical gap between rows 
		
		c.gridx = 0;								// row
		c.gridy = 0;								// column
		pane.add(userNameLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		pane.add(userNameField, c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		pane.add(passwordLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		pane.add(passwordField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		pane.add(nickNameLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		pane.add(nickNameField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		pane.add(emailLabel, c);
		c.gridx = 1;
		c.gridy = 3;
		pane.add(emailField, c);
		c.gridx = 0;
		c.gridy = 4;
		pane.add(ageLabel, c);
		c.gridx = 1;
		c.gridy = 4;
		pane.add(ageField, c);
		add(pane);
	}

	/**
	 * propertyChange()
	 * This function instantiates a property change.
	 * @param e
	 */
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		if (source == userNameField) {
			userName = userNameField.getText();
		} else if (source == nickNameField) {
			nickName = nickNameField.getText();
		} else if (source == emailField) {
			email = emailField.getText();
		} else if (source == ageField) {
			age = ageField.getText();
		} else if (source == passwordField) {
			password = passwordField.getText();
		}
	}
}
