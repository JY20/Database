package app;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileOutputStream;

public class UpdateFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    static String[] originalData = new String[] { "", "", "", "", "", "", "" }; // Each seperate value in old row
    static JLabel[] originalLabels = new JLabel[7];
    static JTextField[] inputs = new JTextField[7];
    static FileWriter fw;
    static FileReader fr;
    static BufferedWriter Bw;
    static BufferedReader Br;

    public UpdateFrame(String title) {
        super(title);

        // Set Layout manager

        GridLayout grid = new GridLayout(9, 3, 30, 10);
        setLayout(grid);

        // Create swing components
        JButton buttonSubmit = new JButton("Submit");
        JButton buttonBack = new JButton("Back");

        JLabel[] titleLabels = new JLabel[] { new JLabel(""), new JLabel("Original Info", SwingConstants.CENTER),
                new JLabel("New Info", SwingConstants.CENTER) };

        JLabel[] labels = new JLabel[7];

        for (int i = 0; i < 7; i++) {
            labels[i] = new JLabel(DatabaseGUI.questions2[i + 1], SwingConstants.CENTER);
            originalLabels[i] = new JLabel(originalData[i], SwingConstants.CENTER);
            inputs[i] = new JTextField();
        }

        // Add swing components to content pane
        Container c = getContentPane();

        for (int i = 0; i < 3; i++) {
            c.add(titleLabels[i]);
        }

        for (int i = 0; i < 7; i++) {
            c.add(labels[i]);
            c.add(originalLabels[i]);
            c.add(inputs[i]);
        }
        c.add(buttonBack);
        c.add(buttonSubmit);

        // Add Behaviour
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // boolean badData = false;
                try {
                    for (int i = 0; i < DatabaseGUI.newInfo.length; i++) {
                        if (inputs[i].getText().contains(",")) {
                            throw new IllegalArgumentException();
                        }
                        DatabaseGUI.newInfo[i] = inputs[i].getText();

                        int tempID = DatabaseGUI.database.lastID;
                        DatabaseGUI.database.updateRow(tempID, DatabaseGUI.newInfo);

                        replaceLines(DatabaseGUI.database.getStringID(tempID),
                                DatabaseGUI.database.getRowString(tempID));

                        MainFrame.updateFrame.setVisible(false);
                        DatabaseGUI.frame.setVisible(true);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Input not valid");
                }
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.updateFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });
    }

    public void setInfo(int row, String[] input) throws IOException {
        fr = new FileReader("Database");
        Br = new BufferedReader(fr);
        fw = new FileWriter("Database", true);
        Bw = new BufferedWriter(fw);

    }

    public void updateLabels(int option) {
        originalData = DatabaseGUI.database.getRow(option);

        for (int i = 0; i < originalLabels.length; i++) {
            originalLabels[i].setText(originalData[i + 1]);
            inputs[i].setText(originalData[i + 1]);
        }
    }

    // getStringID / getRowString
    public static void replaceLines(String ID, String replaced) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("Database"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int counter = 1;
            while ((line = file.readLine()) != null) {
                if (line.startsWith(ID)) {

                    line = replaced + ","; // replace the line here

                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                    System.out.println(line);
                    counter++;
                } else {
                    line = DatabaseGUI.database.getRowString(counter) + ","; // replace the line here

                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                    System.out.println(line);
                    counter++;
                }
            }
            file.close();
            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("Database");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}