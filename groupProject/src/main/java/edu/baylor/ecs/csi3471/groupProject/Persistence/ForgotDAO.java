package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class ForgotDAO
 * This is a Data Access Object meant to be used for finding password and Usernames
 */
public class ForgotDAO {
    /**
     * findPassword
     * This function creates a string userField
     *
     * @param userField entered username field
     * @return the password of the user is returned
     */
    public String findPassword(String userField) { //tested
        try {
            Scanner scanner = new Scanner(new FileReader("src/resources/UserFile.tsv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                if (data[0].equals(userField)) {
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
     * findUsername
     * this function serarches the database for the user's username
     *
     * @param email user entered email
     * @return users username is returned
     */
    public String findUsername(String email) { //tested
        try {
            Scanner scanner = new Scanner(new FileReader("src/resources/UserFile.tsv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                if (data[2].equals(email)) {
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
