import javax.swing.*;
import javax.awt.*;

public class DatabaseGUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                    JFrame frame = new MainFrame("Database");
                    frame.setSize(500, 400);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
            }
        });

    }
}