package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.UI.LoginPage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Runner {
	public static Logger logger = Logger.getLogger(Runner.class.getName());
	static {
		try {
			//InputStream configFile = Runner.class.getClassLoader().getResourceAsStream("logger.properties");
			ClassLoader classLoader = Runner.class.getClass().getClassLoader();
			InputStream configFile = classLoader.getResourceAsStream("logger.properties");


			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured");
		} catch (NullPointerException ne) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured");
		}
		logger.info("starting the app");
	}
	
	public static User curUser;
	public static void main(String[] args) {
		LoginPage lp = new LoginPage();
	}
}
