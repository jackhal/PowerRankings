package edu.baylor.ecs.csi3471.groupProject.Business;

import java.util.Objects;

/**
 * Class CharacterVotes This class keeps track of the choices the User make with
 * their corresponding bets.
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
	 * CharacterVotes This function is the default constructor for the Character
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
	 * Character Votes This function is the custom constructor for the
	 * CharacterVotes class that uses a String array to populate the attributes.
	 * 
	 * @param a
	 */
	public CharacterVotes(String[] a) {
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
	 * 
	 * @return
	 */
	public Integer getMatchABet() {
		return matchABet;
	}

	/**
	 * getMatchBBet()
	 * 
	 * @return
	 */
	public Integer getMatchBBet() {
		return matchBBet;
	}

	/**
	 * getMatchCBet()
	 * 
	 * @return
	 */
	public Integer getMatchCBet() {
		return matchCBet;
	}

	/**
	 * getMatchDBet()
	 * 
	 * @return
	 */
	public Integer getMatchDBet() {
		return matchDBet;
	}

	/**
	 * getMatchAChoice()
	 * 
	 * @return
	 */
	public String getMatchAChoice() {
		return matchAChoice;
	}

	/**
	 * getMatchBChoice()
	 * 
	 * @return
	 */
	public String getMatchBChoice() {
		return matchBChoice;
	}

	/**
	 * getMatchCChoice()
	 * 
	 * @return
	 */
	public String getMatchCChoice() {
		return matchCChoice;
	}

	/**
	 * setMatchABet()
	 * 
	 * @param matchABet
	 */
	public void setMatchABet(Integer matchABet) {
		this.matchABet = matchABet;
	}

	/**
	 * setMatchAChoice()
	 * 
	 * @param matchAChoice
	 */
	public void setMatchAChoice(String matchAChoice) {
		this.matchAChoice = matchAChoice;
	}

	/**
	 * setMatchBBet()
	 * 
	 * @param matchBBet
	 */
	public void setMatchBBet(Integer matchBBet) {
		this.matchBBet = matchBBet;
	}

	/**
	 * setMatchBChoice()
	 * 
	 * @param matchBChoice
	 */
	public void setMatchBChoice(String matchBChoice) {
		this.matchBChoice = matchBChoice;
	}

	/**
	 * setMatchCBet()
	 * 
	 * @param matchCBet
	 */
	public void setMatchCBet(Integer matchCBet) {
		this.matchCBet = matchCBet;
	}

	/**
	 * setMatchCChoice()
	 * 
	 * @param matchCChoice
	 */
	public void setMatchCChoice(String matchCChoice) {
		this.matchCChoice = matchCChoice;
	}

	/**
	 * setMatchDBet()
	 * 
	 * @param matchDBet
	 */
	public void setMatchDBet(Integer matchDBet) {
		this.matchDBet = matchDBet;
	}

	/**
	 * getMatchDChoice()
	 * 
	 * @return
	 */
	public String getMatchDChoice() {
		return matchDChoice;
	}

	/**
	 * setMatchDChoice()
	 * 
	 * @param matchDChoice
	 */
	public void setMatchDChoice(String matchDChoice) {
		this.matchDChoice = matchDChoice;
	}

	/**
	 * setUser()
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		User = user;
	}

	/**
	 * getUser()
	 * 
	 * @return
	 */
	public String getUser() {
		return User;
	}

	/**
	 * toString()
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "CharacterVotes{" + "matchAChoice='" + matchAChoice + '\'' + ", User='" + User + '\''
				+ ", matchBChoice='" + matchBChoice + '\'' + ", matchCChoice='" + matchCChoice + '\''
				+ ", matchDChoice='" + matchDChoice + '\'' + ", matchABet=" + matchABet + ", matchBBet=" + matchBBet
				+ ", matchCBet=" + matchCBet + ", matchDBet=" + matchDBet + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CharacterVotes))
			return false;
		CharacterVotes that = (CharacterVotes) o;
		return Objects.equals(getUser(), that.getUser());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUser());
	}

	/**
	 * Converts class into an array of objects which can be easily indexed
	 * @return
	 */
	public Object[] toList() {
		Object[] data = { this.User, this.matchAChoice, this.matchBChoice, this.matchCChoice, this.matchDChoice,
				this.matchABet, this.matchBBet, this.matchCBet, this.matchDBet };
		return data;
	}
}
