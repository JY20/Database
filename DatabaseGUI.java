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
        // database.addRow(questions2);
        // database.getRow(1);
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

    /*
     * public static void Copy(String ID, BufferedReader x) throws IOException {
     * String teS = x.readLine(); String id = teS.substring(0, idSize); String PrS =
     * ""; while (teS != null) { PrS = teS; id = PrS.substring(0, idSize); if
     * (!id.equals(ID)) { OtherLines.add(PrS); } else { ChangeLine = PrS; } teS =
     * x.readLine(); } x.close(); }
     * 
     * public static void parts(BufferedReader x) throws IOException { String teS =
     * ChangeLine; String PrS = ""; while (teS != null) { PrS = teS; int lineSize =
     * PrS.length() - idSize; int WP = 0; String teteS = ""; for (int i = 0; i <
     * lineSize; i++) { teS = PrS.substring(idSize + i, idSize + (i + 1)); if
     * (teS.equals(",")) { OLineParts[WP] = teteS; OriginalParts[WP] = teteS; WP++;
     * teteS = ""; } else { teteS += teS; } } teS = null; } }
     * 
     * public static void ChangeInfo(String id, String[] x) throws IOException {
     * Copy(id, Br); parts(Br); String teS = ""; for (int i = 0; i <
     * OriginalParts.length; i++) { say(OriginalParts[i]); } for (int i = 0; i <
     * x.length; i++) { if (!OLineParts[i].equals(x[i])) { OLineParts[i] = x[i]; } }
     * teS += id; for (int i = 0; i < OLineParts.length; i++) { teS += OLineParts[i]
     * + ","; } OtherLines.add(teS);
     * 
     * fw = new FileWriter("Database", false); Bw = new BufferedWriter(fw);
     * 
     * Bw.write(OtherLines.get(0)); for (int i = 1; i < OtherLines.size(); i++) {
     * Bw.write("\n" + OtherLines.get(i)); } Bw.close(); }
     */

}