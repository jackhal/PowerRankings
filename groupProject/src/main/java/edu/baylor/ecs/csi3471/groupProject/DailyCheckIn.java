package edu.baylor.ecs.csi3471.groupProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;

public class DailyCheckIn extends UserDAO{
	protected JLabel showBalance(String username) {
		JLabel balanceLabel;
		String bal = "Balance: ";
		bal += Integer.toString(findCurrentBal(username));
		bal += " coins";

		balanceLabel = new JLabel(bal);

		return balanceLabel;
	}

	protected void updateBalance(User user){

	}
}
