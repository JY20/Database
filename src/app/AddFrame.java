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

    static String[] input = new String[] { "", "", "", "", "", "", "" }; // new information
    static JTextField[] inputs = new JTextField[DatabaseGUI.questions2.length - 1];

    static int ID = 0;
    static int idSize = 5;
    static FileWriter fw;
    static FileReader fr;
    static BufferedWriter Bw;
    static BufferedReader Br;

    public AddFrame(String title) {
        super(title);
        // Set Layout manager

        GridLayout grid = new GridLayout(8, 2, 30, 10);
        setLayout(grid);
        // Create swing components
        JButton buttonSubmit = new JButton("Submit");
        JButton buttonBack = new JButton("Back");
        JLabel[] labels = new JLabel[DatabaseGUI.questions2.length - 1];

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(DatabaseGUI.questions2[i + 1], SwingConstants.CENTER);
            inputs[i] = new JTextField();
        }

        // Add swing components to content pane
        Container c = getContentPane();

        for (int i = 0; i < 7; i++) {
            c.add(labels[i]);
            c.add(inputs[i]);
        }

        c.add(buttonBack);
        c.add(buttonSubmit);
        // Add Behaviour
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean badData = false;
                for (int i = 0; i < DatabaseGUI.newInfo.length; i++) {
                    if (inputs[i].getText().contains(",")) {
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
        ID = id(Br);
        output += String.format("%0" + idSize + "d", (ID + 1)) + ",";
        for (int i = 0; i < input.length; i++) {
            output += input[i] + ",";
        }
        Bw.write(output + "\n");
        if (Bw != null) {
            Bw.close();
        }
    }

    public static int id(BufferedReader x) throws IOException {
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

    public void updateInputs() {
    //    for (int i = 0; i < inputs.length; i++) {
      //      inputs[i].setText("");
        //}
    }
}