package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ForgotDAO {
    /**
     * @param userField entered username field
     * @return the password of the user is returned
     */
	public String findPassword(String userField){ //tested
        try {
            Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] data = line.split("\t");
                if(data[0].equals(userField)){
                    Runner.logger.info("user was found");
                    return data[1];
                }
            }
            Runner.logger.info("user was not found");
            return "User not found";
        } catch (FileNotFoundException f) {
            Runner.logger.warning("user file was not found");
        	return "User not found";
        }
	}

    /**
     * @param email user entered email
     * @return users username is returned
     */
	public String findUsername(String email) { //tested
         try {
             Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
             while(scanner.hasNextLine()){
                 String line = scanner.nextLine();
                 String [] data = line.split("\t");
                 if(data[2].equals(email)){
                     Runner.logger.info("user was found");
                     return data[0];
                 }
             }
             Runner.logger.info("user was not found");
             return "User not found";

         } catch (FileNotFoundException f) {
             Runner.logger.warning("user file was not found");
        	 return "User not found";
         }
	}
}
