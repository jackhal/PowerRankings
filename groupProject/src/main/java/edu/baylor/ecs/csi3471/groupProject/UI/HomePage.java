package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.DailyCheckIn;

import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomePage {
	static String currUsername;

	/**
	 * @param username current user passed from login creates the homepage of the
	 *                 app
	 */
	public static void createAndShowGUI(String username) {
		currUsername = username;
		
		// create mainFrame
		JFrame mainFrame = new JFrame("Power Rankings");
		//mainFrame.setLayout(new GridLayout(6, 0));
		mainFrame.setSize(900, 800);

		JPanel menuPanel = addMenu();
		menuPanel.setBounds(0, 0, 900, 60);
		menuPanel.setLayout(new GridLayout());
		menuPanel.setBackground(Color.decode("#032930"));
		mainFrame.add(menuPanel);

		TournamentBracketPanel f 	= new TournamentBracketPanel();
		JLayeredPane layered 		= f.getBracket();
		
		//mainFrame.setLayout(new BorderLayout());

		layered.setBackground(Color.decode("#051821"));
		layered.setOpaque(true);
		
		mainFrame.add(layered);

		// mainFrame.setLayout(g);
		// set mainFrame to true
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected static JPanel addMenu() {
		// variable declarations
		JPanel menuPanel;
		final JButton editProfile, charSearch, leaderboard, currentRound, createChar, cancelBet;

		// variable initialization
		menuPanel = new JPanel();

		editProfile = new JButton("Edit Profile");
		editProfile.setFont(new Font("roboto condensed", Font.PLAIN, 10)); // title font
		editProfile.setBackground(Color.decode("#053943")); // title background color
		editProfile.setBorderPainted(false);
		editProfile.setForeground(Color.WHITE);
		editProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EditProfile ep = new EditProfile(currUsername);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		editProfile.setFocusPainted(false);

		charSearch = new JButton("Characters");
		charSearch.setBackground(Color.decode("#1A4645"));
		charSearch.setOpaque(true); // need for mac OS
		charSearch.setBorderPainted(false); // need for mac OS
		charSearch.setFont(new Font("sans-serif", Font.PLAIN, 10));
		charSearch.setForeground(Color.WHITE);
		charSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Table t = new Table();
				t.createAndShowGUI();
			}
		});
		charSearch.setFocusPainted(false);

		leaderboard = new JButton("View Leaderboards");
		leaderboard.setFocusPainted(false);
		leaderboard.setBackground(Color.decode("#266867"));
		leaderboard.setOpaque(true);
		leaderboard.setBorderPainted(false);
		leaderboard.setFont(new Font("sans-serif", Font.PLAIN, 10));
		leaderboard.setForeground(Color.WHITE);

		// Create Character button listener
		createChar = new JButton("Create Character");
		createChar.setBackground(Color.decode("#F8BC24"));
		createChar.setOpaque(true);
		createChar.setBorderPainted(false);
		createChar.setFont(new Font("sans-serif", Font.PLAIN, 10));
		createChar.setForeground(Color.WHITE);
		createChar.setFocusPainted(false);
		createChar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateCharacter c = new CreateCharacter();
				c.createAndShowGUI(currUsername);
			}
		});

		// leaderboard action listener
		leaderboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// variable declaration
				JFrame selectLead;
				JPanel buttonPanel;
				JButton charLead, userLead;

				// variable initialization
				selectLead = new JFrame("Select Leaderboard");
				buttonPanel = new JPanel();

				charLead = new JButton("Character Leaderboard");
				charLead.setFocusPainted(false);

				userLead = new JButton("User Leaderboard");
				userLead.setFocusPainted(false);

				// char leaderboard action listener
				charLead.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Table t = new Table();
						t.createAndShowGUI();
					}
				});

				// user leaderboard action listener
				userLead.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						UserTable ut = new UserTable();
						ut.createAndShowUserGUI();
					}
				});

				// add items to JFrame
				buttonPanel.add(charLead);
				buttonPanel.add(userLead);

				selectLead.add(buttonPanel);
				selectLead.setSize(350, 75);
				selectLead.setVisible(true);
			}
		});

		currentRound = new JButton("Current Round");
		currentRound.setBackground(Color.decode("#07566"));
		currentRound.setOpaque(true);
		currentRound.setBorderPainted(false);
		currentRound.setFont(new Font("sans-serif", Font.PLAIN, 10));
		currentRound.setForeground(Color.WHITE);
		currentRound.setFocusPainted(false);
		TournamentBracketPanel frame = new TournamentBracketPanel();
		Character[] myChars = frame.getBracketCharacters();

		currentRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VotingBoothGUI windvote = new VotingBoothGUI();
				try {
					windvote.createAndShowGUI(myChars);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// }
			}
		});

		cancelBet = new JButton("Cancel Bet");
		cancelBet.setBackground(Color.decode("#F58800"));
		cancelBet.setOpaque(true);
		cancelBet.setBorderPainted(false);
		cancelBet.setFont(new Font("sans-serif", Font.PLAIN, 10));
		cancelBet.setForeground(Color.WHITE);
		cancelBet.setFocusPainted(false);
		cancelBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// add items to JPanel
		DailyCheckIn d = new DailyCheckIn();
		menuPanel.add(d.showBalance(currUsername));

		menuPanel.add(editProfile);
		menuPanel.add(charSearch);
		menuPanel.add(leaderboard);
		menuPanel.add(currentRound);
		menuPanel.add(createChar);
		menuPanel.add(cancelBet);

		/*
		 * BufferedImage image = null; try { image = ImageIO.read(new
		 * File("bracket_template.png")); } catch (IOException e) { e.printStackTrace();
		 * }
		 * 
		 * 
		 * Image scaledImage = image.getScaledInstance(800,500,Image.SCALE_SMOOTH);
		 * 
		 * JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
		 * 
		 * 
		 * menuPanel.add(picLabel);
		 */

		// return the menu JPanel
		menuPanel.setVisible(true);
		return menuPanel;
	}
}


