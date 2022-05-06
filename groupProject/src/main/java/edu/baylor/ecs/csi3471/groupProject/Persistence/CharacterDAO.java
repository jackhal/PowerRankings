package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;

import javax.swing.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Class CharacterDAO
 */
public class CharacterDAO extends Character {
	/**
	 * findChar This function
	 * 
	 * @param name  name of character
	 * @param world world of charcter
	 * @return Character object matching name and world
	 */
	public Character findChar(String name, String world) throws Exception { // tested
		if (name == null || world == null) {
			throw new Exception();
		}
		File file = new File("CharacterFile.csv");
		Runner.logger.info("Searching file for " + name);

		try {
			Scanner scanner = new Scanner(file);

			// now read the file line by line...
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split("\t");
				lineNum++;
				if (split[0].equals(name) && split[1].equals(world)) {
					Character c = new Character(line);
					return c;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Character c = new Character();
		return c;
	}

	/**
	 * updateCSV this function updates the database entry associated with a
	 * character
	 * 
	 * @param id id of character we want to update
	 */
	// When you change values for a character, use setters to update and then use
	// update to store changes
	public void updateCSV(Integer id) { // tested??
		Runner.logger.info("Updating character in database with id " + id);
		String filePath = "CharacterFile.csv";
		// Instantiating the Scanner class to read the file
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String oldLine = "";
		String line = "";
		// instantiating the StringBuffer class
		StringBuffer buffer = new StringBuffer();
		// Reading lines of the file and appending them to StringBuffer
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			buffer.append(line + System.lineSeparator());
			String[] split = line.split("\t");
			if (split[5].equals(String.valueOf(id))) {
				oldLine = line;
			}
		}
		String fileContents = buffer.toString();
		// System.out.println(fileContents);
		// closing the Scanner object
		sc.close();
		// String oldLine = "No preconditions and no impediments. Simply Easy
		// Learning!";
		String newLine = charToCSV();
		newLine = newLine.replace("\n", "");
		// Replacing the old line with new line
		fileContents = fileContents.replace(oldLine, newLine);
		// instantiating the FileWriter class
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("");
		// System.out.println("new data: "+fileContents);
		try {
			writer.append(fileContents);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeList This function creates a list of characters from the database
	 * 
	 * @return ArrayList of al characters in the database
	 */
	public ArrayList<Character> makeList() { // tested
		Runner.logger.info("Making list of all characters from database");
		String filePath = "CharacterFile.csv";
		ArrayList<Character> cList = new ArrayList();
		// Instantiating the Scanner class to read the file
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = sc.nextLine();
		// instantiating the StringBuffer class
		StringBuffer buffer = new StringBuffer();
		// Reading lines of the file and appending them to StringBuffer
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			Character c = new Character(line);
			cList.add(c);
		}
		return cList;
	}
	
	public ArrayList<Character> makeCharacterRoundsList() {
		Runner.logger.info("Making list of character rounds from database");
		String filePath = "CharacterRounds.csv";
		ArrayList<Character> cList = new ArrayList();
		// Instantiating the Scanner class to read the file
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = sc.nextLine();
		// instantiating the StringBuffer class
		StringBuffer buffer = new StringBuffer();
		// Reading lines of the file and appending them to StringBuffer
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			Character c = new Character(line);
			cList.add(c);
		}
		return cList;
	}

	/**
	 * addCharacter this function adds a character entry to the database
	 * 
	 * @param name     name of character
	 * @param world    world of character
	 * @param desc     description of character
	 * @param URL      url of character
	 * @param currUser user who made character
	 */
	protected void addCharacter(String name, String world, String desc, String URL, String currUser) { // cant test,
																										// because we
																										// dont have a
																										// remove
																										// character
																										// option
		Runner.logger.info("Adding " + name + " to database");
		try {
			List<String[]> allData = new ArrayList<String[]>();
			Scanner sc = new Scanner(new File("CharacterFile.csv"));
			String data[];
			while (sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				allData.add(data);
			}

			PrintWriter pw = new PrintWriter(new File("CharacterFile.csv"));
			for (String currLine[] : allData) {
				pw.write(String.join("\t", currLine));
				pw.write("\n");
			}
			Character tempChar = new Character();
			pw.write(name + "\t" + world + "\t" + desc + "\t" + "0" + "\t" + "0" + "\t" + tempChar.getId().toString()
					+ "\t" + URL + "\t" + currUser);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * doesCharExist This function checks if the character exists in the database.
	 * 
	 * @param name  name of character to search for
	 * @param world world of character to search for
	 * @return true if character exsists
	 */
	public boolean doesCharExist(String name, String world) { // tested
		Runner.logger.info("Checking if " + name + " is in database");
		try {
			Scanner sc = new Scanner(new File("CharacterFile.csv"));
			String data[];
			while (sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if (data[0].equalsIgnoreCase(name) && data[1].equalsIgnoreCase(world)) {
					Runner.logger.info(name + " is in database");
					return true;
				}
			}
			Runner.logger.info(name + " is not in database");
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		}
	}

	public long getCharacterRoundsLines() {
		long lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("CharacterRounds.csv"))) {
			while (reader.readLine() != null)
				lines++;
		} catch (IOException ei) {
			Runner.logger.severe("Unable to open CharacterRounds.csv");
			ei.printStackTrace();
		}

		return lines;
	}

	public void reloadCharacterRounds() {
		Runner.logger.info("newTournament generated");
		// create new tournament
//					FileWriter fw = null;
//					try
//					{
//						fw = new FileWriter("CharacterRounds.csv", true);
//					}
//					catch(Exception ex)
//					{
//						Runner.logger.severe("Unable to open CharacterRounds.csv");
//						System.out.println(ex.getStackTrace());
//					}
//
//
//					BufferedWriter bw = new BufferedWriter(fw);
		// PrintWriter pWriter = new PrintWriter(bw);
		PrintWriter pWriter = null;

		try {
			pWriter = new PrintWriter("CharacterRounds.csv");
		} catch (Exception ex) {
			Runner.logger.severe("unable to open CharacterRounds.csv");
			System.out.println(ex.getStackTrace());
		}

		pWriter.flush();
		pWriter.write("Name\tWorld\tDescription\tWin\tLoss\tID\tURL\tOwner");

		CharacterDAO charDataBase = new CharacterDAO();
		ArrayList<Character> myChars = charDataBase.makeList();

		Random rand = new Random(); // instance of random class

		int upperbound = myChars.size() - 1; // 15
		// generate random values from 0-14

		ArrayList<Integer> usedNumbers = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			int int_random = rand.nextInt(upperbound);
			// add entries to character rounds

			while (usedNumbers.contains(int_random)) {
				int_random = rand.nextInt(upperbound);
			}

			usedNumbers.add(int_random);

			try {
				pWriter.flush();
				pWriter.print(myChars.get(int_random).charToCSV());
				pWriter.flush();
			} catch (Exception ex) {
				Runner.logger.severe("Can't write character");
				System.out.println(ex.getStackTrace());
			}
		}

		usedNumbers.clear();

		JOptionPane.showMessageDialog(null, "The next login will display the new tournament!");
	}

