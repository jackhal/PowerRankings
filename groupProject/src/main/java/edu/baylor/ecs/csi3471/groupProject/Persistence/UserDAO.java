package edu.baylor.ecs.csi3471.groupProject.Persistence;

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
 * UserDao Class
 * This is the Data Access Object that updates the User data without deleting older data
 */
public class UserDAO {

    /**
     * updateUser This function updates the user profile in the database.
     *
     * @param user the user we want to update
     * @throws IOException if the file is not found
     */
    // update one specific user from
    public void updateUser(User user) throws IOException { // Testing done
        if (user == null) {
            return;
        }
        Runner.logger.info("updating the user");
        List<String[]> data = new ArrayList<>();
        ArrayList<User> savedUsers = getUsers();

        for (User u : savedUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                u = user;
            }
            data.add(new String[]{u.getUsername(), u.getPassword(), u.getEmail(), u.getName(),
                    String.valueOf(u.getAge()), String.valueOf(u.getFunds()), String.valueOf(u.getBet()),
                    String.valueOf(u.isVoted()), String.valueOf(u.isAdmin()), u.getDescription(), u.getCurrentVote(),
                    String.valueOf(u.getCurrentStreak()), u.getLastLogin()});
        }

        File tsvOut = new File("UserFile.tsv");
        PrintWriter pw = new PrintWriter(tsvOut);
        pw.write(
                "Username	Password	Email	Name	Age	Currency	Bet	Voted	Admin	Description	CurrentVote	CurrentStreak	LastLogin");
        pw.write("\n");
        for (String s[] : data) {
            pw.write(String.join("\t", s));
            pw.write("\n");
        }
        pw.close();
    }

    /**
     * getUsers This function returns a list of the users as per the database.
     *
     * @return list of all the users from the user file
     * @throws IOException if file is not found
     */
    // Get full list of users from file
    public ArrayList<User> getUsers() throws IOException {
        Runner.logger.info("getting all the users from the file into list");
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader br = new BufferedReader(new FileReader("UserFile.tsv"));
        String line = "";
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] user = line.split("\t");
            User curr = new User(user);

            users.add(curr);
        }

        return users;
    }

    /**
     * findCurrentBal This function returns the user's total balance in coins.
     *
     * @param username given username
     * @return the balance of the user
     */
    public int findCurrentBal(String username) throws NullPointerException { // tested
        if (username == null) {
            throw new NullPointerException();
        }
        try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while (sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if (data[0].equals(username)) {
                    return Integer.parseInt(data[5]);
                }
            }
            return 0;
        } catch (FileNotFoundException e) {
            Runner.logger.severe("Unable to find File");
            return 0;
        }
    }

    /**
     * setCurrentBal This function changes the user balance in the database.
     *
     * @param username   user to change value of
     * @param newBalance new balance for user
     */
    public void setCurrentBal(String username, Integer newBalance) throws NullPointerException { // tested
        if (username == null) {
            throw new NullPointerException();
        }
        if (newBalance == null) {
            throw new NullPointerException();
        }
        try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while (sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if (data[0].equals(username)) {
                    data[5] = Integer.toString(newBalance);
                    User newUser = new User(data);
                    try {
                        updateUser(newUser);
                    } catch (IOException e) {
                        Runner.logger.severe("Exception found when trying to get begin login process");
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Runner.logger.severe("Unable to open User file");
            e.printStackTrace();
        }
    }

    /**
     * findCurrentStreak This function determines how long the user's current
     * longest streak is as per the database.
     *
     * @param username given username to find
     * @return
     */
    protected int findCurrentStreak(String username) {
        try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while (sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if (data[0].equals(username)) {
                    return Integer.parseInt(data[11]);
                }
            }
            return 0;
        } catch (FileNotFoundException e) {
            Runner.logger.severe("User file unable to open");
            return 0;
        }
    }

    /**
     * setCurrentStreak This function changes the current user streak in the
     * database.
     *
     * @param username  username of user to change
     * @param newStreak new streak to add to profile
     */
    protected void setCurrentStreak(String username, int newStreak) {
        try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while (sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if (data[0].equals(username)) {
                    data[11] = Integer.toString(newStreak);
                    User newUser = new User(data);
                    try {
                        updateUser(newUser);
                    } catch (IOException e) {
                        Runner.logger.severe("Can't update User");
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Runner.logger.severe("Can't open the User file");
            e.printStackTrace();
        }
    }

    /**
     * findLastLogin This function returns the last time the user logged in.
     *
     * @param username username of user to find
     * @return returns when they last logged in
     */
    protected String findLastLogin(String username) {
        try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while (sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if (data[0].equals(username)) {
                    return data[12];
                }
            }
            return "null";
        } catch (FileNotFoundException e) {
            Runner.logger.severe("Can't open the User file");
            return "null";
        }
    }

    /**
     * setLastLogin This function sets the last time the user logged in.
     *
     * @param username     username of user to find
     * @param newLastLogin new login date to set
     */
    protected void setLastLogin(String username, String newLastLogin) {
        try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while (sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if (data[0].equals(username)) {
                    data[12] = newLastLogin;
                    User newUser = new User(data);
                    try {
                        updateUser(newUser);
                    } catch (IOException e) {
                        Runner.logger.severe("Can't update User");
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Runner.logger.severe("Can't open the User file");
            e.printStackTrace();
        }
    }

    /**
     * Exports UserFile.tsv to UserFile.xlsx
     *
     * @param dirPath
     * @throws IOException
     */
    public void exportToExcel(String dirPath) throws IOException {
        Runner.logger.info("Exporting Users to excel");
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

        String[] vals = {"Username", "Password", "Email", "Name", "Age", "Currency", "Bet", "Voted", "Admin",
                "Description", "CurrentVote", "CurrentStreak", "LastLogin"};
        Cell headerCell = null;
        int currHeader = 0;
        for (String i : vals) {
            headerCell = header.createCell(currHeader);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(i);

            currHeader++;
        }

        List<User> users = this.getUsers();

        int currRow = 0;

        for (User u : users) {
            Row row = sheet.createRow(currRow + 1);
            Object[] data = u.toList();
            for (int i = 0; i < vals.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(String.valueOf(data[i]));
            }

            currRow++;
        }

        FileOutputStream out = new FileOutputStream(new File(dirPath + "/UserFile.xlsx"));

        workbook.write(out);
        out.close();
    }
}
