package edu.baylor.ecs.csi3471.groupProject.Business;

import java.util.Objects;

/**
 * Class User
 */
public class User {
	private String username;
	private String password;
	private String email;
	private String name;
	private int age;
	private int funds;
	private int bet;
	private boolean voted;
	private boolean admin;
	private String description;
	private String currentVote;
	private int currentStreak;
	private String lastLogin;

	/**
	 * User This is the custom constructor for the User class that uses a string
	 * array to populate the attribute of the User object.
	 * 
	 * @param data
	 */
	public User(String[] data) {
		try {
			this.username = data[0];
			this.password = data[1];
			this.email = data[2];
			this.name = data[3];
			this.age = Integer.parseInt(data[4]);
			this.funds = Integer.parseInt(data[5]);
			this.bet = Integer.parseInt(data[6]);
			this.voted = Boolean.parseBoolean(data[7]);
			this.admin = Boolean.parseBoolean(data[8]);
			this.description = data[9];
			this.currentVote = data[10];
			this.currentStreak = Integer.parseInt(data[11]);
			this.lastLogin = data[12];
		} catch (NullPointerException e) {
			Runner.logger.severe("can't parse the data up to 13 slots");
			e.printStackTrace();
		}
	}

	/**
	 * User() This is the default constructor for the user class.
	 */
	public User() {
		username = "";
		password = "";
		email = "";
		name = "";
		age = -1;
		funds = -1;
		bet = -1;
		voted = false;
		admin = false;
		description = "";
		currentVote = "";
		currentStreak = -1;
		lastLogin = "";
	}

	/**
	 * getUsername()
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * setUsername()
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * getPassword()
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setPassword()
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getEmail()
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setEmail
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getName()
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName()
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getAge()
	 * 
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * setAge()
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * getFunds()
	 * 
	 * @return
	 */
	public int getFunds() {
		return funds;
	}

	/**
	 * setFunds()
	 * 
	 * @param funds
	 */
	public void setFunds(int funds) {
		this.funds = funds;
	}

	/**
	 * getBet()
	 * 
	 * @return
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * setBet()
	 * 
	 * @param bet
	 */
	public void setBet(int bet) {
		this.bet = bet;
	}

	/**
	 * isVoted()
	 * 
	 * @return
	 */
	public boolean isVoted() {
		return voted;
	}

	/**
	 * setVoted()
	 * 
	 * @param voted
	 */
	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	/**
	 * isAdmin()
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * setAdmin()
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * getDescription()
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setDescription()
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getCurrentVote()
	 * 
	 * @return
	 */
	public String getCurrentVote() {
		return currentVote;
	}

	/**
	 * setCurrentVote()
	 * 
	 * @param currentVote
	 */
	public void setCurrentVote(String currentVote) {
		this.currentVote = currentVote;
	}

	/**
	 * setCurrentStreak()
	 * 
	 * @param currentStreak
	 */
	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	/**
	 * getCurrentStreak()
	 * 
	 * @return
	 */
	public int getCurrentStreak() {
		return this.currentStreak;
	}

	/**
	 * setLastLogin()
	 * 
	 * @param lastLogin
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * getLastLogin()
	 * 
	 * @return
	 */
	public String getLastLogin() {
		return this.lastLogin;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(getUsername(), user.getUsername());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUsername());
	}

	/**
	 * Converts class into an array of objects which can be easily indexed
	 * @return
	 */
	public Object[] toList() {
		Object[] data = { this.username, this.password, this.email, this.name, this.age, this.funds, this.bet,
				this.voted, this.admin, this.description, this.currentVote, this.currentStreak, this.lastLogin };

		return data;
	}

}