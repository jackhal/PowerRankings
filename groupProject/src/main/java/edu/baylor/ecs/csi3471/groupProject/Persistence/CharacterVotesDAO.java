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

public class CharacterVotesDAO extends CharacterVotes {

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
    public void updateCharacterVotes(CharacterVotes characterVotes) throws IOException {
        Runner.logger.info("updating the user");
        List<String[]> data = new ArrayList<>();
        ArrayList<CharacterVotes> savedUsers = getCharacterVotes();
        if(savedUsers.size() == 0){
            CharacterVotes u = characterVotes;
            data.add(new String[]{u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
                    u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
                    String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet())});
        }

        for (CharacterVotes u : savedUsers) {
            if (u.getUser().equals(characterVotes.getUser())) {
                u = characterVotes;
            }
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
