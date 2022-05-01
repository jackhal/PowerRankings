package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.CharacterVotes;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class CharacterVotesDAO
 */
public class CharacterVotesDAO extends CharacterVotes {

    /**
     * getCharacterVotes
     * This function returns the votes for each character based on the database entries.
     * @return ArrayList
     * @throws IOException
     */
    public ArrayList<CharacterVotes> getCharacterVotes() throws IOException {
        Runner.logger.info("getting all the users from the file into list");
        ArrayList<CharacterVotes> users = new ArrayList<CharacterVotes>();
        BufferedReader br = new BufferedReader(new FileReader("CharacterVotes.tsv"));
        String line = "";
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] charVotes = line.split("\t");
            CharacterVotes curr = new CharacterVotes(charVotes);

            users.add(curr);
        }

        return users;
    }

    /**
     * UpdateCharacterVotes
     * This function updates the database entries when the votes are updated.
     * @param characterVotes
     * @throws IOException
     */
    public void updateCharacterVotes(CharacterVotes characterVotes) throws IOException {
        Runner.logger.info("updating the user");
        List<String[]> data = new ArrayList<>();
        ArrayList<CharacterVotes> savedUsers = getCharacterVotes();
        boolean pig = false;
        if(savedUsers.size() == 0){
            CharacterVotes u = characterVotes;
            data.add(new String[]{u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
                    u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
                    String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet())});
            pig = true;
        }
        for (CharacterVotes u : savedUsers) {
            if (u.getUser().equals(characterVotes.getUser())) {
                u = characterVotes;
                pig = true;
            }
            data.add(new String[]{u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
                    u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
                    String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet())});
       }
        if(!pig){
            CharacterVotes u = characterVotes;
            data.add(new String[]{u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
                    u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
                    String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet())});
        }

        File tsvOut = new File("CharacterVotes.tsv");
        PrintWriter pw = new PrintWriter(tsvOut);
        pw.write("User	MatchAChoice	MatchBChoice	MatchCChoice	MatchDChoice	ABet	BBet	CBet	DBet");
        pw.write("\n");
        for(String s[]: data) {
            pw.write(String.join("\t", s));
            pw.write("\n");
        }
        pw.close();
    }

    /**
     * getCharacterVoteByUsername
     * This function parses the database for the votes as per
     * character and by user.
     * @param username
     * @return CharacterVotes
     * @throws Exception
     */
    public CharacterVotes getCharacterVoteByUsername(String username) throws Exception {
        Runner.logger.info("getting user from username");
        Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
        String data[];
        while(sc.hasNextLine()) {
            data = sc.nextLine().split("\t");
            if(data[0].equals(username)) {
                CharacterVotes curr = new CharacterVotes(data);
                return curr;
            }
        }
        Runner.logger.warning("username did not exist");
        throw new NoSuchElementException("This CharacterVotes does not exist");
    }

    /**
     * findCurrentVote
     * This function finds the number of votes for a character.
     * @param username
     * @param match
     * @return String
     */
    protected String findCurrentVote(String username, Integer match) {
        try {
            Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                    return data[match];
                }
            }
            return "";
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    /**
     * setCurrentVote
     * This function finds the currentVote
     * @param username
     * @param charVote
     * @param match
     */
    protected void setCurrentVote(String username, String charVote, Integer match) {
        try {
            Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                    data[match] = charVote;
                    CharacterVotes newVote = new CharacterVotes(data);
                    try {
                        updateCharacterVotes(newVote);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * findCurrentBet
     * This function returns the bet the user
     * places on each character.
     * @param username
     * @param match
     * @return int
     */
    protected int findCurrentBet(String username, Integer match) {
        try {
            Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                    return Integer.parseInt(data[match + 4]);
                }
            }
            return 0;
        } catch (FileNotFoundException e) {
            return 0;
        }
    }

    protected void setCurrentBet(String username, int newBet, Integer match) {
        try {
            Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                    data[match+4] = Integer.toString(newBet);
                    CharacterVotes charVotes = new CharacterVotes(data);
                    try {
                        updateCharacterVotes(charVotes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
