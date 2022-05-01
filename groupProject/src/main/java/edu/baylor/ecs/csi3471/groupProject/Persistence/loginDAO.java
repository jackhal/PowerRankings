package edu.baylor.ecs.csi3471.groupProject.Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class LoginDAO
 */
public class loginDAO {
	public static String[] userData;

	/**
	 * validatePassword
	 * This function validates the password the user enters.
	 * @param username username of user
	 * @param password password of user
	 * @return asserts that the username and password match for login
	 */
	public static boolean validatePassword(String username, String password) {
    	//Scan user file, seeing if a username and password match passed
    	//parameters
    	Scanner sc;
		try {
			sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			while(sc.hasNextLine()) {
	    		data = sc.nextLine().split("\t");
	    		if(username.equals(data[0]) && password.equals(data[1])) {
	    			userData = data;
	    			return true;
	    		}
	    	}
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
    }
}
