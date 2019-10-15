import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class UpdateFrame extends JFrame {

    public UpdateFrame(String title) {
        super(title);
        // Set Layout manager
        // setLayout(new FlowLayout());

        // Create swing components
        JButton buttonSubmit = new JButton("Submit");
        JButton buttonId = new JButton("Search Data");
        JLabel label = new JLabel("text");
        JTextField idInput = new JTextField();

        // Add swing components to content pane
        Container c = getContentPane();

        c.add(idInput);
        c.add(buttonId);
        c.add(buttonSubmit);
        c.add(label);

        // Set Locations
        idInput.setBounds(100, 100, 100, 20);

        // Add Behaviour
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.updateFrame.setVisible(false);
            }
        });

    }
}