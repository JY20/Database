package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DatabaseGUI {
    static Scanner scan = new Scanner(System.in);

    static ArrayList<String> OtherLines = new ArrayList<String>(); // All other lines

    static String[] newInfo = new String[] { "", "", "", "", "", "", "" }; // new information

    public static String[] questions = new String[] { "ID", "Name", "Age", "Period 1 Class", "Period 2 Class",
            "Period 3 Class", "Period 4 Class", "Period 5 Class" }; // questions

    static Database database = new Database(questions);
    static JFrame frame = new MainFrame("Database");
    
    public static void main(String[] args) throws IOException {

        database.setup();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                frame.setSize(600, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static void say(String x) {
        System.out.println(x);
    }

    public static void say(int x) {
        System.out.println(x);
    }
}