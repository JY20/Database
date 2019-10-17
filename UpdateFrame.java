import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class UpdateFrame extends JFrame {

    static String[] originalData = new String[] { "", "", "", "", "", "", "" }; // Each seperate value in old row

    public UpdateFrame(String title) {
        super(title);

        // Set Layout manager

        GridLayout grid = new GridLayout(9, 3, 30, 10);
        setLayout(grid);
        // Create swing components
        JButton buttonSubmit = new JButton("Submit");
        JButton buttonBack = new JButton("Back");
        JLabel[] titleLabels = new JLabel[] { new JLabel(""), new JLabel("Original Info"), new JLabel("New Info") };
        JLabel[] labels = new JLabel[7];
        JLabel[] originalLabels = new JLabel[7];
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
                MainFrame.updateFrame.setVisible(false);
            }
        });
        
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.updateFrame.setVisible(false);
            }
        });
    }
}