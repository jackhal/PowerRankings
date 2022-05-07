package edu.baylor.ecs.csi3471.groupProject.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.baylor.ecs.csi3471.groupProject.Business.CharacterVotes;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.CharacterVotesDAO;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;

/**
 * Class VotesPanel
 * this is the UI for displaying the votes window
 */
public class VotesPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;

    /**
     * Displays panel with User's current votes
     *
     * @param username
     */
    public VotesPanel(String username) {
        super();
        Runner.logger.info("displaying votes window");
        User user;

        JFrame editFrame = new JFrame("Votes");

        user = Runner.curUser;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] columnNames = {"Character", "Bet", ""};

        CharacterVotesDAO charDao = new CharacterVotesDAO();
        CharacterVotes votes = null;

        try {
            votes = charDao.getCharacterVoteByUsername(username);
        } catch (Exception e) {
            Runner.logger.severe("failed to get " + username + "'s votes");
            ;
        }

        final DefaultTableModel model = new DefaultTableModel(null, columnNames);
        String[] empty = {"_", "_"};

        int currRow = 0;
        if (!votes.getMatchAChoice().isEmpty() && votes.getMatchABet() != 0) {
            model.addRow(empty);
            model.setValueAt(votes.getMatchAChoice(), currRow, 0);
            model.setValueAt(votes.getMatchABet(), currRow, 1);
            model.setValueAt("Cancel", currRow, 2);
            currRow++;
        }

        if (!votes.getMatchBChoice().isEmpty() && votes.getMatchBBet() != 0) {
            model.addRow(empty);
            model.setValueAt(votes.getMatchBChoice(), currRow, 0);
            model.setValueAt(votes.getMatchBBet(), currRow, 1);
            model.setValueAt("Cancel", currRow, 2);
            currRow++;
        }

        if (!votes.getMatchCChoice().isEmpty() && votes.getMatchCBet() != 0) {
            model.addRow(empty);
            model.setValueAt(votes.getMatchCChoice(), currRow, 0);
            model.setValueAt(votes.getMatchCBet(), currRow, 1);
            model.setValueAt("Cancel", currRow, 2);
            currRow++;
        }

        if (!votes.getMatchDChoice().isEmpty() && votes.getMatchDBet() != 0) {
            model.addRow(empty);
            model.setValueAt(votes.getMatchDChoice(), currRow, 0);
            model.setValueAt(votes.getMatchDBet(), currRow, 1);
            model.setValueAt("Cancel", currRow, 2);
            currRow++;
        }

        table = new JTable(model);

        ButtonColumn buttonColumn = new ButtonColumn(table, new AbstractAction() {

            private static final long serialVersionUID = 1L;

            /**
             * Handles "Cancel" action in which a user intends to cancel
             * a bet for a character they have previously voted on.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get table data
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                String characterName = (String) model.getValueAt(modelRow, 0);

                Runner.logger.info("cancelling bet for " + characterName);

                // Remove bet from CharacterVotes.csv
                CharacterVotesDAO voteDAO = new CharacterVotesDAO();
                try {
                    Integer betToGiveBack = voteDAO.getBetByCharacterName(username, characterName);
                    Runner.logger.info("Giving back " + betToGiveBack + " coins");
                    int funds = Runner.curUser.getFunds() + Integer.valueOf(betToGiveBack);
                    Runner.curUser.setFunds(funds);
                    Runner.logger.info("New user balance is " + funds);


                    voteDAO.removeCharacterVote(Runner.curUser.getUsername(), characterName);
                    Runner.logger.info("Removed " + characterName + " bet for " + username);

                } catch (Exception e1) {
                    Runner.logger.severe("Failed to remove character from database => " + e1.getMessage());
                }


                // Remove from table
                ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            }

        }, 2);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);

        Box b = Box.createHorizontalBox();
        b.add(Box.createHorizontalGlue());
        add(b);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        scrollPane.setOpaque(true); //content panes must be opaque
        editFrame.setContentPane(scrollPane);

        //Display the window.
        editFrame.pack();
        editFrame.setVisible(true);
    }
}