	public void exportCharactersToExcel(String dirPath) throws IOException {
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

		String[] vals = { "Name", "World", "Description", "Win", "Loss", "ID", "URL", "Owner" };
		Cell headerCell = null;
		int currHeader = 0;
		for (String i : vals) {
			headerCell = header.createCell(currHeader);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue(i);

			currHeader++;
		}

		List<Character> chars = this.makeList();

		int currRow = 0;

		for (Character c : chars) {
			Row row = sheet.createRow(currRow + 1);
			Object[] data = c.toList();
			for (int i = 0; i < vals.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(String.valueOf(data[i]));
			}

			currRow++;
		}

		FileOutputStream out = new FileOutputStream(new File(dirPath + "/CharacterFile.xlsx"));

		workbook.write(out);
		workbook.close();
		out.close();
	}
	
	public void exportCharacterRoundsToExcel(String dirPath) throws IOException {
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

		String[] vals = { "Name", "World", "Description", "Win", "Loss", "ID", "URL", "Owner" };
		Cell headerCell = null;
		int currHeader = 0;
		for (String i : vals) {
			headerCell = header.createCell(currHeader);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue(i);

			currHeader++;
		}

		List<Character> chars = this.makeCharacterRoundsList();

		int currRow = 0;

		for (Character c : chars) {
			Row row = sheet.createRow(currRow + 1);
			Object[] data = c.toList();
			for (int i = 0; i < vals.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(String.valueOf(data[i]));
			}

			currRow++;
		}

		FileOutputStream out = new FileOutputStream(new File(dirPath + "/CharacterRounds.xlsx"));

		workbook.write(out);
		workbook.close();
		out.close();
	}
}
