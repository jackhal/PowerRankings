package edu.baylor.ecs.csi3471.groupProject.Persistence;


import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * class ImagePanel
 * This class supports the addition of images to JPanels
 */
class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image img;
    private Image scaled;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
    }

    /**
     * Invalidate
     */
    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaled = img.getScaledInstance(getWidth(), getHeight(),
                    Image.SCALE_SMOOTH);
        }
    }

    /**
     * getPreferredSize
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(200, 200) : new Dimension(
                img.getWidth(this), img.getHeight(this));
    }

    /**
     * paintComponent
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaled, 0, 0, null);
    }
}


/**
 * class TournamentBracketPanel
 * This is the TournamentBracketPanel class that creates and updates the Tournament Bracket
 */
public class TournamentBracketPanel
{
    //public static Logger logger = Logger.getLogger(Runner.class.getName());

    /**
     * initTournamentCharacters()
     * @param  fileName -- filename from whcih the characters must be read from.
     * This function gets the characters fot the tournament from the
     * CharacterRounds.csv file and returns a list of the characters.
     * @return Character[]
     */
    public Character[] initTournamentCharacters(String fileName)
    {
        Runner.logger.info("Initializing tournament characters");
        Character[] characters = new Character[15];
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();

            int i = 0;
            for(String temp = reader.readLine(); temp != null; temp = reader.readLine())
            {
                characters[i] = new Character(temp);
                i++;
            }
            reader.close();
        }
        catch(Exception e)
        {
            Runner.logger.severe("unable to read characters from characterRounds.csv file");
            System.exit(1);
        }
        return characters;
    }


    /**
     * getBracketCharacters()
     * This function returns the characters created
     * by the reading of the CharacterRounds.csv file.
     * @return Character[]
     */
    public Character[] getBracketCharacters()
    {
        Runner.logger.info("returning tournament characters");
        Character[] myChars = initTournamentCharacters("CharacterRounds.csv");
        return myChars;
    }


    /**
     * addImagesToBracket
     * This function adds the images to the
     * bracket as per the round (specified
     * by the arguments).
     * @param myChars
     * @param myBigPane
     * @param x
     * @param y
     * @param xInc
     * @param yInc
     * @param iBeg
     * @param iEnd
     * @return JLayeredPane
     */
    public JLayeredPane addImagesToBracket(Character[] myChars, JLayeredPane myBigPane, int x, int y, int xInc, int yInc, int iBeg, int iEnd) throws MalformedURLException
    {
        int xCoord = x, yCoord = y;
        ImagePanel tempImgPanel = null;
        try
        {
            for(int i = iBeg; i < iEnd; i++)
            {
                if(i == (iBeg + iEnd)/2)
                {
                    xCoord += xInc;

                    if(i!=13)
                    {
                        yCoord = y;
                    }

                }
                URL url = new URL(myChars[i].getPicture());
                Image tempImg = ImageIO.read(url);
                tempImgPanel = new ImagePanel(tempImg);

                Runner.logger.info("image " + i + " is being added to the bracket");
                tempImgPanel.setBounds(xCoord, yCoord, 75, 75);
                myBigPane.add(tempImgPanel, JLayeredPane.PALETTE_LAYER);
                yCoord += yInc;
            }
        }
        catch(Exception e)
        {
            Runner.logger.severe("could not load the images onto the tournament bracket");
            throw new MalformedURLException();
        }

        return myBigPane;
    }


    /**
     * getBracket
     * This function creates the bracket as a LayeredPane
     * and then adds to it the characters participating
     * in the first roun matchup.
     * @return JLayeredPane
     */
    public JLayeredPane getBracket()
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);


        Runner.logger.info("tournament bracket being generated");
        ImagePanel imgPanel = null;
        try
        {
            Image img = ImageIO.read(new File("bracket_template.png"));
            imgPanel = new ImagePanel(img);
        }
        catch(Exception e)
        {
            Runner.logger.severe("Can't read from File");
            System.out.println(e);
            System.exit(1);
        }

        Character[] myChars = initTournamentCharacters("CharacterRounds.csv");

        JLayeredPane myBigPane = new JLayeredPane();
        //imgPanel.setBounds(0, 0, 550, 550);
        imgPanel.setBounds(225, 100, 550, 650);

        myBigPane.add(imgPanel, JLayeredPane.DEFAULT_LAYER);


        Runner.logger.info("adding round one to the tournament bracket");
        try
        {
            myBigPane = addImagesToBracket(myChars, myBigPane, 225, 125, 450, 175, 0, 8);
        }
        catch(MalformedURLException me)
        {
            Runner.logger.severe("Unable to load images to bracket");
            System.exit(1);
        }


//        logger.info("adding round two to the tournament bracket");
//        myBigPane = addImagesToBracket(myChars, myBigPane, 175, 230, 120, 350, 8, 12);
//
//        logger.info("adding round three to the tournament bracket");
//        //myBigPane = addImagesToBracket(myChars, myBigPane, 175, 420, 120, -80, 12, 14);

//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }


    /**
     * getRound2
     * This function accepts a JLayeredPane
     * and adds to it the characters taht advanced from
     * round 1 to the next stage of the bracket.
     * @param myBigPane
     * @return
     */
    public JLayeredPane getRound2(JLayeredPane myBigPane)
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);

        Character[] myChars = initTournamentCharacters("CharacterRounds.csv");

        Runner.logger.info("adding round two to the tournament bracket");

        try
        {
            myBigPane = addImagesToBracket(myChars, myBigPane, 410, 230, 120, 350, 8, 12);
        }
        catch(MalformedURLException me)
        {
            Runner.logger.severe("Unable to load images to bracket");
            System.exit(1);
        }


//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }

    /**
     * getRound3
     * This funcion aceepts a JLayeredPane and
     * adds to it the winners from round 2.
     * @param myBigPane
     * @return
     */
    public JLayeredPane getRound3(JLayeredPane myBigPane)
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);

        Character[] myChars = initTournamentCharacters("CharacterRounds.csv");

        Runner.logger.info("adding round three to the tournament bracket");
        try
        {
            myBigPane = addImagesToBracket(myChars, myBigPane, 400, 420, 120, -80, 12, 14);
        }
        catch(MalformedURLException me)
        {
            Runner.logger.severe("Unable to load images to bracket");
            System.exit(1);
        }


//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }

    public JLayeredPane getWinner(JLayeredPane myBigPane)
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);

        Character[] myChars = initTournamentCharacters("CharacterRounds.csv");

        Runner.logger.info("adding winner to the tournament bracket");

        try
        {
            myBigPane = addImagesToBracket(myChars, myBigPane, 350, 100, 120, -80, 14, 15);
        }
        catch(MalformedURLException me)
        {
            Runner.logger.severe("Unable to load images to bracket");
            System.exit(1);
        }
        myBigPane.add(new JTextField("WINNER"), new Integer(2), 0);

//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }


}








