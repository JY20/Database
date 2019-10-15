import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class AddFrame extends JFrame {
    static String[] NewInfo = new String[] { "Jimmy Yan", "21", "Math", "Chemistry", "jajja", "Com tech",
            "Com Science" }; // new information
    static String[] Questions = new String[] { "What is your name", "What is your age", "What is your period 1 class",
            "What is your period 2 class", "What is your period 3 class", "What is your period 4 class",
            "What is your period 5 class" }; // questions

    public AddFrame(String title) {
        super(title);
        // Set Layout manager
        setLayout(new FlowLayout());

        // Create swing components
        JButton buttonSubmit = new JButton("Submit");

        // JLabels[] labels;
        // for (int i = 0; i < 7; i++) {
        // labels[i] = new Jlabel("Submit");
        // }

        // Add swing components to content pane
        Container c = getContentPane();
        c.add(buttonSubmit);
        // for (int i = 0; i < 7; i++) {
        // c.add(labels[i]);
        // }

        // Add Behaviour
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.addFrame.setVisible(false);
            }
        });
    }

}