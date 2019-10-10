import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

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
                buttonUpdate.setVisible(false);
                buttonAdd.setVisible(false);
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAdd.setVisible(false);
                buttonUpdate.setVisible(false);
            }
        });

    }
}