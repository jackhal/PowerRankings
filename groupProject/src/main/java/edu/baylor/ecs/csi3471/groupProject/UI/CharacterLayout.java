package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Class CharacterLayout
 * This is the UI for how the character will layed out on a table
 */
public class CharacterLayout extends JFrame {
    /*public static void main(String[] args) {
        new PizzaGridBagLayout();
    }*/

    JTextField nameW = new JTextField(20), worldW = new JTextField(10), descW = new JTextField(20);
    JTextField recordW = new JTextField(20);
    JTextField winsW = new JTextField(20);
    JTextField lossesW = new JTextField(20);
    JTextField ownerW = new JTextField(20);

    JButton closeButton = new JButton("Close");

    /**
     * CharacterLayout Constructor
     * This function is the custom constructor for the CharacterLayout that creates
     * a panel for the character passed in.
     * @param c character to display
     */
    public CharacterLayout(Character c) {
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        addItem(panel1, new JLabel("Name:"), 0, 0, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("World:"), 0, 1, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Description:"), 0, 2, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Record:"), 0, 3, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Wins"), 0, 4, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Losses:"), 0, 5, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Owner:"), 0, 6, 1, 1, GridBagConstraints.EAST);




        addItem(panel1, nameW, 1, 0, 2, 1, GridBagConstraints.WEST);
        nameW.setText(c.getName());
        nameW.setEditable(false);
        addItem(panel1, worldW, 1, 1, 1, 1, GridBagConstraints.WEST);
        worldW.setText(c.getWorld());
        worldW.setEditable(false);
        addItem(panel1, descW, 1, 2, 2, 1, GridBagConstraints.WEST);
        descW.setText(c.getDesc());
        descW.setEditable(false);
        addItem(panel1, recordW, 1, 3, 2, 1, GridBagConstraints.WEST);
        String rec = "";
        rec += c.getWin();
        rec += "/";
        rec += c.getLoss();
        recordW.setText(rec);
        recordW.setEditable(false);
        addItem(panel1, winsW, 1, 4, 2, 1, GridBagConstraints.WEST);
        winsW.setText(String.valueOf(c.getWin()));
        winsW.setEditable(false);
        addItem(panel1, lossesW, 1, 5, 2, 1, GridBagConstraints.WEST);
        lossesW.setText(String.valueOf(c.getLoss()));
        lossesW.setEditable(false);
        addItem(panel1, ownerW, 1, 6, 2, 1, GridBagConstraints.WEST);
        ownerW.setText(c.getOwner());
        ownerW.setEditable(false);


        Image image = null;
        Box buttonBox = Box.createHorizontalBox();
        try {
            URL url = new URL(c.getPicture());
            image = ImageIO.read(url);
            JFrame frame = new JFrame();
            Image newImage = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);

            JLabel lblimage = new JLabel(new ImageIcon(newImage));
            buttonBox.add(lblimage);
        }
        catch (IOException e) {
            Runner.logger.severe("Can't get picture for buttonBox");
        }

        addItem(panel1, buttonBox, 2, 8, 1, 1, GridBagConstraints.NORTH);

        this.add(panel1);
        this.pack();
        this.setVisible(true);
    }
    /**
     * addItem
     * This function adds an element to the JPanel passed in.
     * @param p panel
     * @param c component
     * @param x x-dem
     * @param y y-dem
     * @param width width of panel
     * @param height height of panel
     * @param align alignment og panel
     */
    private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }
};