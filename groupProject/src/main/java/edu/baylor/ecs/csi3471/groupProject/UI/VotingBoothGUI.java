package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.VotingBoothDAO;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.logging.Logger;

public class VotingBoothGUI {
    private static Logger applicationlog = Logger.getLogger(Timer.class.getName());
    protected void createAndShowGUI(Character a, Character b) throws MalformedURLException {
        JFrame frame = new JFrame("VoteDialog");
        applicationlog.info("create GUI invoked");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new VotingBoothDAO(a, b));


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
