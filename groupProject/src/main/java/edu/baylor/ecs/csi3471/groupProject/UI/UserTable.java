package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.*;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * UserTable
 * This is the UI for specifically the UserTable
 */
public class UserTable extends JPanel {
        private JTable table;
        //dont think i need text field
        //dont think i need status text
        private TableRowSorter<DefaultTableModel> sorter;

    final Class<?>[] columnClass = new Class[] {
            Integer.class, Integer.class, String.class
    };


    /**
     * createAndShowUserGUI
     * This function creates the graphical user interface for the
     * usertable class.
     */
    //Creating the frame of the table, have to actually build table using something else
        protected static void createAndShowUserGUI() {
            //Create and set up the window.
            JFrame frame = new JFrame("TableFilterDemo");
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            //Create and set up the content pane.
            UserTable newContentPane = new UserTable();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }

    /**
     * UserTable() Constructor
     * This function creates usertable.
     */
    public UserTable() {
            super();
            Runner.logger.info("New userTable generated");
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            String[] columnNames = {"Rank", "Currency", "Name"};
            String[][] data = {{"yes", "no", "idk", "maybe"}};
            int rowNumber = 0;
            //final DefaultTableModel model = new DefaultTableModel(null, columnNames);
        final DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };
            //File selectedFile = openCSV();

            Map<Integer, String> m = new HashMap();

            UserDAO udao = new UserDAO();
            ArrayList<User> uList = null;
            try {
                uList = udao.getUsers();
            } catch (IOException e) {
                Runner.logger.severe("Can't get Users from DAO");
                e.printStackTrace();
            }

            ArrayList<String> name = new ArrayList();
            ArrayList<Integer> curr = new ArrayList();

/*
            try (BufferedReader br = new BufferedReader(
                    new FileReader("UserFile.tsv"))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    Object[] row = line.split("\t");
                    name.add((String) row[0]);
                    curr.add(Integer.valueOf((String) row[5]));
                    m.put(Integer.valueOf((String) row[5]), (String) row[1]);
                    //System.out.println(line);
                    //model.addRow(line.split(","));
                }*/
                //curr.stream().sorted();
                /*Stream<Map.Entry<String,String>> sorted =
                        m.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue());*/
            for (int i = 0; i < uList.size(); i++) {
                name.add(uList.get(i).getName());
                curr.add(uList.get(i).getFunds());
                m.put(uList.get(i).getFunds() ,uList.get(i).getName());
            }

                List<Integer> sortedList = curr.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

                for(int i = 0; i < name.size(); i++){
                    String [] empty = {"_", "_"};

                    model.addRow(empty);
                    model.setValueAt(i + 1, i, 0);
                    model.setValueAt(sortedList.get(i), i, 1);
                    model.setValueAt(m.get(sortedList.get(i)), i, 2);
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


    /**
     * addItem()
     * @param p panel
     * @param c component
     * @param x x-dem
     * @param y y-dem
     * @param width width of panel
     * @param height height of panel
     * @param align alignment og panel
     */
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
                Runner.logger.info("Add Line Action Listener selected");

                JTextField nameW = new JTextField(20), worldW = new JTextField(10), descW = new JTextField(20);
                JTextField recordW = new JTextField(20);
                JTextField winsW = new JTextField(20);
                JTextField lossesW = new JTextField(20);
                JTextField ownerW = new JTextField(20);
                JTextField picW = new JTextField(20);

                JButton closeButton = new JButton("Add");
                JPanel panel1 = new JPanel();
                panel1.setBackground(Color.PINK);
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
                        Runner.logger.severe("Can't open the CharacterFile.csv file");
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        Runner.logger.severe("Can't seek file");
                        ex.printStackTrace();
                    }
                    model.insertRow(0, new Object[]{c.getName(), c.getWorld(),  String.valueOf(c.getRatio()), c.getOwner(),"View", "Edit"});
                }
                ;
            }
        }

    }


