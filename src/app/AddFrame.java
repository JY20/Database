package app;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class AddFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // static String[] input = new String[] { "", "", "", "", "", "", "","" }; //
    // new information
    static JTextField[] inputs = new JTextField[DatabaseGUI.questions.length - 1];

    static int ID = 0; // The id for the information
    static int idSize = 5; // The size of the id which the number of digits in the id

    // File Reading and writing
    static FileWriter fw;
    static FileReader fr;
    static BufferedWriter Bw;
    static BufferedReader Br;

    public AddFrame(String title) {
        super(title);
        // Set Layout manager
        GridLayout grid = new GridLayout(inputs.length + 1, 2, 30, 10); // create a grid to organize components
        setLayout(grid);

        // Create swing components
        JButton buttonSubmit = new JButton("Submit");
        JButton buttonBack = new JButton("Back");
        JLabel[] labels = new JLabel[DatabaseGUI.questions.length - 1];

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(DatabaseGUI.questions[i + 1], SwingConstants.CENTER);
            inputs[i] = new JTextField();
        }

        // Add swing components to content pane
        Container c = getContentPane();

        for (int i = 0; i < DatabaseGUI.questions.length - 1; i++) {
            c.add(labels[i]);
            c.add(inputs[i]);
        }

        c.add(buttonBack);
        c.add(buttonSubmit);
        // Add Behavior
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean badData = false;
                for (int i = 0; i < DatabaseGUI.newInfo.length; i++) {
                    if (inputs[i].getText().contains(",") || inputs[i].getText().equals("")) {
                        badData = true;
                    } else {
                        DatabaseGUI.newInfo[i] = inputs[i].getText();
                    }
                }
                if (!badData) {
                    try {
                        DatabaseGUI.database.addRow(DatabaseGUI.newInfo);
                        addInfo(DatabaseGUI.newInfo);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    MainFrame.addFrame.setVisible(false);
                    DatabaseGUI.frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Input not Valid");
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When back button is pressed, show MainFrame and hide addFrame
                MainFrame.addFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });
    }

    public static void addInfo(String[] input) throws IOException {
        fr = new FileReader("Database");
        Br = new BufferedReader(fr);
        fw = new FileWriter("Database", true);
        Bw = new BufferedWriter(fw);

        String output = "";
        // calculate id number
        ID = id(Br);
        // add the id to the front of the text. (also formats to 5 digits)
        output += String.format("%0" + idSize + "d", (ID + 1)) + ","; // Assign id for the information by adding 1 to
                                                                      // the largest id in the database
        for (int i = 0; i < input.length; i++) {
            output += input[i] + ","; // add all other elements with commas to separate
        }
        Bw.write("\n" + output); // create a newline for the next time a line is added
        if (Bw != null) {
            Bw.close();
        }
    }

    public static int id(BufferedReader x) throws IOException { // Look for the largest id
        String TS = x.readLine();
        String PS = "";
        int id = 0;
        while (TS != null) {
            PS = TS;
            PS = PS.substring(0, idSize);
            if (Integer.parseInt(PS) > id) {
                id = Integer.parseInt(PS);
            }
            TS = x.readLine();
        }
        return id;
    }

    public void updateInputs() { // Set the text to blank
        for (int i = 0; i < inputs.length; i++) {
            inputs[i].setText("");
        }
    }
}