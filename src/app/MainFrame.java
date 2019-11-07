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

    public static AddFrame addFrame = new AddFrame("Add Data");
    public static UpdateFrame updateFrame = new UpdateFrame("Update Data");
    public static ViewFrame viewFrame = new ViewFrame("View Data");

    public MainFrame(String title) {
        super(title);
        // Set Layout manager
        setLayout(new FlowLayout());

        // Create swing components
        JButton buttonUpdate = new JButton("Update Data");
        JButton buttonAdd = new JButton("Add Data");
        JButton buttonView = new JButton("View Data");
        // JButton buttonDelete = new JButton("Delete Entry");

        // Add swing components to content pane
        Container c = getContentPane();
        //GridLayout button

        c.add(buttonUpdate);
        c.add(buttonAdd);
        c.add(buttonView);
        // Add Behaviour
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = -999;

                try {
                    // Open a input dialog and store the ID inputted to 'option'.
                    option = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your ID"));
                    try {
                        if (option == -999) {
                            throw new Exception();
                        } else if (option <= 0 || option > DatabaseGUI.database.largestID) {
                            throw new IllegalArgumentException();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Input not valid");
                    }
                } catch (Exception ex) {
                    // The user pressed the cancel button
                }

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
                addFrame.updateInputs();
                addFrame.setSize(600, 500);
                addFrame.setLocationRelativeTo(null);
                addFrame.setVisible(true);
            }
        });
        
        buttonView.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseGUI.frame.setVisible(false);
                viewFrame.setSize(600, 500);
                viewFrame.setLocationRelativeTo(null);
                viewFrame.setVisible(true);
            }
        });
    }

}
