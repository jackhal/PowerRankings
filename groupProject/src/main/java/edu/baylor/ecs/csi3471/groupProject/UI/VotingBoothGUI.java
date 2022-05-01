package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.VotingBoothDAO;

import javax.swing.*;
import java.awt.*;
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
        /*int length = 0;
        while(a[length] != null){
            length++;
        }
        //System.out.println(length);
        length--;*/
        int length = a.length;


        Container contentPane = frame.getContentPane();
        if(length < 8) {
            contentPane.setLayout(new GridLayout(4, 4));
            contentPane.add(new VotingBoothDAO(a[0], a[1]));
            contentPane.add(new VotingBoothDAO(a[2], a[3]));
            contentPane.add(new VotingBoothDAO(a[4], a[5]));
            contentPane.add(new VotingBoothDAO(a[6], a[7]));
        }
        else if(length <= 12){
            contentPane.setLayout(new GridLayout(2, 2));
            contentPane.add(new VotingBoothDAO(a[8], a[9]));
            contentPane.add(new VotingBoothDAO(a[10], a[11]));
        }
        else{
            contentPane.setLayout(new GridLayout(2, 2));
            contentPane.add(new VotingBoothDAO(a[12], a[13]));
        }

        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
