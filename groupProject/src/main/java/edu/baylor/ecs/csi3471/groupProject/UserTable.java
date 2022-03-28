package edu.baylor.ecs.csi3471.groupProject;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

    public class UserTable extends JPanel {
        private JTable table;
        //dont think i need text field
        //dont think i need status text
        private TableRowSorter<DefaultTableModel> sorter;

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    createAndShowUserGUI();
                }
            });
        }


        //Creating the frame of the table, have to actually build table using something else
        protected static void createAndShowUserGUI() {
            //Create and set up the window.
            JFrame frame = new JFrame("TableFilterDemo");
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            //Create and set up the content pane.
            edu.baylor.ecs.csi3471.groupProject.UserTable newContentPane = new edu.baylor.ecs.csi3471.groupProject.UserTable();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }

        public UserTable() {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            String[] columnNames = {"Name", "Currency"};
            String[][] data = {{"yes", "no", "idk", "maybe"}};
            int rowNumber = 0;
            final DefaultTableModel model = new DefaultTableModel(null, columnNames);
            //File selectedFile = openCSV();

            ArrayList<String> name = new ArrayList();
            ArrayList<String> curr = new ArrayList();


            try (BufferedReader br = new BufferedReader(
                    new FileReader("userFile.tsv"))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    Object[] row = line.split("\t");
                    name.add((String) row[0]);
                    curr.add((String)row[5]);
                    //System.out.println(line);
                    //model.addRow(line.split(","));
                }
                //curr.stream().sorted();

                for(int i = 0; i < name.size(); i++){
                    String [] empty = {"_", "_"};

                    model.addRow(empty);
                    model.setValueAt(name.get(i), i, 0);
                    model.setValueAt(curr.get(i), i, 1);
                    /*System.out.println(row[3]);
                    System.out.println(row[4]);
                    Integer i = Integer.valueOf((String) row[3]);
                    Integer ii = Integer.valueOf((String) row[4]);
                    if (ii == 0) {
                        ii = 1;
                    }*
                    Double r = Double.valueOf(i / ii);
                    String s = row[3] + "/" + row[4];
                    s = String.valueOf(r);
                    model.setValueAt(s, rowNumber, 2);
                    model.setValueAt(row[7], rowNumber, 3);*/
                    //rowNumber++;
                }
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
                exception.printStackTrace();
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, "Issue with loading file: " + exception.getMessage());
                exception.printStackTrace();
            }


            sorter = new TableRowSorter<DefaultTableModel>(model);
            table = new JTable(model);

            table.setRowSorter(sorter);
            //JTable table = new JTable(DefaultTableModel);
            //TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(table.getModel());
            //table.setRowSorter(sorter);
            table.setPreferredScrollableViewportSize(new Dimension(800, 70));
            table.setFillsViewportHeight(true);

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            Box b = Box.createHorizontalBox();
            //b.add(initMenu(model));
            b.add(Box.createHorizontalGlue());
            add(b);

            JScrollPane scrollPane = new JScrollPane(table);

            add(scrollPane);
            TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);







        }


        public File openCSV() {
            File selectedFile = null;
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fc.showOpenDialog(edu.baylor.ecs.csi3471.groupProject.UserTable.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fc.getSelectedFile();

            } else {
                JOptionPane.showMessageDialog(null, "No file selected");
            }
            return selectedFile;
        }

        public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = x;
            gc.gridy = y;
            gc.gridwidth = width;
            gc.gridheight = height;
            gc.weightx = 100.0;
            gc.weighty = 100.0;
            gc.insets = new Insets(5, 5, 5, 5);
            gc.anchor = align;
            gc.fill = GridBagConstraints.NONE;
            p.add(c, gc);
        }


        private final class AddLineActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
            /*
            JTextField animalField = new JTextField(10);
            JTextField idField = new JTextField(10);
            JTextField nameField = new JTextField(10);
            JTextField ageField = new JTextField(10);
            JTextField infoField = new JTextField(10);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Name: "));
            myPanel.add(animalField);
            myPanel.add(new JLabel("World: "));
            myPanel.add(idField);
            myPanel.add(new JLabel("Wins/Losses: "));
            myPanel.add(nameField);
            myPanel.add(new JLabel("Added By: "));
            myPanel.add(ageField);*/
                //**myPanel.add(new JLabel("Info (Optional): "));
                //myPanel.add(infoField);


                JTextField nameW = new JTextField(20), worldW = new JTextField(10), descW = new JTextField(20);
                JTextField recordW = new JTextField(20);
                JTextField winsW = new JTextField(20);
                JTextField lossesW = new JTextField(20);
                JTextField ownerW = new JTextField(20);
                JTextField picW = new JTextField(20);

                JButton closeButton = new JButton("Add");
                JPanel panel1 = new JPanel();
                panel1.setLayout(new GridBagLayout());
                addItem(panel1, new JLabel("Name:"), 0, 0, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("World:"), 0, 1, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("Description:"), 0, 2, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("Record:"), 0, 3, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("Wins"), 0, 4, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("Losses:"), 0, 5, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("Owner:"), 0, 6, 1, 1, GridBagConstraints.EAST);
                addItem(panel1, new JLabel("Picture:"), 0, 7, 1, 1, GridBagConstraints.EAST);


                addItem(panel1, nameW, 1, 0, 2, 1, GridBagConstraints.WEST);
                //nameW.setText(c.getName());
                //nameW.setEditable(false);
                addItem(panel1, worldW, 1, 1, 1, 1, GridBagConstraints.WEST);
                //worldW.setText(c.getWorld());
                //worldW.setEditable(false);
                addItem(panel1, descW, 1, 2, 2, 1, GridBagConstraints.WEST);
                //descW.setText(c.getDesc());
                //descW.setEditable(false);
                addItem(panel1, recordW, 1, 3, 2, 1, GridBagConstraints.WEST);
                //String rec = "";
                //rec += c.getWin();
                //rec += "/";
                // rec += c.getLoss();
                //recordW.setText(rec);
                //recordW.setEditable(false);
                addItem(panel1, winsW, 1, 4, 2, 1, GridBagConstraints.WEST);
                //winsW.setText(String.valueOf(c.getWin()));
                //winsW.setEditable(false);
                addItem(panel1, lossesW, 1, 5, 2, 1, GridBagConstraints.WEST);
                //lossesW.setText(String.valueOf(c.getLoss()));
                // lossesW.setEditable(false);
                addItem(panel1, ownerW, 1, 6, 2, 1, GridBagConstraints.WEST);
                //ownerW.setText(c.getOwner());
                // ownerW.setEditable(false);
                addItem(panel1, picW, 1, 7, 2, 1, GridBagConstraints.WEST);


                /*** Fix meeee to be able to add characters from csv***/


                int result = JOptionPane.showConfirmDialog(null, panel1,
                        "Enter values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    Integer i = Integer.valueOf(winsW.getText());
                    Character c = new Character(nameW.getText(), worldW.getText(), descW.getText(), i, Integer.valueOf(lossesW.getText()), picW.getText(), ownerW.getText());
                    String res = c.charToCSV();
                    try {
                        RandomAccessFile raf = new RandomAccessFile("CharacterFile.csv","rw");
                        raf.seek(raf.length());
                        raf.writeBytes(res);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    model.insertRow(0, new Object[]{c.getName(), c.getWorld(),  String.valueOf(c.getRatio()), c.getOwner(),"View", "Edit"});
                }
                ;
            }
        }

        private final class RemoveLineActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                if (viewRow < 0) {
                    JOptionPane.showMessageDialog(null, "No row selected");

                } else {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to remove " + model.getValueAt(modelRow, 0) + " " + model.getValueAt(modelRow, 1) + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        model.removeRow(modelRow);
                    }
                }
            }
        }
    }

