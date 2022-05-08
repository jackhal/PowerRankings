package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.*;
import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.CharacterDAO;
import edu.baylor.ecs.csi3471.groupProject.Persistence.CharacterVotesDAO;
//import jdk.internal.icu.lang.UCharacterDirection;
import edu.baylor.ecs.csi3471.groupProject.Persistence.TournamentBracketPanel;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * HomePage
 * This is the UI for the main Homepage after the Login
 */
public class HomePage {
    static String currUsername;
    static TournamentBracketPanel p;
    static JLayeredPane layered;
    static JFrame mainFrame;

    /**
     * createAndShowGUI
     * This function creates the graphic user interface for the Home Page.
     *
     * @param username current user passed from login creates the homepage of the
     *                 app
     * @throws IOException
     * @throws ParseException
     */
    public static void createAndShowGUI(String username) throws IOException, ParseException {
        currUsername = username;
        User bill = Runner.curUser;
        if (bill.isAdmin()) {
//			Date date = Calendar.getInstance().getTime();
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//			ArrayList<String> time = new ArrayList<String>();
//			BufferedReader br = new BufferedReader(new FileReader("TimeLog.txt"));
//			String line = "";
//			br.readLine();
//			while ((line = br.readLine()) != null) {
//				time.add(line);
//			}
//			File tsvOut = new File("TimeLog.txt");
//			PrintWriter pw = new PrintWriter(tsvOut);
//			Date first = null;
//			if(time.size() != 0){
//				first = dateFormat.parse(time.get(0));
//				if(first.toInstant().truncatedTo(ChronoUnit.DAYS) != date.toInstant().truncatedTo(ChronoUnit.DAYS)){
//					JOptionPane.showMessageDialog(null, "You should end the Round Now!", "ROUND SHOULD BE OVER", 1);
//					pw.write("");
//				}
//				else {
//					for (int i = 0; i < time.size(); i++) {
//						pw.write(time.get(i));
//						pw.write("\n");
//					}
//					pw.write(dateFormat.format(date));
//				}
//			}
//			else{
//				pw.write(dateFormat.format(date));
//			}
        }

        // create mainFrame
        mainFrame = new JFrame("Power Rankings");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel menuPanel = addMenu();
        menuPanel.setBounds(0, 0, 1375, 100);
        menuPanel.setLayout(new GridLayout());
        menuPanel.setBackground(Color.decode("#032930"));
        mainFrame.add(menuPanel);

        JPanel eastDesignPanel = new JPanel();
        eastDesignPanel.setLayout(new GridLayout(1, 3));
        eastDesignPanel.setPreferredSize(new Dimension(200, 200));
        eastDesignPanel.setBackground(Color.decode("#F58800"));

        JLabel eastLabel = new JLabel("Welcome to the tournament!");
        eastLabel.setFont(new Font("sans-serif", Font.PLAIN, 15));
        eastLabel.setForeground(Color.WHITE);
        eastDesignPanel.add(eastLabel);

        mainFrame.add(eastDesignPanel, BorderLayout.EAST);

        JPanel westDesignPanel = new JPanel();
        westDesignPanel.setPreferredSize(new Dimension(200, 200));
        westDesignPanel.setBackground(Color.decode("#F58800"));
        mainFrame.add(westDesignPanel, BorderLayout.WEST);

        p = new TournamentBracketPanel();
        layered = p.getBracket();
        CharacterDAO myCharDao = new CharacterDAO();
        long lines = myCharDao.getCharacterRoundsLines();
        if (lines >= 13) {
            p.getRound2(layered);
        }
        if (lines >= 15) {
            p.getRound3(layered);
        }
        if (lines == 16) {
            p.getWinner(layered);
        }


        layered.setBackground(Color.decode("#051821"));
        layered.setOpaque(true);

        mainFrame.add(layered);

        // set mainFrame to true
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * addMenu
     * This function initiates the menu for the HomePage.
     *
     * @return
     */
    protected static JPanel addMenu() {
        // variable declarations
        JPanel menuPanel;
        final JButton editProfile, charSearch, leaderboard, currentRound, createChar, cancelBet, exportToXLSX;
        final JButton endRound, newTournament;

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
                    Runner.logger.severe("Unable to edit profile of User");
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
        currentRound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TournamentBracketPanel frame = new TournamentBracketPanel();
                Character[] myChars = frame.getBracketCharacters();
                if(myChars[14] != null){
                    JOptionPane.showMessageDialog(null, "There's already a winner, and it's " + myChars[14].getName());
                }
                else {
                    VotingBoothGUI windvote = new VotingBoothGUI();
                    try {
                        windvote.createAndShowGUI(myChars);
                    } catch (Exception ex) {
                        Runner.logger.severe("unable to create currentround GUI");
                        ex.printStackTrace();
                    }
                }
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
                VotesPanel votePanel = new VotesPanel(Runner.curUser.getUsername());
            }
        });


        endRound = new JButton("End Round");
        endRound.setBackground(Color.decode("#FF0000"));
        endRound.setOpaque(true);
        endRound.setBorderPainted(false);
        endRound.setFont(new Font("sans-serif", Font.PLAIN, 10));
        endRound.setForeground(Color.WHITE);
        endRound.setFocusPainted(false);
        endRound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runner.logger.info("End round pressed");

                CharacterDAO myCharDAO = new CharacterDAO();
                long lines = myCharDAO.getCharacterRoundsLines();

                if (lines == 13) {
                    //ready for round 2
                    p.getRound2(layered);
                } else if (lines == 15) {
                    //ready for round 3
                    p.getRound3(layered);
                } else if (lines == 16) {
                    //ready for winner
                    p.getWinner(layered);
                    endRound.setEnabled(false);
                } else {
                    //not ready for next round
                    JOptionPane.showMessageDialog(null, "Not all matches are over, please try again later!", "Hasty Admin", 1);
                }
            }
        });

        exportToXLSX = new JButton("Export to XLSX");
        exportToXLSX.setBackground(Color.decode("#ff8153"));
        exportToXLSX.setOpaque(true);
        exportToXLSX.setBorderPainted(false);
        exportToXLSX.setFont(new Font("sans-serif", Font.PLAIN, 10));
        exportToXLSX.setForeground(Color.WHITE);
        exportToXLSX.setFocusPainted(false);
        exportToXLSX.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Choose a directory to export each database to: ");
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = jfc.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    if (jfc.getSelectedFile().isDirectory()) {
                        UserDAO userDao = new UserDAO();
                        CharacterDAO charDao = new CharacterDAO();
                        CharacterVotesDAO charVotesDao = new CharacterVotesDAO();

                        try {
                            userDao.exportToExcel(jfc.getSelectedFile().toString());
                            charDao.exportCharactersToExcel(jfc.getSelectedFile().toString());
                            charDao.exportCharacterRoundsToExcel(jfc.getSelectedFile().toString());
                            charVotesDao.exportToExcel(jfc.getSelectedFile().toString());
                        } catch (IOException e1) {
                            Runner.logger.severe("An error occurred while exporting to excel: " + e1.getLocalizedMessage());
                        }
                    }
                }
            }

        });

        newTournament = new JButton("Start new tournament");
        newTournament.setBackground(Color.decode("#db0000"));
        newTournament.setOpaque(true);
        newTournament.setBorderPainted(false);
        newTournament.setFont(new Font("sans-serif", Font.PLAIN, 10));
        newTournament.setForeground(Color.WHITE);
        newTournament.setFocusPainted(false);

        newTournament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runner.logger.info("newTournament pressed");
                //if tournament ongoing make this button invalid

                CharacterDAO myCharDAO = new CharacterDAO();

                long lines = myCharDAO.getCharacterRoundsLines();
                if (lines < 16) {
                    JOptionPane.showMessageDialog(null, "Please wait for the current tournament to finish before starting a new one.");
                } else {
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new tournament?", "New Tournament", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        myCharDAO.reloadCharacterRounds();
                    }
                }

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
        menuPanel.add(exportToXLSX);

        if (Runner.curUser.isAdmin()) {
            menuPanel.add(endRound);
            menuPanel.add(newTournament);
            long lines = 0;
            CharacterDAO myCharDAO = new CharacterDAO();
            lines = myCharDAO.getCharacterRoundsLines();

            if (lines == 16) {
                endRound.setEnabled(false);
            }
        }

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
