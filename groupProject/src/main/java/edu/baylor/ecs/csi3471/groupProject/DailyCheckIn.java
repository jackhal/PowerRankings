package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;


public class DailyCheckIn extends UserDAO {
	protected JLabel showBalance(String username) {
		/*JLabel balanceLabel;
		String bal = "Balance: ";
		bal += Integer.toString(findCurrentBal(username));
		bal += " coins";

		balanceLabel = new JLabel(bal);

		return balanceLabel;*/
		//declare label, current balance, and string for label
		JLabel balanceLabel;
		int currBalance = findCurrentBal(username);
		String labelString = "Balance: ";
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
		Date date = new Date();
		String lastLog = findLastLogin(username);
		//if today is the last login, simply return the label string of current balance
		if(df.format(date).equals(lastLog)) {
			labelString += Integer.toString(findCurrentBal(username)) + " coins";
		//if today is not the last login
		}else {
			//check if yesterday was the last login
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			date = c.getTime();
			//if it is
			if(df.format(date).equals(lastLog)) {
				int currStreak = findCurrentStreak(username);
				int reward = 50;
				for(int i=0; i<currStreak - 1; i++) {
					reward *= 2;
				}
				setCurrentStreak(username, currStreak + 1);
				labelString += Integer.toString(findCurrentBal(username) + reward) + " coins";
				setCurrentBal(username, findCurrentBal(username) + reward);
			//if it isn't, set current streak to 0
			}else {
				setCurrentStreak(username, 0);
				labelString += Integer.toString(findCurrentBal(username)) + " coins";
			}
		}

		//set last login to today
		date = new Date();
		setLastLogin(username, df.format(date));
		
		//create label from string and return it
		balanceLabel = new JLabel(labelString);
		return balanceLabel;
	}
}
