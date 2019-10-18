import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class UpdateFrame extends JFrame {

    static String[] originalData = new String[] { "", "", "", "", "", "", "" }; // Each seperate value in old row
    static JLabel[] originalLabels = new JLabel[7];

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
        JTextField[] inputs = new JTextField[7];

        for (int i = 0; i < 7; i++) {
            labels[i] = new JLabel(DatabaseGUI.questions[i], SwingConstants.CENTER);
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
                        setInfo(DatabaseGUI.newInfo);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    MainFrame.addFrame.setVisible(false);
                }
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.updateFrame.setVisible(false);
            }
        });

    }

    public void setInfo(int row, String[] input) throws IOException {
        fr = new FileReader("DatabaseGUI\\Database");
        Br = new BufferedReader(fr);
        fw = new FileWriter("DatabaseGUI\\Database", true);
        Bw = new BufferedWriter(fw);

        /*
         * String output = ""; ID = id(Br); output += String.format("%0" + idSize + "d",
         * (ID + 1)) + ","; for (int i = 0; i < input.length; i++) { output += input[i]
         * + ","; } Bw.write(output + "\n"); if (Bw != null) { Bw.close(); }
         */
    }

    public void updateLabels(int option) {
        originalData = DatabaseGUI.database.getRow(option);

        for (int i = 0; i < originalLabels.length; i++) {
            originalLabels[i].setText(originalData[i + 1]);
        }
    }
}