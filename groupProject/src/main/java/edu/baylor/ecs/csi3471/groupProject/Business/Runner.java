package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.UI.LoginPage;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Runner {
	//Make sure it is correct
<<<<<<< HEAD
	public static Logger logger = Logger.getLogger(Runner.class.getName());
=======
	public static Logger logger = Logger.getLogger(Timer.class.getName());
	/*
>>>>>>> 56c8cfa42b7e6063804f98f4904fb14e81615701
	static {
		try {
			InputStream configFile = Runner.class.getClassLoader().getResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured");
		} catch (NullPointerException e) {
			System.out.println("WARNING: Could not open configuration file");
		}
		logger.info("starting the app");
	}

	 */
	
	public static User curUser;
	public static void main(String[] args) {
		LoginPage lp = new LoginPage();
	}
}
