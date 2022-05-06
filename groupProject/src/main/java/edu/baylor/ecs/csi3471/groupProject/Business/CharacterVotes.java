package edu.baylor.ecs.csi3471.groupProject.Business;

import java.util.Objects;

/**
 * Class CharacterVotes
 * This class keeps track of the choices the User make with their corresponding bets.
 * @Author: Matt Harrison
 *
 */
public class CharacterVotes {
	private String matchAChoice;
	private String User;
	private String matchBChoice;
	private String matchCChoice;
	private String matchDChoice;
	private Integer matchABet;
	private Integer matchBBet;
	private Integer matchCBet;
	private Integer matchDBet;

	/**
	 * CharacterVotes
	 * This function is the default constructor for the Character
	 * Votes class.
	 */
	public CharacterVotes() {
		this.User = "";
		this.matchAChoice = "";
		this.matchBChoice = "";
		this.matchCChoice = "";
		this.matchDChoice = "";
		this.matchABet = -1;
		this.matchBBet = -1;
		this.matchCBet = -1;
		this.matchDBet = -1;
	}

    /**
     * Character Votes
     * This function is the custom constructor for the CharacterVotes
     * class that uses a String array to populate the attributes.
     * @param a- which is a string that describes CharacterVotes
     */
    public CharacterVotes(String[] a){
        this.User = a[0];
        this.matchAChoice = a[1];
        this.matchBChoice = a[2];
        this.matchCChoice = a[3];
        this.matchDChoice = a[4];
        this.matchABet = Integer.parseInt(a[5]);
        this.matchBBet = Integer.parseInt(a[6]);
        this.matchCBet = Integer.parseInt(a[7]);
        this.matchDBet = Integer.parseInt(a[8]);
    }

    /**
     * getMatchABet()
     * The function of this is to return the bet from match A from the Character Vote.
     * @return Integer
     */
    public Integer getMatchABet() {
        return matchABet;
    }

    /**
     * getMatchBBet()
     * The function of this is to return the bet from match B from the Character Vote.
     * @return Integer
     */
    public Integer getMatchBBet() {
        return matchBBet;
    }

    /**
     * getMatchCBet()
     * The function of this is to return the bet from match C from the Character Vote.
     * @return Integer
     */
    public Integer getMatchCBet() {
        return matchCBet;
    }

    /**
     * getMatchDBet()
     * The function of this is to return the bet from match D from the Character Vote.
     * @return Integer
     */
    public Integer getMatchDBet() {
        return matchDBet;
    }

    /**
     * getMatchAChoice()
     * The function of this is to return the vote from match A from the Character Vote.
     * @return String
     */
    public String getMatchAChoice() {
        return matchAChoice;
    }

    /**
     * getMatchBChoice()
     * The function of this is to return the vote from match B from the Character Vote.
     * @return String
     */
    public String getMatchBChoice() {
        return matchBChoice;
    }

    /**
     * getMatchCChoice()
     * The function of this is to return the vote from match C from the Character Vote.
     * @return String
     */
    public String getMatchCChoice() {
        return matchCChoice;
    }

    /**
     * setMatchABet()
     * The function of this is to set the wager for match A.
     * @param matchABet
     */
    public void setMatchABet(Integer matchABet) {
        this.matchABet = matchABet;
    }

    /**
     * setMatchAChoice()
     * The function of this is to set the vote for match A.
     * @param matchAChoice
     */
    public void setMatchAChoice(String matchAChoice) {
        this.matchAChoice = matchAChoice;
    }

    /**
     * setMatchBBet()
     * The function of this is to set the wager for match B.
     * @param matchBBet
     */
    public void setMatchBBet(Integer matchBBet) {
        this.matchBBet = matchBBet;
    }

    /**
     * setMatchBChoice()
     * The function of this is to set the vote for match B
     * @param matchBChoice
     */
    public void setMatchBChoice(String matchBChoice) {
        this.matchBChoice = matchBChoice;
    }

    /**
     * setMatchCBet()
     * The function of this is to set the wager for match C.
     * @param matchCBet
     */
    public void setMatchCBet(Integer matchCBet) {
        this.matchCBet = matchCBet;
    }

    /**
     * setMatchCChoice()
     * The function of this is to set the vote for match C.
     * @param matchCChoice
     */
    public void setMatchCChoice(String matchCChoice) {
        this.matchCChoice = matchCChoice;
    }

    /**
     * setMatchDBet()
     * The function of this is to set the wager for match D.
     * @param matchDBet
     */
    public void setMatchDBet(Integer matchDBet) {
        this.matchDBet = matchDBet;
    }

    /**
     * getMatchDChoice()
     * The function of this is to return the vote for match D.
     * @return
     */
    public String getMatchDChoice() {
        return matchDChoice;
    }

    /**
     * setMatchDChoice()
     * The function of this is to set the vote for match D.
     * @param matchDChoice
     */
    public void setMatchDChoice(String matchDChoice) {
        this.matchDChoice = matchDChoice;
    }

    /**
     * setUser()
     * The function is to set the user of the CharacterVote
     * @param user
     */
    public void setUser(String user) {
        User = user;
    }

    /**
     * getUser()
     * the function is to return the user of the CharacterVote
     * @return String
     */
    public String getUser() {
        return User;
    }

    /**
     * toString()
     * the function is to return the CharacterVotes in a string form.
     * @return String
     */
    @Override
    public String toString() {
        return "CharacterVotes{" +
                "matchAChoice='" + matchAChoice + '\'' +
                ", User='" + User + '\'' +
                ", matchBChoice='" + matchBChoice + '\'' +
                ", matchCChoice='" + matchCChoice + '\'' +
                ", matchDChoice='" + matchDChoice + '\'' +
                ", matchABet=" + matchABet +
                ", matchBBet=" + matchBBet +
                ", matchCBet=" + matchCBet +
                ", matchDBet=" + matchDBet +
                '}';
    }
    /**
     * equals()
     * The function is to determine whether or not two characterVotes have the same User
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterVotes)) return false;
        CharacterVotes that = (CharacterVotes) o;
        return Objects.equals(getUser(), that.getUser());
    }
    /**
     * hashCode()
     * The function is to return the hashcode
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }
	public Object[] toList() {
		Object[] data = { this.User, this.matchAChoice, this.matchBChoice, this.matchCChoice, this.matchDChoice,
				this.matchABet, this.matchBBet, this.matchCBet, this.matchDBet };
		return data;
	}
}
