package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.DailyCheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomePage {
	static String currUsername;

	/**
	 * @param username current user passed from login
	 * creates the homepage of the app
	 */
	public static void createAndShowGUI(String username) {
		currUsername = username;
		//create mainFrame
		JFrame mainFrame = new JFrame("Power Rankings");
		mainFrame.setSize(900, 800);
		
		//add the menu to mainFrame
		//mainFrame.setLayout(new BorderLayout());
		//mainFrame.add(addMenu(), BorderLayout.CENTER);
		//GridLayout g = new GridLayout(2, 1);
		
		JPanel menuPanel = addMenu();
		menuPanel.setBounds(0, 0, 900, 50);
		mainFrame.add(menuPanel);
		
		TournamentBracketPanel f = new TournamentBracketPanel();
		JLayeredPane layered = f.getBracket();
		
		layered.setBounds(0, 100, 550, 650);
		
		
		mainFrame.add(layered);
		
		
		//mainFrame.setLayout(g);
		//set mainFrame to true
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	protected static JPanel addMenu() {
	//variable declarations
		JPanel menuPanel;
		final JButton editProfile, charSearch, leaderboard, currentRound, createChar, cancelBet;

		
	//variable initialization
		menuPanel = new JPanel();

		
		editProfile = new JButton("Edit Profile");
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

		//Create Character button listener
		createChar = new JButton("Create Character");
		createChar.setFocusPainted(false);
		createChar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateCharacter c = new CreateCharacter();
				c.createAndShowGUI(currUsername);
			}
		});
		
	//leaderboard action listener
		leaderboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			//variable declaration
				JFrame selectLead;
				JPanel buttonPanel;
				JButton charLead, userLead;
			
			//variable initialization
				selectLead = new JFrame("Select Leaderboard");
				
				buttonPanel = new JPanel();
				
				charLead = new JButton("Character Leaderboard");
				charLead.setFocusPainted(false);
				
				userLead = new JButton("User Leaderboard");
				userLead.setFocusPainted(false);


				
			//char leaderboard action listener
				charLead.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Table t = new Table();
						t.createAndShowGUI();
					}
				});
				
			//user leaderboard action listener
				userLead.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						UserTable ut = new UserTable();
						ut.createAndShowUserGUI();
					}
				});

				
			//add items to JFrame
				buttonPanel.add(charLead);
				buttonPanel.add(userLead);
				
				selectLead.add(buttonPanel);
				selectLead.setSize(350, 75);
				selectLead.setVisible(true);
			}
		});
		
		currentRound = new JButton("Current Round");
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
				//}
			}
		});
		
		cancelBet = new JButton("Cancel Bet");
		cancelBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	//add items to JPanel
		DailyCheckIn d = new DailyCheckIn();
		menuPanel.add(d.showBalance(currUsername));
		
		menuPanel.add(editProfile);
		menuPanel.add(charSearch);
		menuPanel.add(leaderboard);
		menuPanel.add(currentRound);
		menuPanel.add(createChar);
		menuPanel.add(cancelBet);

		/*BufferedImage image = null;
		try {
			image = ImageIO.read(new File("bracket_template.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		Image scaledImage = image.getScaledInstance(800,500,Image.SCALE_SMOOTH);

		JLabel picLabel = new JLabel(new ImageIcon(scaledImage));


		menuPanel.add(picLabel);*/

		
	//return the menu JPanel
		menuPanel.setVisible(true);
		return menuPanel;
	}
}

