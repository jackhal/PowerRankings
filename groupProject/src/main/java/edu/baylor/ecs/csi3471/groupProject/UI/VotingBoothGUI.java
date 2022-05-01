package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.CharacterVotes;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.CharacterVotesDAO;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;
import edu.baylor.ecs.csi3471.groupProject.Persistence.VotingBoothDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The VotingBoothGUI is in charge of taking the two characters, creating the GUI
 * of the voting booth and calling the DAO for the functionality.
 */
public class VotingBoothGUI {
    private static Logger applicationlog = Logger.getLogger(Timer.class.getName());
    /**
     * createAndShowGUI()
     * This method always takes two Characters and creates the GUI of the
     * Voting Booth parameters.
     *
     * @param  a  an absolute URL giving the base location of the image
     * @return      void
     */
    protected void createAndShowGUI(Character[] a) throws Exception {
        JFrame frame = new JFrame("VoteDialog");
        applicationlog.info("create GUI invoked");
        int length = 0;
        while(a[length] != null){
            length++;
        }
        length--;

        Integer round = 0;
        Container contentPane = frame.getContentPane();
        if(length < 8) {
            contentPane.setLayout(new GridLayout(4, 4));
            contentPane.add(new VotingBoothDAO(a[0], a[1]));
            contentPane.add(new VotingBoothDAO(a[2], a[3]));
            contentPane.add(new VotingBoothDAO(a[4], a[5]));
            contentPane.add(new VotingBoothDAO(a[6], a[7]));
            round = 1;
        }
        else if(length <= 12){
            contentPane.setLayout(new GridLayout(2, 2));
            contentPane.add(new VotingBoothDAO(a[8], a[9]));
            contentPane.add(new VotingBoothDAO(a[10], a[11]));
            round = 2;
        }
        else{
            contentPane.setLayout(new GridLayout(2, 2));
            contentPane.add(new VotingBoothDAO(a[12], a[13]));
            round = 3;
        }

        if(Runner.curUser.isAdmin()) {
            Runner.logger.info("User is Admin");
            JButton endRound = new JButton("End All Matches");
            endRound.setPreferredSize(new Dimension(100, 30));
            endRound.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //FIXME GO TO NEXT ROUND FUNCTIONALITY
                    endRound.setEnabled(false);
                    TournamentBracketPanel frame = new TournamentBracketPanel();
                    CharacterVotesDAO charVote = new CharacterVotesDAO();
                    Runner.logger.info("End Match Button Pressed");
                    Integer i1 = 0;
                    ArrayList<CharacterVotes> tally = new ArrayList<>();
                    try {
                        tally = charVote.getCharacterVotes();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    int length = 0;
                    while (a[length] != null) {
                        length++;
                    }
                    length--;
                    int hooboy = 0;
                    if (length < 8) {
                        hooboy = 0;
                    } else if (length <= 12) {
                        hooboy = 8;
                    } else {
                        hooboy = 12;
                    }
                    for (int i = hooboy; i < length; i += 2) {
                        Character bert = a[i];
                        Character gandhi = a[i + 1];
                        int bertvote = 0;
                        int ganvote = 0;
                        for (int j = 0; j < tally.size(); j++) {
                            if (bert.getName().equalsIgnoreCase(tally.get(j).getMatchAChoice())) {
                                bertvote++;
                            } else if (bert.getName().equalsIgnoreCase(tally.get(j).getMatchBChoice())) {
                                bertvote++;
                            } else if (bert.getName().equalsIgnoreCase(tally.get(j).getMatchCChoice())) {
                                bertvote++;
                            } else if (bert.getName().equalsIgnoreCase(tally.get(j).getMatchDChoice())) {
                                bertvote++;
                            } else {
                                ganvote++;
                            }

                        }
                        if (bertvote > ganvote) {
                            i1 = bert.getWin();
                            bert.setWin(i1++);
                            i1 = gandhi.getLoss();
                            gandhi.setLoss(i1++);
                            //TournamentBracketPanel frame = new TournamentBracketPanel();
                            FileWriter fileWriter = null;
                            try {
                                fileWriter = new FileWriter("CharacterRounds.csv", true);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                fileWriter.write(bert.charToCSV());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                fileWriter.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            JOptionPane.showMessageDialog(null, bert.getName() + " won the match!");
                            Character[] myChars = frame.getBracketCharacters();
                            int slots = 0;
                            /*while (myChars[slots] != null) {
                                slots++;
                            }
                            slots--;*/
                            slots = myChars.length;
                            Integer match = 0;
                            if (slots == 8 || slots == 12) {
                                match = 1;
                            } else if (slots == 9 || slots == 13) {
                                match = 2;
                            } else if (slots == 10) {
                                match = 3;
                            } else if (slots == 11) {
                                match = 4;
                            }
                            UserDAO users = new UserDAO();
                            ArrayList<User> userList = null;
                            try {
                                userList = users.getUsers();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                ArrayList<CharacterVotes> votes = charVote.getCharacterVotes();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            for (User u : userList) {
                                try {
                                    String opt = charVote.findCurrentVote(u.getUsername(), match);
                                    if (opt.equalsIgnoreCase(bert.getName())) {
                                        int base = u.getFunds();
                                        int win = charVote.findCurrentBet(u.getUsername(), match);
                                        u.setFunds(base + (win * 2));
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            i1 = gandhi.getWin();
                            gandhi.setWin(i1++);
                            i1 = bert.getLoss();
                            bert.setLoss(i1++);
                            FileWriter fileWriter = null;
                            try {
                                fileWriter = new FileWriter("CharacterRounds.csv", true);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                fileWriter.write(gandhi.charToCSV());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                fileWriter.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, gandhi.getName() + " won the match!");
                            Character[] myChars = frame.getBracketCharacters();
                            int longy = 0;
                            while (myChars[longy] != null) {
                                longy++;
                            }
                            longy--;
                            Integer match = 0;
                            if (longy == 8 || longy == 12) {
                                match = 1;
                            } else if (longy == 9 || longy == 13) {
                                match = 2;
                            } else if (longy == 10) {
                                match = 3;
                            } else if (longy == 11) {
                                match = 4;
                            }
                            UserDAO users = new UserDAO();
                            ArrayList<User> userList = null;
                            try {
                                userList = users.getUsers();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                ArrayList<CharacterVotes> votes = charVote.getCharacterVotes();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            for (User u : userList) {
                                try {
                                    String opt = charVote.findCurrentVote(u.getUsername(), match);
                                    if (opt.equalsIgnoreCase(gandhi.getName())) {
                                        int base = u.getFunds();
                                        int win = charVote.findCurrentBet(u.getUsername(), match);
                                        u.setFunds(base + (win * 2));
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        int lengthy = 0;
                        Character[] myChars = frame.getBracketCharacters();
                        while (myChars[lengthy] != null) {
                            lengthy++;
                        }
                        lengthy--;
                        Integer match = 0;
                        if (lengthy >= 14) {
                            PrintWriter writer = null;
                            try {
                                writer = new PrintWriter("CharacterVotes.tsv");
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            writer.print("");
                            writer.close();
                        }
                        bert.setCurrVote(0);
                        gandhi.setCurrVote(0);
                    }
                }
            });
            contentPane.add(endRound, BorderLayout.WEST);
        }

        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
