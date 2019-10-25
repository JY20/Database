package app;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public static JFrame addFrame = new AddFrame("Add Data");
    public static UpdateFrame updateFrame = new UpdateFrame("Update Data");

    public MainFrame(String title) {
        super(title);
        // Set Layout manager
        setLayout(new FlowLayout());

        // Create swing components
        JButton buttonUpdate = new JButton("Update Data");
        JButton buttonAdd = new JButton("Add Data");

        // Add swing components to content pane
        Container c = getContentPane();

        c.add(buttonUpdate);
        c.add(buttonAdd);

        // Add Behaviour
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // try {
                int option = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your ID"));
                if (option <= DatabaseGUI.database.largestID && option > 0) {
                    DatabaseGUI.frame.setVisible(false);
                    updateFrame.updateLabels(option);
                    updateFrame.setSize(600, 500);
                    updateFrame.setLocationRelativeTo(null);
                    updateFrame.setVisible(true);
                    DatabaseGUI.database.lastID = option;
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseGUI.frame.setVisible(false);
                addFrame.setSize(600, 500);
                addFrame.setLocationRelativeTo(null);
                addFrame.setVisible(true);
            }
        });
    }
}
