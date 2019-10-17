import javax.swing.*;
import javax.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;

public class DatabaseGUI {
    static Scanner scan = new Scanner(System.in);

    static ArrayList<String> OtherLines = new ArrayList<String>(); // All other lines

    static String[] newInfo = new String[] { "", "", "", "", "", "", "" }; // new information
    static String[] questions = new String[] { "Name", "Age", "Period 1 Class", "Period 2 Class", "Period 3 Class",
            "Period 4 Class", "Period 5 Class" }; // questions
    static String[] questions2 = new String[] { "ID", "Name", "Age", "Period 1 Class", "Period 2 Class",
            "Period 3 Class", "Period 4 Class", "Period 5 Class" }; // questions

    // static ArrayList<String>[] database = new ArrayList[8];
    static Database database = new Database(questions2);

    public static void main(String[] args) throws IOException {

        database.setup();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                JFrame frame = new MainFrame("Database");
                frame.setSize(500, 400);
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