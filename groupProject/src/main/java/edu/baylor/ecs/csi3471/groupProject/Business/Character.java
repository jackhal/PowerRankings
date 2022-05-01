package edu.baylor.ecs.csi3471.groupProject.Business;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import edu.baylor.ecs.csi3471.groupProject.UI.CharacterLayout;

import javax.swing.*;

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

    public Character(){
        id = id++;
    }

    /**
     * @param name string name of character
     * @param world fanstasy world of character
     * @param desc description of character and  abilites
     * @param win total number of wins
     * @param loss total number of losses
     * @param picture url for image of character
     * @param owner user who uploaded character
     */
    public Character(String name, String world, String desc, Integer win, Integer loss, String picture, String owner){
        Runner.logger.info("Character " + name + " created");
        this.name = name;
        this.world = world;
        this.desc = desc;
        this.win = win;
        this.loss = loss;
        this.picture = picture;
        this.owner = owner;
        id = id++;
    }

    /**
     * @param line line of data from database to be split and added to character
     */
    public Character(String line){
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
            ratio = Double.valueOf((1.0 * win) /(1.0 * (win + loss)));
        } else {
            if(win == 0){
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
        id = id++;
    }

    /**
     * @return user who made character
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner user who made character
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return charcater id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return ratio of wins to losses
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * @return number of losses
     */
    public Integer getLoss() {
        return loss;
    }

    /**
     * @param loss number of losses
     */
    public void setLoss(Integer loss) {
        this.loss = loss;
        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) /(1.0 * (win + loss)));
        } else {
            if(win == 0){
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
    }

    /**
     * @return  number of wins
     */
    public Integer getWin() {
        return win;
    }

    /**
     * @param win number of wins
     */
    public void setWin(Integer win) {
        this.win = win;
        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) /(1.0 * (win + loss)));
        } else {
            if(win == 0){
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
    }

    /**
     * @return decription of character
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc decription of character
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return worl dof character
     */
    public String getWorld() {
        return world;
    }

    /**
     * @param world world of charcater
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * @return name of character
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of charcater
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture url of character image
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCurrVote() {
        return currVote;
    }

    public void setCurrVote(Integer currVote) {
        this.currVote = currVote;
    }

    /**
     * @param o charctaer object
     * @return true if they are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) && Objects.equals(world, character.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, world);
    }

    public void displayChar(){
        logger.info("Character displayed");
        new CharacterLayout(this);
        //This may open a dialog box to see character data
    }

    public String charToCSV(){
        logger.info("Character converted to csv form to print");
        String ret = "\n";
        ret += name + "\t" + world + "\t" + desc + "\t";
        ret += win + "\t" + loss + "\t" + id + "\t" + picture + "\t" + owner;
        return ret;
    }
}


