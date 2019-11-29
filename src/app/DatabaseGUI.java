package app;
// Jimmy Yan and Haashim Rehan

// 10 29 2019

// Import the libraries
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DatabaseGUI {

    static ArrayList<String> OtherLines = new ArrayList<String>(); // All other lines

    static String[] newInfo = new String[] { "", "", "", "", "", "", "" }; // new information

    public static String[] questions = new String[] { "ID", "Name", "Age", "Period 1 Class", "Period 2 Class",
            "Period 3 Class", "Period 4 Class", "Period 5 Class" }; // questions

    static Database database = new Database(questions); // Creates a database object with the questions as columns

    static JFrame frame = new MainFrame("Database"); // Menu screen

    public static void main(String[] args) throws IOException {

        database.setup();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                frame.setSize(600, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program when frame is closed
                frame.setLocationRelativeTo(null); // start frame in center
                frame.setVisible(true); // make frame visible
            }
        });
    }
}