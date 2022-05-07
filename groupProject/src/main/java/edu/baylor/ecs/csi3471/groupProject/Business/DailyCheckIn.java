package edu.baylor.ecs.csi3471.groupProject.Business;

import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;

import javax.swing.JLabel;


/**
 * DailyCheckIn class
 * This is in charge of keeping track of the current user's balance and such
 *
 * @Author: Cyril P.
 */
public class DailyCheckIn extends UserDAO {
    /**
     * showBalance()
     * This function shows the users remaining balance
     * in a JLabel element that can be added to the viewing frame.
     *
     * @param username
     * @return JLabel
     */
    public JLabel showBalance(String username) { //tested
        Runner.logger.info("starting rendering of balance");
        //declare label, current balance, and string for label
        JLabel balanceLabel;
        int currBalance = findCurrentBal(username);
        String labelString = "Balance: ";

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        String lastLog = findLastLogin(username);
        //if today is the last login, simply return the label string of current balance
        if (df.format(date).equals(lastLog)) {
            labelString += Integer.toString(currBalance) + " coins";
            //if today is not the last login
        } else {
            //check if yesterday was the last login
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1);
            date = c.getTime();
            //if it is
            if (df.format(date).equals(lastLog)) {
                int currStreak = findCurrentStreak(username);
                int reward = 50;
                for (int i = 0; i < currStreak - 1; i++) {
                    reward *= 2;
                }
                setCurrentStreak(username, currStreak + 1);
                labelString += Integer.toString(currBalance + reward) + " coins";
                setCurrentBal(username, currBalance + reward);
                //if it isn't, set current streak to 0
            } else {
                setCurrentStreak(username, 0);
                labelString += Integer.toString(currBalance) + " coins";
            }
        }

        //set last login to today
        date = new Date();
        setLastLogin(username, df.format(date));

        //create label from string and return it
        balanceLabel = new JLabel(labelString);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("sans-serif", Font.PLAIN, 10));
        Runner.logger.info("balance rendered");
        return balanceLabel;
    }
}
