package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterDAO {
    public void writeToFile(User newUser)
    {
        try
        {
            List<String[]> allData = new ArrayList<String[]>();
            BufferedReader br = new BufferedReader(new FileReader("UserFile.tsv"));
            String data[];
            String line = "";
            while((line = br.readLine()) != null) {
                data = line.split("\t");
                allData.add(data);
            }


            File tsvOut = new File("UserFile.tsv");
            PrintWriter pw = new PrintWriter(tsvOut);
            for(String currLine[]: allData) {
                pw.write(String.join("\t", currLine));
                pw.write("\n");
            }

            Runner.logger.info("Writing new user to UserFile.tsv");
            pw.write(newUser.getUsername() + "\t" + newUser.getPassword() + "\t" + newUser.getEmail() + "\t" + newUser.getName() + "\t" + newUser.getAge() + "\t" + newUser.getFunds()
                    + "\t" + newUser.getBet() + "\t" + newUser.isVoted() + "\t" + newUser.isAdmin() + "\t" + newUser.getDescription() + "\t" + newUser.getCurrentVote()
                    + "\t" + newUser.getCurrentStreak() + "\t" + newUser.getLastLogin());
            pw.write("\n");
            pw.flush();
            pw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
