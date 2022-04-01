package edu.baylor.ecs.csi3471.groupProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharacterDAO extends Character{
	public Character findChar(String name, String world){
        File file = new File("CharacterFile.csv");

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[]split = line.split("\t");
                lineNum++;
                if(split[0].equals(name) && split[1].equals(world)) {
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
	
	//When you change values for a character, use setters to update and then use update to store changes
    public void updateCSV(Integer id){
        String filePath = "CharacterFile.csv";
        //Instantiating the Scanner class to read the file
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String oldLine = "";
        String line = "";
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            buffer.append(line+System.lineSeparator());
            String [] split = line.split("\t");
            if(split[5].equals(String.valueOf(id))){
                oldLine = line;
            }
        }
        String fileContents = buffer.toString();
        //System.out.println(fileContents);
        //closing the Scanner object
        sc.close();
        //String oldLine = "No preconditions and no impediments. Simply Easy Learning!";
        String newLine = charToCSV();
        newLine = newLine.replace("\n", "");
        //Replacing the old line with new line
        fileContents = fileContents.replace(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("");
        //System.out.println("new data: "+fileContents);
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
}
