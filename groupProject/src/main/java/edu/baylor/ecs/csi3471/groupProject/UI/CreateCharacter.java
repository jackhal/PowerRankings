package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Persistence.CharacterDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

/**
 * Class responsible for character creation screen
 * and character creation user input validation
 * @author jackh
 *
 */
public class CreateCharacter extends CharacterDAO {
	/**
	 * createAndShowGUI
	 * Function responsible for character creation screen
	 * @param currUsername
	 */
	protected void createAndShowGUI(String currUsername) {
		Runner.logger.info("displaying create character window");
		//declaration of variables
		JFrame createFrame;
		JLabel nameLabel, worldLabel, descLabel, urlLabel;
		JTextField nameField, worldField, descField, urlField;
		JPanel createPanel;
		JButton createButton;
		
		//initialization of character creation frame, panel, and button
		createFrame = new JFrame("Create Character");
		createPanel = new JPanel();
		createButton = new JButton("Create");
		
		//initialization of name label and field
		nameLabel = new JLabel("Name:           ");
		nameField = new JTextField();
		nameField.setColumns(30);
		
		//initialization of world label and field
		worldLabel = new JLabel("World:          ");
		worldField = new JTextField();
		worldField.setColumns(30);
		
		//initialization of desc label and field
		descLabel = new JLabel("Description:");
		descField = new JTextField();
		descField.setColumns(30);
		
		//initialization of URL label and field
		urlLabel = new JLabel("Photo URL:  ");
		urlField = new JTextField();
		urlField.setColumns(30);
		
		//adding listeners
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String errorMsg = "Invalid ";
				try {
					//check for any errors
					if(!isValidCharName(nameField.getText())||!isValidWorld(worldField.getText())||
							!isValidCharDesc(descField.getText())||!isValidCharURL(urlField.getText())) {
						//check if name is valid, if it isn't update error message
						if(!isValidCharName(nameField.getText())) {
							errorMsg += "character name, ";
						}
						//check if world is valid, if it isn't update error message
						if(!isValidWorld(worldField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg += "and ";
							}
							errorMsg += "world name, ";
						}
						//check if description is valid, if it isn't update error message
						if(!isValidCharDesc(descField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg = errorMsg.replaceAll("and ", "");
								errorMsg += "and ";
							}
							errorMsg += "character description, ";
						}
						//check if URL is valid, if it isn't update error message
						if(!isValidCharURL(urlField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg = errorMsg.replaceAll("and ", "");
								errorMsg += "and ";
							}
							errorMsg += "picture URL";
						}
						//remove any trailing commas
						if(errorMsg.substring(errorMsg.length()-2, errorMsg.length()).equals(", ")) {
							errorMsg = errorMsg.substring(0, errorMsg.length()-2);
						}
						//throw error with correct message
						JOptionPane.showMessageDialog(createFrame, errorMsg,"ERROR", JOptionPane.ERROR_MESSAGE);
					//if no errors, write to file
					}else {
						if(doesCharExist(nameField.getText(), worldField.getText())) {
							JOptionPane.showMessageDialog(createFrame, "Character already exists in chosen world",
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}else {
							Runner.logger.info(currUsername + " created a new character with data name: " + 
									nameField.getText() + ", world: " + worldField.getText() + ", description: "
									+ descField.getText() + ", and URL: " + urlField.getText());
							addCharacter(nameField.getText(), worldField.getText(), descField.getText(),
									urlField.getText(), currUsername);
							createFrame.dispose();
						}
					}
				} catch (NullPointerException n) {
					n.printStackTrace();
				}
			}
		});
		//JOptionPane.showMessageDialog(loginForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
		
		//adding elements
		createPanel.add(nameLabel);
		createPanel.add(nameField);
		
		createPanel.add(worldLabel);
		createPanel.add(worldField);
		
		createPanel.add(descLabel);
		createPanel.add(descField);
		
		createPanel.add(urlLabel);
		createPanel.add(urlField);
		
		createPanel.add(createButton);
		
		createFrame.add(createPanel);
		
		//setting visible
		createFrame.setSize(400, 300);
		createFrame.setVisible(true);
		Runner.logger.info("create character screen displayed");
	}

	
	/**
	 * isValidCharName
	 * This function is responsible for checking if the input character name is valid
	 * @param name
	 * @return
	 */
	public boolean isValidCharName(String name) {
		//checking if character name length is valid
		if(49<name.length()||name.length()==0) {
			return false;
		}
		//checking if character name contains any banned characters
		String spec = "!?.@#$%&*()'+,-/:;<=>[]^_`{|}";
		char ch;
		for(int i=0; i<name.length(); i++) {
			ch = name.charAt(i);
			if(spec.contains(String.valueOf(ch))) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * isValidWorld
	 * This function is responsible for checking if the input world is valid
	 * @param world
	 * @return
	 */
	public boolean isValidWorld(String world) {
		//checking if character world length is valid
		if(49<world.length()||world.length()==0) {
			return false;
		}
		//checking if character world contains any banned characters
		String spec = "@#$%&*()+,-/:;<=>[]^_`{|}";
		char ch;
		for(int i=0; i<world.length(); i++) {
			ch = world.charAt(i);
			if(spec.contains(String.valueOf(ch))) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * isValidCharDesc
	 * This function is responsible for checking if the input description is valid
	 * @param desc
	 * @return
	 */
	public boolean isValidCharDesc(String desc) {
		//checking if character description length is valid
		if(299<desc.length()||desc.length()==0) {
			return false;
		}
		//checking if character description contains any banned characters
		String spec = "@#$%&*()+,-/:;<=>[]^_`{|}";
		char ch;
		for(int i=0; i<desc.length(); i++) {
			ch = desc.charAt(i);
			if(spec.contains(String.valueOf(ch))) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * isValidCharURL
	 * This function is responsible for checking if the input URL is valid
	 * @param URL
	 * @return
	 */
	public boolean isValidCharURL(String URL) {
		//checking if URL is within required length
		if(299<URL.length()) {
			return false;
		}
		//check if it is a valid URL
		try {
			new URL(URL).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}
