package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * EditProfile
 * This class is responsible for edit profile functionality
 * @author jackh
 *
 */
public class EditProfile extends JPanel {

    /**
	 * EditProfile
     * This function is responsible for profile edit screen
     * @param username
     */
    public EditProfile(String username){
    	Runner.logger.info("displaying edit profile window");
        User user;
        
        JFrame editFrame = new JFrame("Edit Profile");
        JPanel editPanel = new JPanel();
        
		try {
			//user = getUserByUsername(username);
			user = Runner.curUser;
			
			//add labels for input fields
	        JLabel name = new JLabel("Name");
	        JLabel age = new JLabel("Age");
	        JLabel description = new JLabel("Description");
	
	        //add fields for input
	        final JTextField nameInput = new JTextField();
	        final JTextField ageInput = new JTextField();
	        final JTextField descInput = new JTextField();
	
	        //set new values
	        nameInput.setText(user.getName());
	        ageInput.setText(String.valueOf(user.getAge()));
	        descInput.setText(user.getDescription());
	
	        JButton editButton = new JButton("Save");
	
	        editButton.addActionListener(new ActionListener() {
	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(isNameValid(nameInput.getText()) && isAgeValid(ageInput.getText()) && isDescValid(descInput.getText())) {
		                user.setName(nameInput.getText());
		                user.setAge(Integer.valueOf(ageInput.getText()));
		                user.setDescription(descInput.getText());
		                
						//Database Access
						UserDAO update = new UserDAO();
	
						try {
							update.updateUser(user);
							Runner.logger.info(username + " updated their profile. New values are name: " + nameInput.getText() + 
									", age: " + ageInput.getText() + ", and description: " + descInput.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							Runner.logger.severe("Can't update " + user.getUsername());
							e1.printStackTrace();
						}
						editFrame.dispose();
	            	}else {
	            		Runner.logger.info(username + " tried to update their profile with invalid input");
	            		String errMsg = "";
	            		if(!isNameValid(nameInput.getText())) {
	            			errMsg += "Name ";
	            		}
	            		if(!isAgeValid(ageInput.getText())) {
	            			if(1 < errMsg.length()) {
	            				errMsg += " and age are";
	            			}else {
	            				errMsg += "Age ";
	            			}
	            		}
	            		if(!isDescValid(descInput.getText())) {
	            			if(8<errMsg.length()) {
	            				errMsg = "Name, age, and description are";
	            			}else if (errMsg.length() < 6){
	            				errMsg += "and description are";
	            			}else {
	            				errMsg += "Description is ";
	            			}
	            		}
	            		if(errMsg.length() < 6) {
	            			errMsg += "is";
	            		}
	            		errMsg += " invalid";
	            		JOptionPane.showMessageDialog(editFrame, errMsg,
								"ERROR", JOptionPane.ERROR_MESSAGE);
	            	}
	            }
	
	        });
	        
	        //set size of text box
	        nameInput.setColumns(30);
	        ageInput.setColumns(30);
	        descInput.setColumns(30);
	        
	        //add label and text box to panel
	        editPanel.add(name);
	        editPanel.add(nameInput);
	
	        //add label and text box to panel
	        editPanel.add(age);
	        editPanel.add(ageInput);
	
	        //add label and text box to panel
	        editPanel.add(description);
	        editPanel.add(descInput);
	
	        //add save button
	        editPanel.add(editButton);
	        
	        //add panel to frame, set visible, and set size
	        editFrame.add(editPanel);
	        editFrame.setVisible(true);
	        editFrame.setSize(325, 300);
		} catch (Exception e2) {
			Runner.logger.severe("can't get current User to edit the profile");
			e2.printStackTrace();
		}
    }
    
    /**
	 * isNameValid
     * This function is responsible for checking if the new name is valid
     * @param newName
     * @return boolean if the name inputted is valid
     */
    public boolean isNameValid(String newName) {
    	Runner.logger.info("checking new username for validity");
    	return newName != null && newName.matches("^[a-zA-Z0-9]*$") && (newName.length()<50);
    }
    
    /**
	 * isAgeValid
     * This function is responsible for checking if the new age is valid
     * @param newAge
     * @return boolean if the age inputted is a valid int
     */
    public boolean isAgeValid(String newAge) {
    	Runner.logger.info("checking new age for validity");
    	return newAge != null && newAge.matches("[0-9]+") && (18 <= Integer.parseInt(newAge)) && (Integer.parseInt(newAge) <= 200);
    }
    
    /**
	 * isDescValid
     * This function is responsible for checking if the new description is valid
     * @param newDesc
     * @return boolean whether or not the description is valid or not.
     */
    public boolean isDescValid(String newDesc) {
    	Runner.logger.info("checking new description for validity");
    	return newDesc != null && newDesc.matches("^[a-zA-Z0-9 ]*$") && (newDesc.length() < 75);
    }
}
