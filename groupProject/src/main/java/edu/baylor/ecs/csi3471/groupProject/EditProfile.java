package edu.baylor.ecs.csi3471.groupProject;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditProfile extends JPanel {

    public EditProfile(String username) throws Exception {
        final User user = getUserByUsername(username);

        JLabel name = new JLabel("Name");
        JLabel age = new JLabel("Age");
        JLabel description = new JLabel("Description");

        final JTextField nameInput = new JTextField();
        final JTextField ageInput = new JTextField();
        final JTextField descInput = new JTextField();

        nameInput.setText(user.getName());
        ageInput.setText(String.valueOf(user.getAge()));
        descInput.setText(user.getDescription());

        JButton editButton = new JButton("Save");

        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(nameInput.getText());
                user.setAge(Integer.valueOf(ageInput.getText()));
                user.setDescription(descInput.getText());

                try {
                    updateUser(user);
                } catch (IOException e1) {
                    throw new NoSuchElementException("This user does not exist");
                }
            }

        });

        nameInput.setColumns(30);
        ageInput.setColumns(30);
        descInput.setColumns(30);

        add(name);
        add(nameInput);

        add(age);
        add(ageInput);

        add(description);
        add(descInput);

        add(editButton);
    }

    public User getUserByUsername(String username) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/groupProject/UserFile.csv"));
        String line = "";

        while ((line = br.readLine()) != null) {
            String[] user = line.split(",");
            User curr = new User(user);

            if (curr.getUsername() == username) {
                return curr;
            }
        }

        throw new NoSuchElementException("This user does not exist");
    }

    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader br = new BufferedReader(new FileReader("/groupProject/UserFile.csv"));
        String line = "";

        while ((line = br.readLine()) != null) {
            String[] user = line.split(",");
            User curr = new User(user);

            users.add(curr);
        }

        return users;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }

    private void updateUser(User user) throws IOException {
        List<String[]> data = new ArrayList<>();
        ArrayList<User> savedUsers = getUsers();

        for (User u : savedUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                u = user;
            }
            data.add(new String[]
                    {u.getUsername(), u.getPassword(), u.getEmail(), u.getName(), String.valueOf(u.getAge()), String.valueOf(u.getFunds()), String.valueOf(u.getBet()), String.valueOf(u.isVoted()), String.valueOf(u.isAdmin()), u.getDescription(), u.getCurrentVote()});
        }


        File csvOutputFile = new File("/groupProject/UserFile.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream().map(this::convertToCSV).forEach(pw::println);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 300);
    }
}