package edu.baylor.ecs.csi3471.groupProject.Business;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import edu.baylor.ecs.csi3471.groupProject.UI.CharacterLayout;

import javax.swing.*;

/**
 * Class Character
 * This class describes the fictional character that are competing against each other.
 */
public class Character {
    public static Logger logger = Logger.getLogger(Timer.class.getName());

    String name = "";
    String world = "";
    String desc = "";
    Integer win = 0;
    Integer loss = 0;
    static Integer id = 0;
    String picture = "";
    Double ratio = 0.0;
    String owner = "";
    Integer currVote = 0;

    public Character() {
        id = id++;
    }

	/**
	 * Character Custom Constructor This is the character class custom constructor
	 * that creates a character object based on a set of parameters.
	 *
	 * @param name    string name of character
	 * @param world   fanstasy world of character
	 * @param desc    description of character and abilites
	 * @param win     total number of wins
	 * @param loss    total number of losses
	 * @param picture url for image of character
	 * @param owner   user who uploaded character
	 */
	public Character(String name, String world, String desc, Integer win, Integer loss, String picture, String owner) {
		Runner.logger.info("Character " + name + " created");
		this.name = name;
		this.world = world;
		this.desc = desc;
		this.win = win;
		this.loss = loss;
		this.picture = picture;
		this.owner = owner;
		id = Runner.numchar;
		Runner.numchar = Runner.numchar + 1;
	}

    /**
     * Character Custom Constructor This class parses a line for the attributes of
     * the Character class
     *
     * @param line line of data from database to be split and added to character
     */
    public Character(String line) {
        String[] split = line.split("\t");
        this.name = split[0];
        this.world = split[1];
        this.desc = split[2];
        this.win = Integer.valueOf(split[3]);
        this.loss = Integer.valueOf(split[4]);
        this.id = Integer.valueOf(split[5]);
        this.picture = split[6];
        this.owner = split[7];
        Runner.logger.info("Character " + this.name + " created from splitting constructor");

        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) / (1.0 * (win + loss)));
        } else {
            if (win == 0) {
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
        id = id++;
    }

    /**
     * getOwner()
     *
     * @return user who made character
     */
    public String getOwner() {
        return owner;
    }

    /**
     * setOwner()
     *
     * @param owner user who made character
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * getId()
     *
     * @return charcater id
     */
    public Integer getId() {
        return id;
    }

    /**
     * getRatio()
     *
     * @return ratio of wins to losses
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * getLoss()
     *
     * @return number of losses
     */
    public Integer getLoss() {
        return loss;
    }

    /**
     * setLoss()
     *
     * @param loss number of losses
     */
    public void setLoss(Integer loss) {
        this.loss = loss;
        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) / (1.0 * (win + loss)));
        } else {
            if (win == 0) {
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
    }

    /**
     * getWin()
     *
     * @return number of wins
     */
    public Integer getWin() {
        return win;
    }

    /**
     * setWin()
     *
     * @param win number of wins
     */
    public void setWin(Integer win) {
        this.win = win;
        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) / (1.0 * (win + loss)));
        } else {
            if (win == 0) {
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
    }

    /**
     * getDesc()
     *
     * @return decription of character
     */
    public String getDesc() {
        return desc;
    }

    /**
     * setDesc()
     *
     * @param desc decription of character
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * getWorld()
     *
     * @return world dof character
     */
    public String getWorld() {
        return world;
    }

    /**
     * setWorld()
     *
     * @param world world of charcater
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * getName()
     *
     * @return name of character
     */
    public String getName() {
        return name;
    }

    /**
     * setName()
     *
     * @param name name of charcater
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPicture()
     *
     * @return
     */
    public String getPicture() {
        return picture;
    }

    /**
     * setPicture()
     *
     * @param picture url of character image
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * getCurrVote()
     *
     * @return
     */
    public Integer getCurrVote() {
        return currVote;
    }

    /**
     * setCurrVote()
     *
     * @param currVote
     */
    public void setCurrVote(Integer currVote) {
        this.currVote = currVote;
    }

    /**
     * @param o charctaer object
     * @return true if they are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) && Objects.equals(world, character.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, world);
    }

    /**
     * displayChar()
     */
    public void displayChar() {
        logger.info("Character displayed");
        new CharacterLayout(this);
        // This may open a dialog box to see character data
    }

    /**
     * charToCSV()
     *
     * @return
     */
    public String charToCSV() {
        logger.info("Character converted to csv form to print");
        String ret = "\n";
        ret += name + "\t" + world + "\t" + desc + "\t";
        ret += win + "\t" + loss + "\t" + id + "\t" + picture + "\t" + owner;
        return ret;
    }

    /**
     * Converts class into an array of objects which can be easily indexed
     *
     * @return
     */
    public Object[] toList() {
        Object[] data = {this.name, this.world, this.desc, this.win, this.loss, id, this.picture, this.owner};
        return data;
    }
}
