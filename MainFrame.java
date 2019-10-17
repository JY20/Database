import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public static JFrame addFrame = new AddFrame("Add Data");
    public static JFrame updateFrame = new UpdateFrame("Update Data");

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
                updateFrame.setSize(500, 400);
                updateFrame.setLocationRelativeTo(null);
                updateFrame.setVisible(true);
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame.setSize(500, 400);
                addFrame.setLocationRelativeTo(null);
                addFrame.setVisible(true);
            }
        });
    }
}