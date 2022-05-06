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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class CharacterVotesDAO
 */
public class CharacterVotesDAO extends CharacterVotes {

	/**
	 * getCharacterVotes This function returns the votes for each character based on
	 * the database entries.
	 * 
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
	 * UpdateCharacterVotes This function updates the database entries when the
	 * votes are updated.
	 * 
	 * @param characterVotes
	 * @throws IOException
	 */
	public void updateCharacterVotes(CharacterVotes characterVotes) throws IOException { // tested
		Runner.logger.info("updating the user");
		List<String[]> data = new ArrayList<>();
		ArrayList<CharacterVotes> savedUsers = getCharacterVotes();
		boolean pig = false;
		if (savedUsers.size() == 0) {
			CharacterVotes u = characterVotes;
			data.add(new String[] { u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
					u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
					String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet()) });
			pig = true;
		}
		for (CharacterVotes u : savedUsers) {
			if (u.getUser().equals(characterVotes.getUser())) {
				u = characterVotes;
				pig = true;
			}
			data.add(new String[] { u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
					u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
					String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet()) });
		}
		if (!pig) {
			CharacterVotes u = characterVotes;
			data.add(new String[] { u.getUser(), u.getMatchAChoice(), u.getMatchBChoice(), u.getMatchCChoice(),
					u.getMatchDChoice(), String.valueOf(u.getMatchABet()), String.valueOf(u.getMatchBBet()),
					String.valueOf(u.getMatchCBet()), String.valueOf(u.getMatchDBet()) });
		}

		File tsvOut = new File("CharacterVotes.tsv");
		PrintWriter pw = new PrintWriter(tsvOut);
		pw.write("User	MatchAChoice	MatchBChoice	MatchCChoice	MatchDChoice	ABet	BBet	CBet	DBet");
		pw.write("\n");
		for (String s[] : data) {
			pw.write(String.join("\t", s));
			pw.write("\n");
		}
		pw.close();
	}

	/**
	 * removeCharacterVote This function removes a specific user's vote on a
	 * specific character
	 * 
	 * @param username
	 * @param characterName
	 * @throws Exception
	 */
	public void removeCharacterVote(String username, String characterName) throws Exception { // tested?
		Runner.logger.info("removing " + characterName + " from " + username);
		CharacterVotes votes = this.getCharacterVoteByUsername(username);

		if (votes.getMatchAChoice().equals(characterName)) {
			votes.setMatchAChoice("");
			votes.setMatchABet(0);
			this.updateCharacterVotes(votes);
		} else if (votes.getMatchBChoice().equals(characterName)) {
			votes.setMatchBChoice("");
			votes.setMatchBBet(0);
			this.updateCharacterVotes(votes);
		} else if (votes.getMatchCChoice().equals(characterName)) {
			votes.setMatchCChoice("");
			votes.setMatchCBet(0);
			this.updateCharacterVotes(votes);
		} else if (votes.getMatchDChoice().equals(characterName)) {
			votes.setMatchDChoice("");
			votes.setMatchDBet(0);
			this.updateCharacterVotes(votes);
		}
	}

	/**
	 * getCharacterVoteByUsername This function parses the database for the votes as
	 * per character and by user.
	 * 
	 * @param username
	 * @return CharacterVotes
	 * @throws Exception
	 */
	public CharacterVotes getCharacterVoteByUsername(String username) throws Exception { // tested
		Runner.logger.info("getting user from username");
		Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
		String data[];
		while (sc.hasNextLine()) {
			data = sc.nextLine().split("\t");
			if (data[0].equals(username)) {
				CharacterVotes curr = new CharacterVotes(data);
				return curr;
			}
		}
		Runner.logger.warning("username did not exist");
		throw new NoSuchElementException("This CharacterVotes does not exist");
	}

	/**
	 * findCurrentVote This function finds the number of votes for a character.
	 * 
	 * @param username
	 * @param match
	 * @return String
	 */
	public String findCurrentVote(String username, Integer match) { // tested
		try {
			Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
			String data[];
			while (sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if (data[0].equals(username)) {
					return data[match];
				}
			}
			return "";
		} catch (FileNotFoundException e) {
			return "";
		}
	}

	/**
	 * setCurrentVote This function finds the currentVote
	 * 
	 * @param username
	 * @param charVote
	 * @param match
	 */
	protected void setCurrentVote(String username, String charVote, Integer match) {
		try {
			Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
			String data[];
			while (sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if (data[0].equals(username)) {
					data[match] = charVote;
					CharacterVotes newVote = new CharacterVotes(data);
					try {
						updateCharacterVotes(newVote);
					} catch (IOException e) {
						Runner.logger.severe("Unable to update Character Votes");
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			Runner.logger.severe("Unable to find File");
			e.printStackTrace();
		}
	}

	/**
	 * findCurrentBet This function returns the bet the user places on each
	 * character.
	 * 
	 * @param username
	 * @param match
	 * @return int
	 */
	public int findCurrentBet(String username, Integer match) { // tested
		try {
			Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
			String data[];
			while (sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if (data[0].equals(username)) {
					return Integer.parseInt(data[match + 4]);
				}
			}
			return 0;
		} catch (FileNotFoundException e) {
			return 0;
		}
	}

	/**
	 * setCurrentBet This function sets a specific user's current bet
	 * 
	 * @param username
	 * @param newBet
	 * @param match
	 */
	protected void setCurrentBet(String username, int newBet, Integer match) {
		try {
			Scanner sc = new Scanner(new File("CharacterVotes.tsv"));
			String data[];
			while (sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if (data[0].equals(username)) {
					data[match + 4] = Integer.toString(newBet);
					CharacterVotes charVotes = new CharacterVotes(data);
					try {
						updateCharacterVotes(charVotes);
					} catch (IOException e) {
						Runner.logger.severe("Unable to update CharacterVotes");
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			Runner.logger.severe("Unable to find File");
			e.printStackTrace();
		}
	}
	
	public Integer getBetByCharacterName(String username, String characterName) throws Exception {
		CharacterVotes v = this.getCharacterVoteByUsername(username);
		if (v.getMatchAChoice().equals(characterName)) {
			return v.getMatchABet();
		} else if (v.getMatchBChoice().equals(characterName)) {
			return v.getMatchBBet();
		} else if (v.getMatchCChoice().equals(characterName)) {
			return v.getMatchCBet();
		} else if (v.getMatchDChoice().equals(characterName)) {
			return v.getMatchDBet();
		}
		
		return 0;
	}

	public void exportToExcel(String dirPath) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Users");

		// Set sheet headers
		Row header = sheet.createRow(0);
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setBold(true);
		headerStyle.setFont(font);

		String[] vals = { "User", "MatchAChoice", "MatchBChoice", "MatchDChoice", "ABet", "BBet", "CBet", "DBet" };
		Cell headerCell = null;
		int currHeader = 0;
		for (String i : vals) {
			headerCell = header.createCell(currHeader);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue(i);

			currHeader++;
		}

		List<CharacterVotes> chars = this.getCharacterVotes();

		int currRow = 0;

		for (CharacterVotes c : chars) {
			Row row = sheet.createRow(currRow + 1);
			Object[] data = c.toList();
			for (int i = 0; i < vals.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(String.valueOf(data[i]));
			}

			currRow++;
		}

		FileOutputStream out = new FileOutputStream(new File(dirPath + "/CharacterVotes.xlsx"));

		workbook.write(out);
		out.close();
	}

}
